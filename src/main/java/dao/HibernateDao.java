/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 * @param <T>
 */
public class HibernateDao<T> implements Serializable {

    public static EntityManager em = null;
    
    protected String className;
    protected EntityManagerFactory emf;
    protected Class<T> entityClass;
    protected List<T> listOfResult;
    protected T result;

    public HibernateDao(Class entityClass) {
        if(em == null){
            emf = Persistence.createEntityManagerFactory("myhibernate");
            em = emf.createEntityManager();
        }
        this.entityClass = entityClass;
        this.className = entityClass.getName();
    }

    public boolean save(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean remove(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public T saveAndGet(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    public T getById(int id) {
        result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM " + this.className + " c WHERE c.id = :id";
            result = (T) em.createQuery(hql).setParameter("id", id).getSingleResult();
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

}
