/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.NoResultException;
import entities.Jsessionid;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class JsessionidDao extends HibernateDao<Jsessionid>{
    
    private Jsessionid result;
    
    public JsessionidDao(Class entityClass) {
        super(entityClass);
    }
    
    public Jsessionid getJsessionById(String jsessionid){
        result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Jsessionid c WHERE c.jsessionid = :jsessionid";
            result = (Jsessionid) em.createQuery(hql).setParameter("jsessionid", jsessionid).getSingleResult();
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
