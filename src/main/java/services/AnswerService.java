/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AnswerDao;
import java.util.List;
import entities.Answer;
import entities.Cours;
import entities.Exam;
import entities.Mark;
import entities.Question;
import entities.SubQuestion;
import entities.User;

/**
 *
 * @author ISLEM
 */
public class AnswerService {

    private final AnswerDao dao;

    public AnswerService() {
        this.dao = new AnswerDao(Cours.class);
    }

    public boolean insertAnswers(Exam e, List<Integer> questions, List<Integer> subQuestions, String username) throws Exception {
        UserService userService = new UserService();
        QuestionService questionService = new QuestionService();
        SubquestionService subquestionService = new SubquestionService();
        MarkService markService = new MarkService();
        Answer answer;
        Mark mark = new Mark();
        Question q;
        SubQuestion subQuestion;

        User user = userService.getDao().getUserByUsername(username);
        if (user != null) {
            for (int i = 0; i < questions.size(); i++) {
                answer = new Answer();
                mark.setTotalPoints(e.getTotalPoints());
                mark.setUser(user);
                mark.setExam(e);
                q = questionService.getDao().getById(questions.get(i));
                if (q != null) {
                    subQuestion = subquestionService.getDao().getById(subQuestions.get(i));
                    if (subQuestion != null) {
                        answer.setSubQuestion(subQuestion);
                        answer.setUser(user);
                        dao.save(answer);
                        if(subQuestion.isCorrect()){
                            mark.setMark(mark.getMark()+q.getPoints());
                        }
                    }
                }
            }
            markService.insertMark(mark);
            return true;
        }
        return false;
    }

    public AnswerDao getDao() {
        return dao;
    }

}
