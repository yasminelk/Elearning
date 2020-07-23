/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.Mailer;
import entities.Exam;
import entities.User;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author ISLEM
 */
public class MailService {

    public static void notifySubscrbtionAccepted(String coursname, String username, String email) throws MessagingException {
        String msg = "Dear " + username + ", your subscription in " + coursname + " has been accepted";
        Mailer.Sender(email, "Subscribtion Info", msg);
    }

    public static void notifyExamSubmited(Exam e) throws Exception {
        SubscriptionService subscriptionService = new SubscriptionService();
        List<User> users = subscriptionService.getSubscribedUsers(e.getCours().getCoursName());
        if (users != null) {
            for (User u : users) {
                String msg = "Dear " + u.getUsername() + ", an exam of " + e.getCours().getCoursName() + " has been scheduled on "+e.getStartDate();
                Mailer.Sender(u.getEmail(), "Exam Info", msg);
            }
        }
    }

}
