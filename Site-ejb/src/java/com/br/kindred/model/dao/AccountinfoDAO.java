/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.Accountinfo;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author First Place
 */
@Stateful
@LocalBean
public class AccountinfoDAO implements GenericDAO<Accountinfo>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.EXTENDED)
    EntityManager em;
    
    @Override
    public void create(Accountinfo e) {
        em.persist(e);
    }

    @Override
    public List<Accountinfo> read() {
        Query query = em.createNamedQuery("Accountinfo.findAll");
        return (List<Accountinfo>)query.getResultList();
    }

    @Override
    public Accountinfo readById(long id) {
        return em.find(Accountinfo.class, id);
    }

    @Override
    public void update(Accountinfo e) {
        em.merge(e);
    }

    @Override
    public void delete(Accountinfo e) {
        em.merge(e);
        em.remove(e);
    }
    
}
