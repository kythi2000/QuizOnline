/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.QuizDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class QuizDAO implements Serializable{
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
    
    public boolean create(QuizDTO dto) throws Exception{
        boolean check = false;
        try{
            String sql = "insert into Quiz(QuizID, Email, SubjectID, Point, DateOfCreate) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, dto.getQuizID());
            preS.setString(2, dto.getEmail());
            preS.setString(3, dto.getSubjectID());      
            preS.setFloat(4, dto.getPoint());
            preS.setTimestamp(5, new Timestamp(new Date().getTime()));
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public String getLastQuestionID() throws Exception {       
        String questionID = null;       
        String sql = "select QuizID from Quiz "
                + "where DateOfCreate = (select MAX(DateOfCreate) "
                + "from Quiz)";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if(rs.next()) {
               questionID = rs.getString("QuizID");
            }
        } finally {
            closeConnection();
        }
        return questionID;
    } 
    
    public List<QuizDTO> getQuizByEmail(String email) throws Exception {
        List<QuizDTO> list = null;
        String subjectID;
        Date date;
        float point;
        QuizDTO dto = null;       
        String sql = "select SubjectID, Point, DateOfCreate from Quiz "
                + "where Email = ?";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, email);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                subjectID = rs.getString("SubjectID");
                date = rs.getTimestamp("DateOfCreate");
                point = rs.getFloat("Point");
                dto = new QuizDTO(subjectID, point, date);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public List<QuizDTO> search(String search) throws Exception {
        List<QuizDTO> list = null;
        String subjectID;
        Date date;
        float point;
        QuizDTO dto = null;       
        String sql = "select SubjectID, Point, DateOfCreate from Quiz "
                + "where SubjectID like ?";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, "%" + search + "%");
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                date = rs.getTimestamp("DateOfCreate");
                point = rs.getFloat("Point");
                subjectID = rs.getString("SubjectID");
                dto = new QuizDTO(subjectID, point, date);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
