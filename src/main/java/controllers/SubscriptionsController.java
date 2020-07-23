/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utils.Codes;
import entities.Subscription;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.SessionService;
import services.SubscriptionService;

/**
 *
 * @author ISLEM
 */
@Controller
public class SubscriptionsController {

    @RequestMapping("/subscribe")
    public ModelAndView subscribe(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        
        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        SubscriptionService subscribtionService = new SubscriptionService();

        mv.setViewName("subscriptions");

        String coursName = request.getParameter("coursName");

        try {
            switch (subscribtionService.subscribeCours(coursName, user.getUsername())) {
                case Codes.SUCCESS:
                    mv.addObject("alert", "alert-success");
                    mv.addObject("message", "Subscribed to " + coursName + " successfuly");
                    break;
                case Codes.NOT_FOUND:
                    mv.addObject("alert", "alert-danger");
                    mv.addObject("message", "Cours " + coursName + " doesnt exist");
                    break;
                case Codes.ALREADY_EXIST:
                    mv.addObject("alert", "alert-info");
                    mv.addObject("message", "You have already sbscribed to " + coursName);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Internal Error");
        }

        List<Subscription> list = subscribtionService.getUserSubscriptions(user.getUsername());
        if (list != null) {
            mv.addObject("subscriptions_list", list);
        }

        LoginController.refreshSession(request, user);

        return mv;
    }

    @RequestMapping("/subscriptions")
    public ModelAndView getSubscriptions(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        mv.setViewName("subscriptions");
        mv.addObject("subscriptions_list", "");

        SubscriptionService subscribtionService = new SubscriptionService();
        List<Subscription> list = subscribtionService.getUserSubscriptions(user.getUsername());
        if (list != null) {
            mv.addObject("subscriptions_list", list);
        }

        LoginController.refreshSession(request, user);

        return mv;
    }

    @RequestMapping("/subscriptionsProfessor")
    public ModelAndView getSubscriptionsProfessor(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }

        mv.setViewName("subscriptionsProfessor");
        mv.addObject("subscriptions_list", "");

        SubscriptionService subscribtionService = new SubscriptionService();
        List<Subscription> list = null;
        try {
            loadSubscriptions(mv, user.getUsername(), list);
        } catch (Exception ex) {
            Logger.getLogger(SubscriptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        LoginController.refreshSession(request, user);

        return mv;
    }

    @RequestMapping("/approvesub")
    public ModelAndView approve(HttpServletRequest request, HttpServletResponse response, @RequestParam("subId") int subscriptionId) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }
        
        mv.setViewName("subscriptionsProfessor");
        SubscriptionService subscribtionService = new SubscriptionService();

        if (subscribtionService.approveSubscrition(subscriptionId, user.getUsername())) {
            System.out.println("success");
        }

        List<Subscription> list = null;
        loadSubscriptions(mv, user.getUsername(), list);
        
        LoginController.refreshSession(request, user);

        return mv;

    }

    @RequestMapping("/removesub")
    public ModelAndView remove(HttpServletRequest request, HttpServletResponse response, @RequestParam("subId") int subscriptionId) throws IOException, Exception {

        SessionService sessionService = new SessionService();
        User user = sessionService.chekCookie(request);
        ModelAndView mv = new ModelAndView();

        if (user == null) {
            response.sendRedirect("login.html");
            return null;
        }
        
        mv.setViewName("subscriptionsProfessor");
        SubscriptionService subscribtionService = new SubscriptionService();

        if (subscribtionService.deleteSubscrition(subscriptionId, user.getUsername())) {
            System.out.println("success");
        }
        
        List<Subscription> list = null;
        loadSubscriptions(mv, user.getUsername(), list);
        
        LoginController.refreshSession(request, user);

        return mv;

    }

    private void loadSubscriptions(ModelAndView mv, String username, List<Subscription> list) throws Exception {
        SubscriptionService subscribtionService = new SubscriptionService();
        list = subscribtionService.getProfessorSubscriptions(username);
        if (list != null) {
            mv.addObject("subscriptions_list", list);
        }
    }
}

