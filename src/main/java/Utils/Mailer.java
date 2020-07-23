/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ISLEM
 */
public class Mailer {
    
    public static void Sender(String destination,String subject,String message) throws MessagingException{
        Properties pro = new Properties();
        pro.put("mail.smtp.host","smtp.gmail.com");
        pro.put("mail.smtp.port", "587");
        pro.put("mail.smtp.auth", "true");
        pro.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(pro,new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("sender@gmail.com","ur password");
            }
        });
        try{
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("sender@gmail.com"));
            msg.addRecipients(Message.RecipientType.TO,destination);
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
        }
        catch(MessagingException e){
            e.printStackTrace();
            throw new MessagingException("Cant send message");
        }
    }
    
}
