/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.NoResultException;
import entities.Exam;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class ExamDao extends HibernateDao<Exam>{
    
    Exam result;
    
    public ExamDao(Class entityClass) {
        super(entityClass);
    }
    
    public Exam getExamByCours(int coursId){
        result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Exam c WHERE c.cours.id = :coursId";
            result = (Exam) em.createQuery(hql).setParameter("coursId", coursId).getSingleResult();
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
