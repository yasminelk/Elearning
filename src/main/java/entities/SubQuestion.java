/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ISLEM
 */

@Entity
public class SubQuestion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "text")
    private String text;
    
    @Column(name = "correct")
    private boolean correct;
    
    @ManyToOne
    private Question question;

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * @return the status
     */
    public boolean isCorrect() {
        return correct;
    }
    
    /**
     * @return the status
     */
    public boolean getCorrect() {
        return correct;
    }

    /**
     * @param correct the status to set
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
