/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Utils.Codes;
import dao.CoursDao;
import java.util.List;
import entities.Cours;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class CoursService {

    private CoursDao dao;

    public CoursService() {
        this.dao = new CoursDao(Cours.class);
    }

    public int insertCours(Cours cours, String username) {

        UserService userService = new UserService();
        try {
            cours.setUser(userService.getDao().getUserByUsername(username));
            if (cours.getUser() != null) {
                if (cours.getUser().isTeacher()) {
                    if (getDao().getCoursByName(cours.getCoursName()) == null) {
                        if (getDao().save(cours)) {
                            return Codes.SUCCESS;
                        }
                    } else {
                        return Codes.USERNAME_EXIST;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Codes.INTERNAL_ERROR;
    }

    public List<Cours> getCourses(String username) throws Exception {
        List result = null;
        UserService userService = new UserService();
        try {
            User user = userService.getDao().getUserByUsername(username);
            if (user != null) {
                if (user.isTeacher()) {
                    result = dao.getCourcesByUser(user.getId());
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    public boolean coursExist(String coursName) {
        if (dao.getCoursByName(coursName) == null) {
            return false;
        }
        return true;
    }

    public boolean coursProfessor(String coursname, String username) {
        boolean result = false;
        Cours c = dao.getCoursByName(coursname);
        if (c != null) {
            if(c.getUser().getUsername().equals(username)){
                result = true;
            }
        }
        return result;
    }

    /**
     * @return the dao
     */
    public CoursDao getDao() {
        return dao;
    }

}
