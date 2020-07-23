/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.MarkDao;
import java.util.ArrayList;
import java.util.List;
import entities.Exam;
import entities.Mark;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class MarkService {

    private MarkDao dao;

    public MarkService() {
        dao = new MarkDao(Mark.class);
    }

    public boolean insertMark(Mark mark) {
        try {
            return dao.save(mark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Mark> getCoursMarks(String coursName, String username) throws Exception {
        List<Mark> result = null;
        Mark mark;
        List<User> users;
        CoursService coursService = new CoursService();
        SubscriptionService subscribtionService = new SubscriptionService();
        if (coursService.coursProfessor(coursName, username)) {
            result = new ArrayList<Mark>();
            users = subscribtionService.getSubscribedUsers(coursName);
            if (!users.isEmpty()) {
                for (User u : users) {
                    mark = dao.getMarkByCoursnameAndUserId(coursName, u.getId());
                    if (mark == null) {
                        mark = new Mark();
                        mark.setUser(u);
                    }
                    result.add(mark);
                }
            }
        }
        return result;
    }

    public Mark studentMark(Exam exam, String username) {
        Mark result = null;
        result = dao.getMarkByExamAndUserName(exam.getId(), username);
        return result;
    }

    /**
     * @return the dao
     */
    public MarkDao getDao() {
        return dao;
    }

}
