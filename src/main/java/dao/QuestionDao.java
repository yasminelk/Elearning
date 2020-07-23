/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import entities.Question;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class QuestionDao extends HibernateDao<Question> {

    public QuestionDao(Class entityClass) {
        super(entityClass);
    }

    public List<Question> getQuestionsByExam(int examId) throws Exception {
        List<Question> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Question c WHERE c.exam.id = :examId";
            result = (ArrayList<Question>) em.createQuery(hql).setParameter("examId", examId).getResultList();
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
