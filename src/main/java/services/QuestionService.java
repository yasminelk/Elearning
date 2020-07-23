/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.QuestionDao;
import entities.Question;

/**
 *
 * @author ISLEM
 */
public class QuestionService {

    private QuestionDao dao;

    public QuestionService() {
        this.dao = new QuestionDao(Question.class);
    }

    public Question insertQuestion(Question question) {

        Question result = null;
        try {
            result = getDao().saveAndGet(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return the dao
     */
    public QuestionDao getDao() {
        return dao;
    }

}
