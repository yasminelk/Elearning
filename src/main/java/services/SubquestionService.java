/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.SubquestionDao;
import entities.SubQuestion;

/**
 *
 * @author ISLEM
 */
public class SubquestionService {
    
    private SubquestionDao dao;
    
    public SubquestionService(){
        this.dao = new SubquestionDao(SubQuestion.class);
    }
    
    public SubQuestion insertSubQuestion(SubQuestion subQuestion){
        
        SubQuestion result = null;
        try{
            result = getDao().saveAndGet(subQuestion);
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return the dao
     */
    public SubquestionDao getDao() {
        return dao;
    }
    
}
