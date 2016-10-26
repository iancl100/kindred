/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.util.List;

/**
 *
 * @author 31535811
 */
public class Page {
    private long id;
    private String name;
    private boolean current;
    private List<Slot> slots;

    public Page() {
    }

    public Page(long id, String name, boolean current, List<Slot> slots) {
        this.id = id;
        this.name = name;
        this.current = current;
        this.slots = slots;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        return "Rune{" + "id=" + id + ", name=" + name + ", current=" + current + ", slots=" + slots + '}';
    }
    
    
}
