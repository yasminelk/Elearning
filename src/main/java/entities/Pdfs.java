/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.JDBCType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ISLEM
 */
@Entity
public class Pdfs implements Serializable {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "cours_chapter")
    private int coursChapter;

    @Lob
    @Column(name = "file", columnDefinition="LONGBLOB")
    private Blob file;

    @ManyToOne
    private Cours cours;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the coursChapter
     */
    public int getCoursChapter() {
        return coursChapter;
    }

    /**
     * @param coursChapter the coursChapter to set
     */
    public void setCoursChapter(int coursChapter) {
        this.coursChapter = coursChapter;
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
     * @return the file
     */
    public Blob getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Blob file) {
        this.file = file;
    }

}
