/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
@Table(name="Answer")
public class Answer implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private SubQuestion subQuestion;
    
    @ManyToOne
    private User user;

    /**
     * @return the subQuestion
     */
    public SubQuestion getSubQuestion() {
        return subQuestion;
    }

    /**
     * @param subQuestion the subQuestion to set
     */
    public void setSubQuestion(SubQuestion subQuestion) {
        this.subQuestion = subQuestion;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
