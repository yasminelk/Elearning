/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utils.Codes;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Cours;
import entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.CoursService;
import services.SessionService;
import services.UserService;

/**
 *
 * @author ISLEM
 */
@Controller
public class CoursController {

    @RequestMapping("/addCours")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        Cours cours = new Cours();
        cours.setYear(request.getParameter("year"));
        cours.setCoursName(request.getParameter("coursName"));
        cours.setMaxStudents(Integer.parseInt(request.getParameter("maxStudents")));

        CoursService coursService = new CoursService();
        mv.setViewName("cours");

        switch (coursService.insertCours(cours, user.getUsername())) {
            case Codes.SUCCESS:
                mv.addObject("alert", "alert-success");
                mv.addObject("message", "Cours added successfuly");
                break;
            case Codes.USERNAME_EXIST:
                mv.addObject("alert", "alert-danger");
                mv.addObject("message", "Cours Already exist");
                break;
        }

        List<Cours> list = null;
        refresh(mv, user.getUsername(), list);
        LoginController.refreshSession(request, user);

        return mv;

    }

    @RequestMapping("/coursProfessor")
    public ModelAndView courcesProfessor(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        mv.setViewName("cours");
        List<Cours> list = null;

        refresh(mv, user.getUsername(), list);

        LoginController.refreshSession(request, user);

        return mv;
    }

    private void refresh(ModelAndView mv, String username, List<Cours> list) throws Exception {
        mv.addObject("cours_list", "");
        CoursService coursService = new CoursService();
        list = coursService.getCourses(username);
        if (list != null) {
            if (list.size() > 0) {
                mv.addObject("cours_list", list);
            } else {
                mv.addObject("cours_list", "");
            }
        } else {
            throw new Exception();
        }
    }

}
