/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ExamDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entities.Cours;
import entities.Exam;
import entities.Question;
import entities.SubQuestion;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class ExamService {

    private ExamDao dao;

    public ExamService() {
        this.dao = new ExamDao(Exam.class);
    }

    public Exam insertExam(Exam exam, Cours cours) {

        try {
            Exam e = getDao().getExamByCours(cours.getId());
            if (e == null) {
                exam.setCours(cours);
                exam = getDao().saveAndGet(exam);
                MailService.notifyExamSubmited(e);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exam;
    }

    public boolean examPassed(Exam exam, String username) throws Exception {
        UserService userService = new UserService();
        QuestionService questionService = new QuestionService();
        SubquestionService subquestionService = new SubquestionService();
        AnswerService answerService = new AnswerService();
        User user = userService.getDao().getUserByUsername(username);
        List<SubQuestion> subQuestions;
        if (user != null) {
            List<Question> questions = questionService.getDao().getQuestionsByExam(exam.getId());
            for (Question q : questions) {
                subQuestions = subquestionService.getDao().getSubQuestionsByQuestion(q.getId());
                for (SubQuestion sq : subQuestions) {
                    if (answerService.getDao().getAnswersByUserAndSub(user.getId(), sq.getId()) != null) {
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * @return the dao
     */
    public ExamDao getDao() {
        return dao;
    }

}
