/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import com.br.kindred.model.dao.MasteryItemDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author 31535811
 */
public class Ferocity {

    MasteryItemDAO masteryItemDAO = lookupMasteryItemDAOBean();
    private List<MasteryItem> lines;

    

    public Ferocity() {
       this.lines = masteryItemDAO.readByTree("Ferocity");
    }

    public List<MasteryItem> getLines() {
        return lines;
    }

    public void setLines(List<MasteryItem> lines) {
        this.lines = lines;
    }

    

    @Override
    public String toString() {
        return "Ferocity{" + "lines=" + lines + '}';
    }

    private MasteryItemDAO lookupMasteryItemDAOBean() {
        try {
            Context c = new InitialContext();
            return (MasteryItemDAO) c.lookup("java:global/Site/Site-ejb/MasteryItemDAO!com.br.kindred.model.dao.MasteryItemDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
