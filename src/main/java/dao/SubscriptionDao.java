/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import entities.Subscription;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author ISLEM
 */
public class SubscriptionDao extends HibernateDao<Subscription>{
    
    public SubscriptionDao(Class entityClass) {
        super(entityClass);
    }
    
    /*public List<Subscription> getSubsciptionsByUser(int userId) throws Exception{
        String hql = "SELECT subscribtion FROM Subscription subscribtion "
                + " WHERE subscribtion.user.id LIKE '%" + userId + "%'";
        return executeHQLListofResult(hql, 0, 0);
    }*/
    
    public List<Subscription> getSubsciptionsByUser(int userId) throws Exception{
        List<Subscription> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Subscription c WHERE c.user.id = :userId";
            result = (ArrayList<Subscription>) em.createQuery(hql).setParameter("userId", userId).getResultList();
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
    
    public Subscription getSubsciptionByUserAndCours(int userId, int coursId) throws Exception{
        Subscription result = null;
        Query query;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Subscription c WHERE c.user.id = :userId and c.cours.id = :coursId";
            result = (Subscription) em.createQuery(hql)
                    .setParameter("userId", userId)
                    .setParameter("coursId", coursId)
                    .getSingleResult();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            result = null;
        } catch (HibernateException exception) {
            throw new Exception(exception);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
        return result;
    }
    
    public List<Subscription> getSubsciptionsByProfessorId(int userId) throws Exception{
        List<Subscription> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Subscription c WHERE c.cours.user.id = :userId";
            result = (ArrayList<Subscription>) em.createQuery(hql).setParameter("userId", userId).getResultList();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            result = null;
        } catch (HibernateException exception) {
            throw new Exception(exception);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
        return result;
    }
    
    public Subscription getSubsciptionByUsernameAndCoursname(String user, String cours) throws Exception{
        Subscription result = null;
        Query query;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Subscription c WHERE c.user.username = :userId and c.cours.coursName = :coursId";
            result = (Subscription) em.createQuery(hql)
                    .setParameter("userId", user)
                    .setParameter("coursId", cours)
                    .getSingleResult();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            result = null;
        } catch (HibernateException exception) {
            throw new Exception(exception);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
        return result;
    }
    
    public List<Subscription> getSubsciptionsByCours(String coursname) throws Exception{
        List<Subscription> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Subscription c WHERE c.cours.coursName = :coursname";
            result = (ArrayList<Subscription>) em.createQuery(hql).setParameter("coursname", coursname).getResultList();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            result = null;
        } catch (HibernateException exception) {
            throw new Exception(exception);
        } catch (Exception exception) {
            throw new Exception(exception);
        }
        return result;
    }
    
}
