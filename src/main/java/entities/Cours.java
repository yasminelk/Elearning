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

/**
 *
 * @author ISLEM
 */

@Entity
public class Cours implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "cours_name")
    private String coursName;
    
    @Column(name = "year")
    private String year;
    
    @Column(name = "max_students")
    private int maxStudents;
    
    @ManyToOne
    private User user;

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

    /**
     * @return the coursName
     */
    public String getCoursName() {
        return coursName;
    }

    /**
     * @param coursName the coursName to set
     */
    public void setCoursName(String coursName) {
        this.coursName = coursName;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the maxStudents
     */
    public int getMaxStudents() {
        return maxStudents;
    }

    /**
     * @param maxStudents the maxStudents to set
     */
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
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

    
    
}
