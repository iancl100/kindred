/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.Champion;
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
public class ChampionDAO implements GenericDAO<Champion>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.EXTENDED)
    EntityManager em;
    
    @Override
    public void create(Champion e) {
        em.persist(e);
    }

    @Override
    public List<Champion> read() {
        Query query = em.createNamedQuery("Champion.findAll");
        return (List<Champion>)query.getResultList();
    }

    @Override
    public Champion readById(long id) {
        return em.find(Champion.class, id);
    }

    @Override
    public void update(Champion e) {
        em.merge(e);
    }

    @Override
    public void delete(Champion e) {
        em.merge(e);
        em.remove(e);
    }
    
}
