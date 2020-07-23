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
public class Subscription implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "validated")
    private boolean validated;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Cours cours; 

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
     * @return the cours
     */
    public Cours getCours() {
        return cours;
    }

    /**
     * @param cours the cours to set
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * @return the validated
     */
    public boolean isValidated() {
        return validated;
    }
    
    /**
     * @return the validated
     */
    public boolean getValidated() {
        return validated;
    }

    /**
     * @param validated the validated to set
     */
    public void setValidated(boolean validated) {
        this.validated = validated;
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
