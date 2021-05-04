/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.QuestionDTO;
import antdp.utilities.DBHelpers;
import static antdp.utilities.DBHelpers.makeConnection;
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
public class QuestionDAO implements Serializable{
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
    
   
    
    public QuestionDTO getQuestionByID(String questionID) throws Exception {
        String title, op1, op2, op3, answer, subjectID;
        QuestionDTO dto = null;
        
        String sql = "select Title, Option1, Option2, Option3, Answer, SubjectID from Question "
                + "where QuestionID = ?";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, questionID);
            rs = preS.executeQuery();
            if (rs.next()) {
                title = rs.getString("Title");
                op1 = rs.getString("Option1");
                op2 = rs.getString("Option2");
                op3 = rs.getString("Option3");
                answer = rs.getString("Answer");
                subjectID = rs.getString("SubjectID");
                dto = new QuestionDTO(questionID, title, op1, op2, op3, answer, subjectID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<QuestionDTO> getAllQuestionBySubjectID(String subjectID) throws Exception {
        List<QuestionDTO> list = null;
        QuestionDTO dto = null;
        String title, op1, op2, op3, answer, questionID;
        try {
             String sql = "select QuestionID, Title, Option1, Option2, Option3, Answer, SubjectID from Question "
                + "where SubjectID = ? and Status = 'true'";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, subjectID);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                questionID = rs.getString("QuestionID");
                title = rs.getString("Title");
                op1 = rs.getString("Option1");
                op2 = rs.getString("Option2");
                op3 = rs.getString("Option3");
                answer = rs.getString("Answer");
                dto = new QuestionDTO(questionID, title, op1, op2, op3, answer, subjectID);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public List<QuestionDTO> findLikeTitle(String title, int index) throws Exception{
        List<QuestionDTO> list = null;
        QuestionDTO dto = null;
        String subjectID, op1, op2, op3, answer, questionID;
        String sql = "with listSearch as(select ROW_NUMBER() over (order by Title asc) as r, * "
                        + "from Question "
                        + "where Status = 'true' and Title like ?) "
                        + "select QuestionID, Title, Option1, Option2, Option3, Answer, SubjectID "
                        + "from listSearch "
                        + "where Status = 'true' and r between ?*3 - 2 and ?*3";
        
        try{
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, "%" + title + "%");
            preS.setInt(2, index);
            preS.setInt(3, index);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                questionID = rs.getString("QuestionID");
                title = rs.getString("Title");
                op1 = rs.getString("Option1");
                op2 = rs.getString("Option2");
                op3 = rs.getString("Option3");
                answer = rs.getString("Answer");
                subjectID = rs.getString("SubjectID");
                dto = new QuestionDTO(questionID, title, op1, op2, op3, answer, subjectID);
                list.add(dto);
            }
        }finally{
            closeConnection();
        }
        return list;
    }
    
    public int countQuestionLikeTitle(String title) throws Exception {
        int count = 0;
        String sql = "select count(*) from Question where Title like ? and Status = 'true'";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, "%" + title + "%");
            rs = preS.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public boolean create(QuestionDTO dto) throws Exception{
        boolean check = false;
        try{
            String sql = "insert into Question(QuestionID, Title, Option1, Option2, Option3, Answer, DateOfCreate, SubjectID, Status) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, dto.getQuestionID());
            preS.setString(2, dto.getTitle());
            preS.setString(3, dto.getOption1());
            preS.setString(4, dto.getOption2());
            preS.setString(5, dto.getOption3());
            preS.setString(6, dto.getAnswer());
            preS.setTimestamp(7, new Timestamp(new Date().getTime()));
            preS.setString(8, dto.getSubjectID());
            preS.setBoolean(9, true);
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public boolean update(QuestionDTO dto) throws Exception{
        boolean check = false;
        try{
            String sql = "update Question set Title = ?, Option1 = ?, Option2= ?, Option3 = ?, Answer = ?, SubjectID = ? "
                    + "where QuestionID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            
            preS.setString(1, dto.getTitle());
            preS.setString(2, dto.getOption1());
            preS.setString(3, dto.getOption2());
            preS.setString(4, dto.getOption3());
            preS.setString(5, dto.getAnswer());
            preS.setString(6, dto.getSubjectID());
            preS.setString(7, dto.getQuestionID());
            
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    public String getLastQuestionID() throws Exception {       
        String questionID = null;       
        String sql = "select QuestionID from Question "
                + "where DateOfCreate = (select MAX(DateOfCreate) "
                + "from Question)";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if(rs.next()) {
               questionID = rs.getString("QuestionID");
            }
        } finally {
            closeConnection();
        }
        return questionID;
    } 
    
    public boolean delete(String questionID) throws Exception{
        boolean check = false;
        try{
            String sql = "update Question set Status = 'false' "
                    + "where QuestionID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);            
            preS.setString(1, questionID);
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public List<String> getQuestionIDBySubjectID(String subjectID, int numOfQuestions) throws Exception{
         List<String> list = null;
         String questionID = "";
         try{
             String sql = "select top (?) QuestionID "
                     + "from Question "
                     + "where SubjectID = ? "
                     + "order by newid()";
             conn = DBHelpers.makeConnection();
             preS = conn.prepareStatement(sql);
             preS.setInt(1, numOfQuestions);
             preS.setString(2, subjectID);
             rs = preS.executeQuery();
             list = new ArrayList<>();
             while(rs.next()){
                 questionID = rs.getString(1);
                 list.add(questionID);
             }
         }finally{
             closeConnection();
         }
         return list;
    }
}
