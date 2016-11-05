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
public class MasteryLine {
    private List<MasteryItem> itens;

    public MasteryLine() {
    }

    public MasteryLine(List<MasteryItem> itens) {
        this.itens = itens;
    }
    

    public List<MasteryItem> getItens() {
        return itens;
    }

    public void setItens(List<MasteryItem> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "MasteryLine{" + "itens=" + itens + '}';
    }
    
    
}
