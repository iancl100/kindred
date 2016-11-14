/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.Summoner;
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
public class SummonerDAO implements GenericDAO<Summoner>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.EXTENDED)
    EntityManager em;
    
    @Override
    public void create(Summoner e) {
        em.persist(e);
    }

    @Override
    public List<Summoner> read() {
        Query query = em.createNamedQuery("Summoner.findAll");
        return (List<Summoner>)query.getResultList();
    }

    @Override
    public Summoner readById(long id) {
        return em.find(Summoner.class, id);
    }

    @Override
    public void update(Summoner e) {
        em.merge(e);
    }

    @Override
    public void delete(Summoner e) {
        em.merge(e);
        em.remove(e);
    }
    
}
