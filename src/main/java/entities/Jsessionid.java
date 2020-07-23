/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author ISLEM
 */
@Entity
public class Jsessionid implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "jsession_id")
    private String jsessionid;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @ManyToOne
    private User user;

    /**
     * @return the jsessionid
     */
    public String getJsessionid() {
        return jsessionid;
    }

    /**
     * @param jsessionid the jsessionid to set
     */
    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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
