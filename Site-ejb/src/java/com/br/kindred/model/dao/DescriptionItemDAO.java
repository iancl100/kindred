/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.DescriptionItem;
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
public class DescriptionItemDAO implements GenericDAO<DescriptionItem>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.EXTENDED)
    EntityManager em;
    
    @Override
    public void create(DescriptionItem e) {
        em.persist(e);
    }

    @Override
    public List<DescriptionItem> read() {
        Query query = em.createNamedQuery("DescriptionItem.findAll");
        return (List<DescriptionItem>)query.getResultList();
    }

    @Override
    public DescriptionItem readById(long id) {
        return em.find(DescriptionItem.class, id);
    }

    @Override
    public void update(DescriptionItem e) {
        em.merge(e);
    }

    @Override
    public void delete(DescriptionItem e) {
        em.merge(e);
        em.remove(e);
    }
    
}
