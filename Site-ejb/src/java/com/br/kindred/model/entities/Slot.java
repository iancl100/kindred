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
public class Slot {
    private long slotId;
    private int runeSlotId;
    private Rune rune;

    public Slot() {
    }

    public Slot(long slotId, int runeSlotId, Rune rune) {
        this.slotId = slotId;
        this.runeSlotId = runeSlotId;
        this.rune = rune;
    }

    

    

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public int getRuneSlotId() {
        return runeSlotId;
    }

    public void setRuneSlotId(int runeSlotId) {
        this.runeSlotId = runeSlotId;
    }

    public Rune getRune() {
        return rune;
    }

    public void setRune(Rune rune) {
        this.rune = rune;
    }

    @Override
    public String toString() {
        return "Slot{" + "slotId=" + slotId + ", runeSlotId=" + runeSlotId + ", rune=" + rune + '}';
    }

   
    

    
    
    
}
