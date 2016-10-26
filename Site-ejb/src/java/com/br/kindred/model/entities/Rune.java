/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.util.Comparator;

/**
 *
 * @author First Place
 */
public class Rune implements Comparator<Rune> {

    private long runeId;
    private String name, description, image, type;
    private double stats;

    public Rune() {
    }

    public Rune(long runeId, String name, String description, String image, String type, double stats) {
        this.runeId = runeId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.type = type;
        this.stats = stats;
    }

    public long getRuneId() {
        return runeId;
    }

    public void setRuneId(long runeId) {
        this.runeId = runeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getStats() {
        return stats;
    }

    public int getStatsByType() {
        String type=this.type;
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

    public void setStats(double stats) {
        this.stats = stats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecificType() {
        String type = this.type;
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
    public String toString() {
        return "Rune{" + "runeId=" + runeId + ", name=" + name + ", description=" + description + ", image=" + image + ", type=" + type + ", stats=" + stats + '}';
    }

    @Override
    public int compare(Rune r1, Rune r2) {
        Integer r1Id = Integer.parseInt(String.valueOf(r1.runeId));
        Integer r2Id = Integer.parseInt(String.valueOf(r2.runeId));
        return r1Id.compareTo(r2Id);
    }

}
