/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import entities.SubQuestion;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class SubquestionDao extends HibernateDao<SubQuestion>{
    
    public SubquestionDao(Class entityClass) {
        super(entityClass);
    }
    
    public List<SubQuestion> getSubQuestionsByQuestion(int questionId) throws Exception{
        List<SubQuestion> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM SubQuestion c WHERE c.question.id = :questionId";
            result = (ArrayList<SubQuestion>) em.createQuery(hql).setParameter("questionId", questionId).getResultList();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            return null;
        } catch (HibernateException exception) {
            throw new Exception(exception);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
        return result;
    }
    
}
