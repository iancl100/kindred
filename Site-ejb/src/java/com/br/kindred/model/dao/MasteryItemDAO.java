/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.MasteryItem;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author First Place
 */
@Stateful
@LocalBean
public class MasteryItemDAO implements GenericDAO<MasteryItem>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.EXTENDED)
    EntityManager em;
    
    @Override
    public void create(MasteryItem e) {
        em.persist(e);
    }

    @Override
    public List<MasteryItem> read() {
        Query query = em.createNamedQuery("MasteryItem.findAll");
        return (List<MasteryItem>)query.getResultList();
    }

    @Override
    public MasteryItem readById(long id) {
        return em.find(MasteryItem.class, id);
    }
//    public MasteryItem readByUsername(String username){
//        Query query = em.createNamedQuery("MasteryItem.findByUsername").setParameter("username", username);
//        Object obj= null;
//        try{
//            obj=query.getSingleResult();
//            return (MasteryItem)obj; 
//        }catch(NoResultException ex){
//            return null;
//        }
//    }
    
    public List<MasteryItem> readByTree(String tree){
        Query query = em.createNamedQuery("MasteryItem.findByMasterytree").setParameter("masterytree", tree);
        return (List<MasteryItem>)query.getResultList();
        
    }

    @Override
    public void update(MasteryItem e) {
        em.merge(e);
    }

    @Override
    public void delete(MasteryItem e) {
        em.merge(e);
        em.remove(e);
    }
    
}
