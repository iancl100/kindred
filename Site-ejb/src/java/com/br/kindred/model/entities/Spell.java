/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author First Place
 */
@Entity
@Table(name = "SPELL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spell.findAll", query = "SELECT s FROM Spell s"),
    @NamedQuery(name = "Spell.findBySpellid", query = "SELECT s FROM Spell s WHERE s.spellid = :spellid"),
    @NamedQuery(name = "Spell.findBySpellname", query = "SELECT s FROM Spell s WHERE s.spellname = :spellname")})
public class Spell implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SPELLID")
    private Long spellid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SPELLNAME")
    private String spellname;

    public Spell() {
    }

    public Spell(Long spellid) {
        this.spellid = spellid;
    }

    public Spell(Long spellid, String spellname) {
        this.spellid = spellid;
        this.spellname = spellname;
    }

    public Long getSpellid() {
        return spellid;
    }

    public void setSpellid(Long spellid) {
        this.spellid = spellid;
    }

    public String getSpellname() {
        return spellname;
    }

    public void setSpellname(String spellname) {
        this.spellname = spellname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spellid != null ? spellid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spell)) {
            return false;
        }
        Spell other = (Spell) object;
        if ((this.spellid == null && other.spellid != null) || (this.spellid != null && !this.spellid.equals(other.spellid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.Spell[ spellid=" + spellid + " ]";
    }
    
}
