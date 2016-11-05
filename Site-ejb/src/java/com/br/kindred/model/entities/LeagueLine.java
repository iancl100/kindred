/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import java.util.Comparator;

/**
 *
 * @author 31535811
 */
public class LeagueLine implements Comparator<LeagueLine>{
    private Integer leaguePoints, losses,wins;
    private String playerOrTeamId;
    private String division, playerOrTeamName;
    private boolean isFreshBlood, isHotStreak, isInactive, isVeteran;
    private MiniSeries miniSerie;

    public LeagueLine() {
    }

    public LeagueLine(int leaguePoints, int losses, int wins, String playerOrTeamId, String division, String playerOrTeamName, boolean isFreshBlood, boolean isHotStreak, boolean isInactive, boolean isVeteran) {
        this.leaguePoints = leaguePoints;
        this.losses = losses;
        this.wins = wins;
        this.playerOrTeamId = playerOrTeamId;
        this.division = division;
        this.playerOrTeamName = playerOrTeamName;
        this.isFreshBlood = isFreshBlood;
        this.isHotStreak = isHotStreak;
        this.isInactive = isInactive;
        this.isVeteran = isVeteran;
        this.miniSerie=null;
    }
    

    public LeagueLine(int leaguePoints, int losses, int wins, String playerOrTeamId, String division, String playerOrTeamName, boolean isFreshBlood, boolean isHotStreak, boolean isInactive, boolean isVeteran, MiniSeries miniSerie) {
        this.leaguePoints = leaguePoints;
        this.losses = losses;
        this.wins = wins;
        this.playerOrTeamId = playerOrTeamId;
        this.division = division;
        this.playerOrTeamName = playerOrTeamName;
        this.isFreshBlood = isFreshBlood;
        this.isHotStreak = isHotStreak;
        this.isInactive = isInactive;
        this.isVeteran = isVeteran;
        this.miniSerie = miniSerie;
    }

    

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    public boolean isIsFreshBlood() {
        return isFreshBlood;
    }

    public void setIsFreshBlood(boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    public boolean isIsHotStreak() {
        return isHotStreak;
    }

    public void setIsHotStreak(boolean isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    public boolean isIsInactive() {
        return isInactive;
    }

    public void setIsInactive(boolean isInactive) {
        this.isInactive = isInactive;
    }

    public boolean isIsVeteran() {
        return isVeteran;
    }

    public void setIsVeteran(boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    public MiniSeries getMiniSerie() {
        return miniSerie;
    }

    public void setMiniSerie(MiniSeries miniSerie) {
        this.miniSerie = miniSerie;
    }

    @Override
    public String toString() {
        return "LeagueLine{" + "leaguePoints=" + leaguePoints + ", losses=" + losses + ", wins=" + wins + ", playerOrTeamId=" + playerOrTeamId + ", division=" + division + ", playerOrTeamName=" + playerOrTeamName + ", isFreshBlood=" + isFreshBlood + ", isHotStreak=" + isHotStreak + ", isInactive=" + isInactive + ", isVeteran=" + isVeteran + ", miniSerie=" + miniSerie + '}';
    }

    

    @Override
    public int compare(LeagueLine o1, LeagueLine o2) {
        return o1.leaguePoints.compareTo(o2.leaguePoints);
    }

    
    
    
    
}
