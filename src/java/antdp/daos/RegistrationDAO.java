/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.RegistrationDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HP 840 G2
 */
public class RegistrationDAO implements Serializable{
    private Connection conn = null;
    private PreparedStatement preS = null;
    private ResultSet rs = null;
    
    
    private void closeConnection() throws Exception{
        if(rs != null){
            rs.close();
        }
        if(preS != null){
            preS.close();
        }
        if(conn != null){
            conn.close();
        }
    }
    
    public String checkLogin(String email, String password) throws Exception{
        String role = "failed";
        String sql = "select Role from Registration where Email = ? and Password = ? and Status = 'New'";
        try{
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, email);
            preS.setString(2, password);
            rs = preS.executeQuery();
            if(rs.next()){
                role = rs.getString("Role");
            }
        }finally{
            closeConnection();
        }
        return role;
    }
     public RegistrationDTO getRegistrationByKey(String email) throws Exception{
        RegistrationDTO dto = null;
        String fullname, role;
        String sql = "select Fullname, Role from Registration where Email = ? and Status = 'New'";
        try{
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, email);
            rs = preS.executeQuery();
            if(rs.next()){
                fullname = rs.getString("Fullname");
                role = rs.getString("Role");
                dto = new RegistrationDTO(email, fullname, role);
            }
        } finally{
            closeConnection();
        }
        return dto;
    }

     public boolean create(RegistrationDTO dto) throws Exception{
        boolean check = false;
        try {
            String sql = "insert into Registration(Email, Password, Fullname, Role, Status) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, dto.getEmail());
            preS.setString(2, dto.getPassword());
            preS.setString(3, dto.getFullname());
            preS.setString(4, dto.getRole());
            preS.setString(5, "New");
            check = preS.executeUpdate() > 0;
        } finally{
            closeConnection();
        }
        return check;
    }
   
}
