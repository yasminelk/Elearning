/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import entities.Cours;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class CoursDao extends HibernateDao<Cours>{
    
    Cours result;
    
    public CoursDao(Class entityClass) {
        super(entityClass);
    }
    
    public Cours getCoursByName(String coursName){
        result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Cours c WHERE c.coursName = :coursName";
            //return (User) _sessionFactory.getCurrentSession().createQuery(hql).setParameter("email", email);
            //TypedQuery<User> query = (TypedQuery<User>) session.createQuery("FROM User WHERE username = " + username);
            result = (Cours) em.createQuery(hql).setParameter("coursName", coursName).getSingleResult();
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
    
    public List<Cours> getCourcesByUser(int userId) throws Exception{
        List<Cours> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Cours c WHERE c.user.id = :userId";
            result = (ArrayList<Cours>) em.createQuery(hql).setParameter("userId", userId).getResultList();
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
