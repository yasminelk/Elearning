/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utils.Codes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Cours;
import entities.Subscription;
import entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.CoursService;
import services.SessionService;
import services.SubscriptionService;
import services.UserService;

/**
 *
 * @author ISLEM
 */
@Controller
public class LoginController {

    public static EntityManagerFactory emf;
    public static EntityManager em;

    @RequestMapping("/tst")
    public ModelAndView tst(HttpServletRequest request, HttpServletResponse response) throws Exception {

        throw new Exception();

    }

    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        } else {
            mv.setViewName("index");
            refreshSession(request, user);
        }

        return mv;
    }

    @RequestMapping("/login.html")
    public ModelAndView getlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            mv.setViewName("sign-in");
        } else {
            response.sendRedirect("home");
            return null;
        }

        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user != null) {
            response.sendRedirect("home");
            return null;
        }

        String username = request.getParameter("username").toLowerCase();
        String password = request.getParameter("password");

        UserService service = new UserService();

        user = service.logUser(username, password);

        if (user != null) {
            response.addCookie(sessionService.addSession(request.getSession().getId(), user));
            response.sendRedirect("home");
            return null;
        } else {
            //Failed to log in
            mv.addObject("alert", "alert-danger");
            mv.addObject("message", "Wrong username or password");
            mv.setViewName("sign-in");
        }

        return mv;
    }

    @RequestMapping("/sign")
    public ModelAndView sign(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user != null) {
            response.sendRedirect("home");
            return null;
        }

        if (request.getParameter("username") == null) {
            mv.setViewName("sign-up");
        } else {
            user = new User();
            user.setUsername(request.getParameter("username").toLowerCase());
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email").toLowerCase());
            user.setFirstName(request.getParameter("firstName").toLowerCase());
            user.setLastName(request.getParameter("lastName").toLowerCase());
            user.setMobileNumber(request.getParameter("mobile"));
            user.setAdress(request.getParameter("adress"));
            if (request.getParameter("professor") != null) {
                user.setTeacher(request.getParameter("professor").equals("on"));
            }

            if (!user.getPassword().equals(request.getParameter("confirm"))) {
                mv.setViewName("sign-up");
                mv.addObject("alert", "alert-danger");
                mv.addObject("ps", true);
                mv.addObject("message", "Passwords doesnt match");
                return mv;
            }

            UserService service = new UserService();

            switch (service.insertUser(user)) {
                case Codes.SUCCESS:
                    mv.addObject("alert", "alert-success");
                    mv.addObject("message", "User created successfuly");
                    mv.setViewName("sign-in");
                    break;
                case Codes.EMAIL_EXIST:
                    mv.addObject("alert", "alert-danger");
                    mv.addObject("message", "Email Already Exist");
                    mv.addObject("em", true);
                    mv.setViewName("sign-up");
                    break;
                case Codes.USERNAME_EXIST:
                    mv.addObject("alert", "alert-danger");
                    mv.addObject("message", "Username Already Exist");
                    mv.addObject("us", true);
                    mv.setViewName("sign-up");
                    break;
                case Codes.INTERNAL_ERROR:
                    throw new Exception();
                default:
                    mv.setViewName("sign-up");
                    break;
            }
        }

        return mv;
    }
    
    @RequestMapping("/500.html")
    public String getError(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "500";
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        mv.setViewName("sign-in");

        return mv;
    }

    public static void refreshSession(HttpServletRequest request, User user) throws Exception {
        HttpSession session = request.getSession();
        List<Cours> list;
        List<Subscription> listSub;
        CoursService coursService = new CoursService();
        SubscriptionService subscribtionService = new SubscriptionService();
        if (user.isTeacher()) {
            list = coursService.getCourses(user.getUsername());
        } else {
            listSub = subscribtionService.getUserSubscriptions(user.getUsername());
            list = new ArrayList<>();
            for (Subscription b : listSub) {
                list.add(b.getCours());
            }
        }
        //Session creation
        session.setAttribute("cours", list);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("firstname", user.getFirstName());
        session.setAttribute("lastname", user.getLastName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("teacher", user.isTeacher());
    }

}
