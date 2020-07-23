/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.NoResultException;
import entities.Answer;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author ISLEM
 */
public class AnswerDao extends HibernateDao<Answer>{
    
    Answer result;
    
    public AnswerDao(Class entityClass) {
        super(entityClass);
    }
    
    public Answer getAnswersByUserAndSub(int userId, int subId) throws Exception{
        Answer result = null;
        Query query; 
        try {
            String hql = "FROM Answer c WHERE c.user.id = :userId AND c.subQuestion.id = :subId";
            result = (Answer) em.createQuery(hql)
                    .setParameter("userId", userId)
                    .setParameter("subId", subId)
                    .getSingleResult();
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
