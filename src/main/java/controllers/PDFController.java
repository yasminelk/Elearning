/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import entities.Pdfs;
import entities.User;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import services.CoursService;
import services.PdfsService;
import services.SessionService;

/**
 *
 * @author ISLEM
 */
@Controller
public class PDFController {

    @RequestMapping("/pdfs")
    public ModelAndView pdfs(HttpServletRequest request, @RequestParam("coursName") String coursName, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        mv.setViewName("upload");
        PdfsService pdfsService = new PdfsService();
        List<Pdfs> pdfs = null;

        if (coursName == null) {
            System.out.println("ERROR");
        } else {
            pdfs = new ArrayList<>();
            for (Pdfs p : pdfsService.getProfessorPdfs(coursName, user.getUsername())) {
                Pdfs pq = new Pdfs();
                pq.setCours(p.getCours());
                pq.setCoursChapter(p.getCoursChapter());
                pq.setId(p.getId());
                pq.setFileName(p.getFileName());
                pdfs.add(pq);
            }
            mv.addObject("coursName", coursName);
            mv.addObject("pdfs", pdfs);
        }

        LoginController.refreshSession(request, user);

        return mv;
    }

    @RequestMapping("/upload")
    public ModelAndView upload(HttpServletRequest request, @RequestParam("chapter") int chapter, @RequestParam("coursName") String coursName, @RequestParam("file") MultipartFile file, HttpSession session, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        PdfsService pdfsService = new PdfsService();
        mv.setViewName("upload");
        List<Pdfs> pdfs = null;

        if (coursName == null || file == null) {
            System.out.println("ERROR");
        } else {
            if (pdfsService.uploadFile(coursName, file, user.getUsername(), chapter)) {
                mv.addObject("alert", "alert-success");
                mv.addObject("message", "File " + file.getOriginalFilename() + " uploaded successfuly");

            } else {
                mv.addObject("alert", "alert-danger");
                mv.addObject("message", "Failed to upload the file");
            }
        }
        pdfs = new ArrayList<Pdfs>();
        for (Pdfs p : pdfsService.getProfessorPdfs(coursName, user.getUsername())) {
            Pdfs pq = new Pdfs();
            pq.setCours(p.getCours());
            pq.setCoursChapter(p.getCoursChapter());
            pq.setId(p.getId());
            pq.setFileName(p.getFileName());
            pdfs.add(pq);
        }
        /*for (Pdfs f : pdfs) {
            f.setFile(null);
        }*/
        mv.addObject("coursName", coursName);
        mv.addObject("pdfs", pdfs);
        LoginController.refreshSession(request, user);

        return mv;
    }

    @RequestMapping("/download")
    public ModelAndView download(@RequestParam("fileid") String fileid, HttpServletResponse response, HttpSession session) throws IOException, Exception {

        response.setContentType("application/pdf");
        PdfsService pdfsService = new PdfsService();
        String username = session.getAttribute("username").toString();

        Pdfs pdfs = pdfsService.downloadFile(Integer.parseInt(fileid), username);
        if (pdfs != null) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + pdfs.getFileName() + "\"");
            response.setHeader("cache-control", "no-cache");
            response.setHeader("cache-control", "must-revalidate");

            ServletOutputStream outs = response.getOutputStream();
            outs.write(pdfs.getFile().getBytes(1, (int) pdfs.getFile().length()));
            outs.flush();
            outs.close();
        }

        return null;
    }

}
