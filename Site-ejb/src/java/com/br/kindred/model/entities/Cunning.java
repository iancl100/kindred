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
public class Cunning {
    private List<MasteryLine> lines;

    public Cunning() {
    }

    public Cunning(List<MasteryLine> lines) {
        this.lines = lines;
    }

    public List<MasteryLine> getLines() {
        return lines;
    }

    public void setLines(List<MasteryLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Cunning{" + "lines=" + lines + '}';
    }
    
    
}
