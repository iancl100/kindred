/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

/**
 *
 * @author 31535811
 */
public class Stats {
    private String type;
    private double value;

    public Stats() {
    }

    public Stats(String type, double value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Stats{" + "type=" + type + ", value=" + value + '}';
    }
    
    
}
