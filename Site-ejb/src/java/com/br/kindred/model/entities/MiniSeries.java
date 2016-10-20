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
public class MiniSeries {
    private int losses,target,wins;
    private String progress;

    public MiniSeries() {
    }

    
    public MiniSeries(int losses, int target, int wins, String progress) {
        this.losses = losses;
        this.target = target;
        this.wins = wins;
        this.progress = progress;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "MiniSeries{" + "losses=" + losses + ", target=" + target + ", wins=" + wins + ", progress=" + progress + '}';
    }
    
    
    
}
