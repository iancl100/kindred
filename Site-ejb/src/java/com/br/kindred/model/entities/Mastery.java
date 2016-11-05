/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 31535811
 */
public class Mastery {
    private String name;
    private Ferocity ferocity;
    private Cunning cunning;
    private Resolve resolve;

    public Mastery() {
    }

    public Mastery(String name, Ferocity ferocity, Cunning cunning, Resolve resolve) {
        this.name = name;
        this.ferocity = ferocity;
        this.cunning = cunning;
        this.resolve = resolve;
    }

    public Mastery(String name) {
        this.name = name;
        List<MasteryLine> lines = new ArrayList<>();
        List<MasteryItem> itens = new ArrayList<>();
        
    }
    
    
    public Ferocity getFerocity() {
        return ferocity;
    }

    public void setFerocity(Ferocity ferocity) {
        this.ferocity = ferocity;
    }

    public Cunning getCunning() {
        return cunning;
    }

    public void setCunning(Cunning cunning) {
        this.cunning = cunning;
    }

    public Resolve getResolve() {
        return resolve;
    }

    public void setResolve(Resolve resolve) {
        this.resolve = resolve;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public String toString() {
        return "Mastery{" + "ferocity=" + ferocity + ", cunning=" + cunning + ", resolve=" + resolve + '}';
    }
    
    
}
