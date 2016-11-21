/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.io.Serializable;
import java.util.Comparator;
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
@Table(name = "RUNE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rune.findAll", query = "SELECT r FROM Rune r"),
    @NamedQuery(name = "Rune.findByIdRune", query = "SELECT r FROM Rune r WHERE r.idRune = :idRune"),
    @NamedQuery(name = "Rune.findByDescription", query = "SELECT r FROM Rune r WHERE r.description = :description"),
    @NamedQuery(name = "Rune.findByNome", query = "SELECT r FROM Rune r WHERE r.nome = :nome"),
    @NamedQuery(name = "Rune.findByImage", query = "SELECT r FROM Rune r WHERE r.image = :image"),
    @NamedQuery(name = "Rune.findByTipo", query = "SELECT r FROM Rune r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Rune.findByStats", query = "SELECT r FROM Rune r WHERE r.stats = :stats")})
public class Rune implements Serializable, Comparator<Rune> {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RUNE")
    private Long idRune;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 100)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 20)
    @Column(name = "IMAGE")
    private String image;
    @Size(max = 30)
    @Column(name = "TIPO")
    private String tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "STATS")
    private Double stats;

    public Rune() {
    }

    public Rune(Long idRune, String description, String nome, String image, String tipo, Double stats) {
        this.idRune = idRune;
        this.description = description;
        this.nome = nome;
        this.image = image;
        this.tipo = tipo;
        this.stats = stats;
    }
    

    public Rune(Long idRune) {
        this.idRune = idRune;
    }

    public Long getIdRune() {
        return idRune;
    }

    public void setIdRune(Long idRune) {
        this.idRune = idRune;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getStats() {
        return stats;
    }

    public void setStats(Double stats) {
        this.stats = stats;
    }
    public int getStatsByType() {
        String type=this.tipo;
        if (type.contains("PerLevel")) {
            if (type.contains("Flat")) {
                return 18;
            } else {
                return 18*100;
            }
        } else {
            if (type.contains("Percent")) {
                return 100;
            }else{
                return 1;
            }
        }
    }
    public String getSpecificType() {
        String type = this.tipo;
        type = type.replace("rFlat", "");
        type = type.replace("Flat", "");
        type = type.replace("rPercent", "% ");
        type = type.replace("Percent", "% ");
        type = type.replace("Mod", "");
        type = type.replace("PerLevel", " at level 18");
        String aux="";
        if(type.contains("MP")){
            type=type.replace("MP", "");
            aux="MP ";
        }
        if(type.contains("HP")){
            type=type.replace("HP", "");
            aux="HP ";
        }
        String[] vetor = type.split("(?=\\p{Upper})");
        type="";
        for (String word : vetor) {
            type+=word+" ";
        }
        if(type.contains("Spell Block")){
            return type.replace("Spell Block", "Magic Resist");
        }
        return aux+type;
    }
    @Override
    public int compare(Rune r1, Rune r2) {
        Integer r1Id = Integer.parseInt(String.valueOf(r1.idRune));
        Integer r2Id = Integer.parseInt(String.valueOf(r2.idRune));
        return r1Id.compareTo(r2Id);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRune != null ? idRune.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rune)) {
            return false;
        }
        Rune other = (Rune) object;
        if ((this.idRune == null && other.idRune != null) || (this.idRune != null && !this.idRune.equals(other.idRune))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.kindred.model.entities.Rune[ idRune=" + idRune + " ]";
    }
    
}
