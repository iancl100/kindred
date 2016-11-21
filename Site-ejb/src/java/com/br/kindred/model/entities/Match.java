/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;


import com.br.kindred.model.dao.ChampionDAO;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author First Place
 */
public class Match {

    ChampionDAO championDAO = lookupChampionDAOBean();
    private long matchId;
    private Champion champ;
    private String queue;
    private Map<Long, String> participants = new HashMap<>();
    private int spell1Id, spell2Id;
    private int assists, deaths, kills, champLevel, gold, minions, matchDuration;
    private boolean winner;

    public Match() {
    }

    public Match(long matchId, String queue, int spell1Id, int spell2Id, int assists, int deaths, int kills, int champLevel, int gold, int minions, int matchDuration, boolean winner) {
        this.matchId = matchId;;
        this.queue = queue;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
        this.champLevel = champLevel;
        this.gold = gold;
        this.minions = minions;
        this.matchDuration = matchDuration;
        this.winner = winner;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Map<Long, String> getParticipants() {
        return participants;
    }

    public void setParticipants(Map<Long, String> participants) {
        this.participants = participants;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public void setChampLevel(int champLevel) {
        this.champLevel = champLevel;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Champion getChamp() {
        return champ;
    }

    public void setChamp(Long id_champ) {
        try{
            this.champ = championDAO.readById(id_champ);
        }catch(Exception ex){
            this.champ=null;
        }
    }

    public int getMinions() {
        return minions;
    }

    public void setMinions(int minions) {
        this.minions = minions;
    }

    public int getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration;
    }

    @Override
    public String toString() {
        return "Match{" + "matchId=" + matchId + ", champ=" + champ + ", queue=" + queue + ", participants=" + participants + ", spell1Id=" + spell1Id + ", spell2Id=" + spell2Id + ", assists=" + assists + ", deaths=" + deaths + ", kills=" + kills + ", champLevel=" + champLevel + ", gold=" + gold + ", minions=" + minions + ", matchDuration=" + matchDuration + ", winner=" + winner + '}';
    }

    private ChampionDAO lookupChampionDAOBean() {
        try {
            Context c = new InitialContext();
            return (ChampionDAO) c.lookup("java:global/Site/Site-ejb/ChampionDAO!com.br.kindred.model.dao.ChampionDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    

    
    
    

    
    
    
}
