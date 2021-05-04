/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP 840 G2
 */
public class QuizDTO implements Serializable{
    private String quizID, email, subjectID;
    private float point;
    private Date dateOfCreate;

    public QuizDTO(String quizID, String email, String subjectID, float point) {
        this.quizID = quizID;
        this.email = email;
        this.subjectID = subjectID;
        this.point = point;
    }

    public QuizDTO(String subjectID, float point, Date dateOfCreate) {
        this.subjectID = subjectID;
        this.point = point;
        this.dateOfCreate = dateOfCreate;
    }

    
    
    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    
    
    
}
