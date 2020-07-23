/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.Codes;
import dao.SubscriptionDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import entities.Cours;
import entities.Subscription;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class SubscriptionService {

    private SubscriptionDao dao;

    public SubscriptionService() {
        dao = new SubscriptionDao(Subscription.class);
    }

    public int subscribeCours(String coursName, String userName) throws Exception {
        UserService userService = new UserService();
        CoursService coursService = new CoursService();
        User user = userService.getDao().getUserByUsername(userName);
        if (user != null) {
            Cours cours = coursService.getDao().getCoursByName(coursName);
            if (cours != null) {
                if (dao.getSubsciptionByUserAndCours(user.getId(), cours.getId()) == null) {
                    Subscription subscribtion = new Subscription();
                    subscribtion.setCours(cours);
                    subscribtion.setUser(user);
                    if (dao.save(subscribtion)) {
                        return Codes.SUCCESS;
                    }
                    return Codes.INTERNAL_ERROR;
                }
                return Codes.ALREADY_EXIST;
            } else {
                return Codes.NOT_FOUND;
            }
        } else {
            return Codes.INTERNAL_ERROR;
        }
    }

    public List<Subscription> getUserSubscriptions(String username) {
        List<Subscription> result;
        UserService userService = new UserService();
        User user = userService.getDao().getUserByUsername(username);
        if (user != null) {
            try {
                return dao.getSubsciptionsByUser(user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Subscription> getProfessorSubscriptions(String username) throws Exception {
        List<Subscription> result = null;
        UserService userService = new UserService();
        User user = userService.getDao().getUserByUsername(username);
        if (user != null) {
            result = (List<Subscription>) dao.getSubsciptionsByProfessorId(user.getId());
        }
        return result;
    }

    public List<User> getSubscribedUsers(String coursname) throws Exception {
        List<User> result = new ArrayList<User>();
        List<Subscription> subscribtions = null;
        subscribtions = dao.getSubsciptionsByCours(coursname);
        if (!subscribtions.isEmpty()) {
            for (Subscription b : subscribtions) {
                result.add(b.getUser());
            }
        }
        return result;
    }

    public boolean approveSubscrition(int subId, String username) {
        Subscription subscribtion = dao.getById(subId);
        if (subscribtion != null) {
            if (subscribtion.getCours().getUser().getUsername().equals(username)) {
                subscribtion.setValidated(true);
                if (dao.update(subscribtion)) {
                    try {
                        MailService.notifySubscrbtionAccepted(subscribtion.getCours().getCoursName(), subscribtion.getUser().getFirstName()+" "+subscribtion.getUser().getLastName(), subscribtion.getUser().getEmail());
                    } catch (MessagingException ex) {
                        Logger.getLogger(SubscriptionService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean deleteSubscrition(int subId, String username) {
        Subscription subscribtion = dao.getById(subId);
        if (subscribtion != null) {
            if (subscribtion.getCours().getUser().getUsername().equals(username)) {
                if (dao.remove(subscribtion)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubscribed(String username, String coursname) throws Exception {
        if (dao.getSubsciptionByUsernameAndCoursname(username, coursname) != null) {
            return true;
        }
        return false;
    }

}
