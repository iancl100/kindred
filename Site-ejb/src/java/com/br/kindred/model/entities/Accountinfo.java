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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author First Place
 */
@Entity
@Table(name = "ACCOUNTINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accountinfo.findAll", query = "SELECT a FROM Accountinfo a"),
    @NamedQuery(name = "Accountinfo.findByIdAccountinfo", query = "SELECT a FROM Accountinfo a WHERE a.idAccountinfo = :idAccountinfo"),
    @NamedQuery(name = "Accountinfo.findByEmail", query = "SELECT a FROM Accountinfo a WHERE a.email = :email")})
public class Accountinfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACCOUNTINFO")
    private Long idAccountinfo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccountinfo")
    private List<Summoner> summonerList;
    @JoinColumn(name = "ID_ACCOUNTINFO", referencedColumnName = "ID_ACCOUNT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Account account;

    public Accountinfo() {
    }

    public Accountinfo(Long idAccountinfo) {
        this.idAccountinfo = idAccountinfo;
    }

    public Accountinfo(Long idAccountinfo, String email) {
        this.idAccountinfo = idAccountinfo;
        this.email = email;
    }

    public Long getIdAccountinfo() {
        return idAccountinfo;
    }

    public void setIdAccountinfo(Long idAccountinfo) {
        this.idAccountinfo = idAccountinfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Summoner> getSummonerList() {
        return summonerList;
    }

    public void setSummonerList(List<Summoner> summonerList) {
        this.summonerList = summonerList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccountinfo != null ? idAccountinfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accountinfo)) {
            return false;
        }
        Accountinfo other = (Accountinfo) object;
        if ((this.idAccountinfo == null && other.idAccountinfo != null) || (this.idAccountinfo != null && !this.idAccountinfo.equals(other.idAccountinfo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.Accountinfo[ idAccountinfo=" + idAccountinfo + " ]";
    }
    
}
