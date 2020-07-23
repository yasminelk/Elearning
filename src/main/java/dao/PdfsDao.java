/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import entities.Pdfs;
import org.hibernate.HibernateException;

/**
 *
 * @author ISLEM
 */
public class PdfsDao extends HibernateDao<Pdfs>{
    
    public PdfsDao(Class entityClass) {
        super(entityClass);
    }
    
    public List<Pdfs> getPdfsByCours(int coursId) throws Exception{
        List<Pdfs> result = null;
        try {
            //em.getTransaction().begin();
            String hql = "FROM Pdfs c WHERE c.cours.id = :coursId";
            result = (ArrayList<Pdfs>) em.createQuery(hql).setParameter("coursId", coursId).getResultList();
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
