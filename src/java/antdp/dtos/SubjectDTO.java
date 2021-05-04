/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class SubjectDTO implements Serializable{
    private String subjectID, name, description;
    private int numberOfQuestions, time;
    private List<QuestionDTO> listQuestion;

    public SubjectDTO(String subjectID, String name, String description, int numberOfQuestions, int time) {
        this.subjectID = subjectID;
        this.name = name;
        this.description = description;
        this.numberOfQuestions = numberOfQuestions;
        this.time = time;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    

    public List<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<QuestionDTO> listQuestion) {
        this.listQuestion = listQuestion;
    }

    

    
    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
