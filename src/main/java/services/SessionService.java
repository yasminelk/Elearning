/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.DateUtility;
import dao.JsessionidDao;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entities.Jsessionid;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class SessionService {

    private static long sesstionlifems = (long) 31536000 * (long) 1000;
    private static int sesstionlife = 31536000;
    private static String cookieName = "JSESSION";
    private JsessionidDao dao;
    private Jsessionid jsessionid;

    public SessionService() {
        dao = new JsessionidDao(Jsessionid.class);
    }

    public User chekCookie(HttpServletRequest request) {
        User result = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName)) {
                jsessionid = dao.getJsessionById(c.getValue());
                if (jsessionid != null) {
                    if (jsessionid.getExpirationDate().after(new Date())) {
                        result = jsessionid.getUser();
                    }
                }
            }
        }
        return result;
    }

    public Cookie addSession(String sessionId, User user) {
        Jsessionid j = new Jsessionid();
        j.setUser(user);
        j.setJsessionid(sessionId);
        j.setExpirationDate(new Date(DateUtility.getCurrentDateMs() + sesstionlifems));
        if(dao.save(j)){
            Cookie c = new Cookie(cookieName, sessionId);
            c.setMaxAge(sesstionlife);
            return c;
        }
        return null;
    }

}
