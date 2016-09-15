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
 * @author First Place
 */
@Entity
@Table(name = "SUMMONER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Summoner.findAll", query = "SELECT s FROM Summoner s"),
    @NamedQuery(name = "Summoner.findByIdSummoner", query = "SELECT s FROM Summoner s WHERE s.idSummoner = :idSummoner"),
    @NamedQuery(name = "Summoner.findBySummonername", query = "SELECT s FROM Summoner s WHERE s.summonername = :summonername"),
    @NamedQuery(name = "Summoner.findBySummonerlevel", query = "SELECT s FROM Summoner s WHERE s.summonerlevel = :summonerlevel"),
    @NamedQuery(name = "Summoner.findByProfileicon", query = "SELECT s FROM Summoner s WHERE s.profileicon = :profileicon")})
public class Summoner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SUMMONER")
    private Long idSummoner;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "SUMMONERNAME")
    private String summonername;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUMMONERLEVEL")
    private int summonerlevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROFILEICON")
    private long profileicon;
    @JoinColumn(name = "ID_ACCOUNTINFO", referencedColumnName = "ID_ACCOUNTINFO")
    @ManyToOne(optional = false)
    private Accountinfo idAccountinfo;

    public Summoner() {
    }

    public Summoner(Long idSummoner) {
        this.idSummoner = idSummoner;
    }

    public Summoner(Long idSummoner, String summonername, int summonerlevel, long profileicon) {
        this.idSummoner = idSummoner;
        this.summonername = summonername;
        this.summonerlevel = summonerlevel;
        this.profileicon = profileicon;
    }

    public Long getIdSummoner() {
        return idSummoner;
    }

    public void setIdSummoner(Long idSummoner) {
        this.idSummoner = idSummoner;
    }

    public String getSummonername() {
        return summonername;
    }

    public void setSummonername(String summonername) {
        this.summonername = summonername;
    }

    public int getSummonerlevel() {
        return summonerlevel;
    }

    public void setSummonerlevel(int summonerlevel) {
        this.summonerlevel = summonerlevel;
    }

    public long getProfileicon() {
        return profileicon;
    }

    public void setProfileicon(long profileicon) {
        this.profileicon = profileicon;
    }

    public Accountinfo getIdAccountinfo() {
        return idAccountinfo;
    }

    public void setIdAccountinfo(Accountinfo idAccountinfo) {
        this.idAccountinfo = idAccountinfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSummoner != null ? idSummoner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Summoner)) {
            return false;
        }
        Summoner other = (Summoner) object;
        if ((this.idSummoner == null && other.idSummoner != null) || (this.idSummoner != null && !this.idSummoner.equals(other.idSummoner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.Summoner[ idSummoner=" + idSummoner + " ]";
    }
    
}
