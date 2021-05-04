/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;

/**
 *
 * @author HP 840 G2
 */
public class QuestionCreateError implements Serializable{
    
   
    private String answerErr;

    public QuestionCreateError() {
        
    }

    public String getAnswerErr() {
        return answerErr;
    }

    public void setAnswerErr(String answerErr) {
        this.answerErr = answerErr;
    }

   
}
