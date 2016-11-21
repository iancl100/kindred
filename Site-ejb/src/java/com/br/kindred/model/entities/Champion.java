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
@Table(name = "CHAMPION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Champion.findAll", query = "SELECT c FROM Champion c"),
    @NamedQuery(name = "Champion.findByIdChamp", query = "SELECT c FROM Champion c WHERE c.idChamp = :idChamp"),
    @NamedQuery(name = "Champion.findByChampname", query = "SELECT c FROM Champion c WHERE c.champname = :champname")})
public class Champion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CHAMP")
    private Long idChamp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CHAMPNAME")
    private String champname;

    public Champion() {
    }

    public Champion(Long idChamp) {
        this.idChamp = idChamp;
    }

    public Champion(Long idChamp, String champname) {
        this.idChamp = idChamp;
        this.champname = champname;
    }

    public Long getIdChamp() {
        return idChamp;
    }

    public void setIdChamp(Long idChamp) {
        this.idChamp = idChamp;
    }

    public String getChampname() {
        return champname;
    }

    public void setChampname(String champname) {
        this.champname = champname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChamp != null ? idChamp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Champion)) {
            return false;
        }
        Champion other = (Champion) object;
        if ((this.idChamp == null && other.idChamp != null) || (this.idChamp != null && !this.idChamp.equals(other.idChamp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.Champion[ idChamp=" + idChamp + " ]";
    }
    
}
