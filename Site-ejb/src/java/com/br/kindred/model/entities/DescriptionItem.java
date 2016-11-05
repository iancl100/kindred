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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 31535811
 */
@Entity
@Table(name = "DescriptionItem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescriptionItem.findAll", query = "SELECT d FROM DescriptionItem d"),
    @NamedQuery(name = "DescriptionItem.findByIdDescriptionItem", query = "SELECT d FROM DescriptionItem d WHERE d.idDescriptionItem = :idDescriptionItem"),
    @NamedQuery(name = "DescriptionItem.findByText", query = "SELECT d FROM DescriptionItem d WHERE d.text = :text")})
public class DescriptionItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DescriptionItem")
    private Long idDescriptionItem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "TEXT")
    private String text;
    @JoinColumn(name = "FK_MasteryItem", referencedColumnName = "ID_MASTERYID")
    @ManyToOne(optional = false)
    private MasteryItem fkMasteryItem;

    public DescriptionItem() {
    }

    public DescriptionItem(Long idDescriptionItem) {
        this.idDescriptionItem = idDescriptionItem;
    }

    public DescriptionItem( String text) {
        this.text = text;
    }

    public Long getIdDescriptionItem() {
        return idDescriptionItem;
    }

    public void setIdDescriptionItem(Long idDescriptionItem) {
        this.idDescriptionItem = idDescriptionItem;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MasteryItem getFkMasteryItem() {
        return fkMasteryItem;
    }

    public void setFkMasteryItem(MasteryItem fkMasteryItem) {
        this.fkMasteryItem = fkMasteryItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescriptionItem != null ? idDescriptionItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescriptionItem)) {
            return false;
        }
        DescriptionItem other = (DescriptionItem) object;
        if ((this.idDescriptionItem == null && other.idDescriptionItem != null) || (this.idDescriptionItem != null && !this.idDescriptionItem.equals(other.idDescriptionItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.DescriptionItem[ idDescriptionItem=" + idDescriptionItem + " ]";
    }
    
}
