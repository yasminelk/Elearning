/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.NoResultException;
import entities.User;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class UserDao extends HibernateDao<User> {

    private User result;

    public UserDao(Class entityClass) {
        super(entityClass);
    }

    public User getUserByUsername(String username) {
        result = null;
        try {
            String hql = "FROM User c WHERE c.username = :username";
            result = (User) em.createQuery(hql).setParameter("username", username).getSingleResult();
        } catch (NoResultException exception) {
            result = null;
        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public User getUserByEmail(String email) {
        result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM User c WHERE c.email = :email";
            result = (User) em.createQuery(hql).setParameter("email", email).getSingleResult();
            //em.getTransaction().commit();
        } catch (NoResultException exception) {
            result = null;
        } catch (HibernateException exception) {
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }

}
