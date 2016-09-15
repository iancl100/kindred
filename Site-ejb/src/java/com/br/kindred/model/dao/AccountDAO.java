/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.dao;

import com.br.kindred.model.entities.Account;
import com.br.kindred.model.entities.Accountinfo;
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
public class AccountDAO implements GenericDAO<Account>{
    
    @PersistenceContext(unitName="kindred-ejbPU", type= PersistenceContextType.TRANSACTION)
    EntityManager em;
    
    @Override
    public void create(Account e) {
        em.persist(e);
    }

    @Override
    public List<Account> read(long id) {
        Query query = em.createNamedQuery("Account.findAll");
        return (List<Account>)query.getResultList();
    }

    @Override
    public Account readById(long id) {
        return em.find(Account.class, id);
    }
    public Account readByUsername(String username){
        Query query = em.createNamedQuery("Account.findByUsername").setParameter("username", username);
        Object obj= null;
        try{
            obj=query.getSingleResult();
            return (Account)obj; 
        }catch(NoResultException ex){
            return null;
        }
    }
    public Account readByEmail(String email){
        Query query = em.createNamedQuery("Accountinfo.findByEmail").setParameter("email", email);
        Object obj= null;
        try{
            obj=query.getSingleResult();
            return ((Accountinfo)obj).getAccount(); 
        }catch(NoResultException ex){
            return null;
        }
    }

    @Override
    public void update(Account e) {
        em.merge(e);
    }

    @Override
    public void delete(Account e) {
        em.merge(e);
        em.remove(e);
    }
    
}
