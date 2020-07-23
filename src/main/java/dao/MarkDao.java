/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.NoResultException;
import entities.Mark;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class MarkDao extends HibernateDao<Mark> {

    public MarkDao(Class entityClass) {
        super(entityClass);
    }

    public Mark getMarkByExamAndUserName(int examId, String username) {
        result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Mark c WHERE c.exam.id = :examId AND c.user.username = :usern";
            result = (Mark) em.createQuery(hql).setParameter("examId", examId).setParameter("usern", username).getSingleResult();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            return null;
        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public Mark getMarkByCoursnameAndUserId(String coursName, int userId) throws Exception {
        Mark result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Mark c WHERE c.exam.cours.coursName = :coursName AND c.user.id = :userId";
            result = (Mark) em.createQuery(hql).setParameter("coursName", coursName).setParameter("userId", userId).getSingleResult();
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
