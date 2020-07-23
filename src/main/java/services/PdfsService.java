/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PdfsDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import entities.Cours;
import entities.Pdfs;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ISLEM
 */
public class PdfsService {

    private PdfsDao dao;

    public boolean uploadFile(String coursname, MultipartFile file, String username, int chapter) throws IOException, SQLException {
        boolean result = false;
        CoursService coursService = new CoursService();
        Cours cours = coursService.getDao().getCoursByName(coursname);
        if (cours != null) {
            if (cours.getUser().getUsername().equals(username)) {
                String fileName = file.getOriginalFilename();
                if (fileName.toLowerCase().endsWith(".pdf")) {
                    Pdfs pdfs = new Pdfs();
                    pdfs.setCours(cours);
                    pdfs.setFileName(fileName);
                    pdfs.setCoursChapter(chapter);
                    pdfs.setFile(new SerialBlob(file.getBytes()));
                    System.out.println(""+pdfs.getFile());
                    dao.save(pdfs);
                    result = true;
                }
            }
        }

        return result;
    }
    
    public List<Pdfs> getProfessorPdfs(String coursname, String username) throws Exception{
        List<Pdfs> result = null;
        CoursService coursService = new CoursService();
        SubscriptionService subscribtionService = new SubscriptionService();
        Cours cours = coursService.getDao().getCoursByName(coursname);
        if (cours != null) {
            if (cours.getUser().getUsername().equals(username) || subscribtionService.isSubscribed(username, coursname)) {
                result = dao.getPdfsByCours(cours.getId());
            }
        }
        return result;
    }
    
    public Pdfs downloadFile(int fileid, String username) throws Exception{
        SubscriptionService subscribtionService = new SubscriptionService();
        Pdfs result = null;
        result = dao.getById(fileid);
        /*if (result != null) {
            /*Cours cours = result.getCours();
            if (subscribtionService.isSubscribed(username, username) || cours.getUser().getUsername().equals(username)) {
            return result;
            //}
        }*/
        return result;
    }

    public PdfsService() {
        dao = new PdfsDao(Pdfs.class);
    }

}
