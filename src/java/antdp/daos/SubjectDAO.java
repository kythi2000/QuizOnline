/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.SubjectDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class SubjectDAO implements Serializable{
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
    
    public SubjectDTO getSubjectByID(String subjectID) throws Exception{       
        SubjectDTO dto = null;
        String name, description;
        int numOfQuestions, time;
        try{
            conn = DBHelpers.makeConnection();
            String sql = "select Name, Description, NumberOfQuestions, Time from Subject where SubjectID = ?"; 
            preS = conn.prepareStatement(sql);
            preS.setString(1, subjectID);
            rs = preS.executeQuery();
            if(rs.next()){
                name = rs.getString("Name");
                description = rs.getString("Description");
                numOfQuestions = rs.getInt("NumberOfQuestions");
                time = rs.getInt("Time");
                dto = new SubjectDTO(subjectID, name, description, numOfQuestions, time);
            }
        }finally{
            closeConnection();
        }
        return dto;
    }
    
    public List<SubjectDTO> getAllSubject(int index) throws Exception{  
        List<SubjectDTO> list = null;
        SubjectDTO dto = null;
        int numOfQuestions, time;
        String subjectID, name, description;
        try{
            conn = DBHelpers.makeConnection();
            String sql = "with listSearch as(select ROW_NUMBER() over (order by Name asc) as r, * "
                + "from Subject)"
                + "select SubjectID, Name, Description, NumberOfQuestions, Time "
                + "from listSearch "
                + "where r between ?*2 - 1 and ?*2";
            preS = conn.prepareStatement(sql);
            preS.setInt(1, index);
            preS.setInt(2, index);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                subjectID = rs.getString("SubjectID");
                name = rs.getString("Name");
                description = rs.getString("Description");
                numOfQuestions = rs.getInt("NumberOfQuestions");
                time = rs.getInt("Time");
                dto = new SubjectDTO(subjectID, name, description, numOfQuestions, time);
                list.add(dto);
            }
        }finally{
            closeConnection();
        }
        return list;
    }
    
    public int countAllSubject() throws Exception {
        int count = 0;
        String sql = "select count(*) from Subject";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<String> getAllSubjectID() throws Exception{
        List<String> list = null;
        try {
            String sql = "select SubjectID from Subject";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                String subjectID = rs.getString("SubjectID");
                list.add(subjectID);
            }
        } finally{
            closeConnection();
        }
        return list;
    }

    public boolean update(SubjectDTO dTO) throws Exception{
        boolean check = false;
        try{
            String sql = "update Subject set Time = ?, NumberOfQuestions = ? "
                    + "where SubjectID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setInt(1, dTO.getTime());
            preS.setInt(2, dTO.getNumberOfQuestions());
            preS.setString(3, dTO.getSubjectID());
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
}
