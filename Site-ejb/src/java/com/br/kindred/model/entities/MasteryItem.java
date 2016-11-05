/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 31535811
 */
@Entity
@Table(name = "MasteryItem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasteryItem.findAll", query = "SELECT m FROM MasteryItem m"),
    @NamedQuery(name = "MasteryItem.findByIdMasteryid", query = "SELECT m FROM MasteryItem m WHERE m.idMasteryid = :idMasteryid"),
    @NamedQuery(name = "MasteryItem.findByMasteryname", query = "SELECT m FROM MasteryItem m WHERE m.masteryname = :masteryname"),
    @NamedQuery(name = "MasteryItem.findByMasterytree", query = "SELECT m FROM MasteryItem m WHERE m.masterytree = :masterytree")})
public class MasteryItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MASTERYID")
    private Long idMasteryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MASTERYNAME")
    private String masteryname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MASTERYTREE")
    private String masterytree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkMasteryItem")
    private List<DescriptionItem> DescriptionItemList;
    private int rank;

    public MasteryItem() {
    }

    public MasteryItem(Long idMasteryid) {
        this.idMasteryid = idMasteryid;
    }

    public MasteryItem(Long idMasteryid, String masteryname, String masterytree) {
        this.idMasteryid = idMasteryid;
        this.masteryname = masteryname;
        this.masterytree = masterytree;
        this.rank=1;
    }
    

    public Long getIdMasteryid() {
        return idMasteryid;
    }

    public void setIdMasteryid(Long idMasteryid) {
        this.idMasteryid = idMasteryid;
    }

    public String getMasteryname() {
        return masteryname;
    }

    public void setMasteryname(String masteryname) {
        this.masteryname = masteryname;
    }

    public String getMasterytree() {
        return masterytree;
    }

    public void setMasterytree(String masterytree) {
        this.masterytree = masterytree;
    }

    @XmlTransient
    public List<DescriptionItem> getDescriptionItemList() {
        return DescriptionItemList;
    }

    public void setDescriptionItemList(List<DescriptionItem> DescriptionItemList) {
        this.DescriptionItemList = DescriptionItemList;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMasteryid != null ? idMasteryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasteryItem)) {
            return false;
        }
        MasteryItem other = (MasteryItem) object;
        if ((this.idMasteryid == null && other.idMasteryid != null) || (this.idMasteryid != null && !this.idMasteryid.equals(other.idMasteryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.MasteryItem[ idMasteryid=" + idMasteryid + " ]";
    }
    
}
