/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author 31535811
 */
public class League {

    private String queue, name, tier;
    private long id;
    private List<LeagueLine> lines;

    public League() {
    }

    public League(String queue, String name, String tier, long id, List<LeagueLine> lines) {
        this.queue = queue;
        this.name = name;
        this.tier = tier;
        this.id = id;
        this.lines = lines;
        this.order();
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LeagueLine> getLines() {
        return lines;
    }

    public void setLines(List<LeagueLine> lines) {
        this.lines = lines;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "League{" + "queue=" + queue + ", name=" + name + ", tier=" + tier + ", id=" + id + ", lines=" + lines + '}';
    }
    public void order(){
        Collections.sort(lines, new LeagueLine());
        Collections.reverse(lines);
    }

   

}
