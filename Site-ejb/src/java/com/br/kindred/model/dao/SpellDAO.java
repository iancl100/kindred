/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.Spell;
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
public class SpellDAO implements GenericDAO<Spell>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.EXTENDED)
    EntityManager em;
    
    @Override
    public void create(Spell e) {
        em.persist(e);
    }

    @Override
    public List<Spell> read() {
        Query query = em.createNamedQuery("Spell.findAll");
        return (List<Spell>)query.getResultList();
    }

    @Override
    public Spell readById(long id) {
        return em.find(Spell.class, id);
    }

    @Override
    public void update(Spell e) {
        em.merge(e);
    }

    @Override
    public void delete(Spell e) {
        em.merge(e);
        em.remove(e);
    }
    
}
