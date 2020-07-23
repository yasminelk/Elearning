package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ISLEM
 */

@Entity
public class Exam implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "duration")
    private int duration;
    
    @Column(name = "total_points")
    private int totalPoints;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @ManyToOne
    private Cours cours;

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the endDate to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
     * @return the totalPoints
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * @param totalPoints the totalPoints to set
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
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
