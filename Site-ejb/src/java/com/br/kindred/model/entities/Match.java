/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.model.entities;

import com.br.kindred.model.dao.ChampionDAO;
import com.br.kindred.model.dao.SpellDAO;
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

    SpellDAO spellDAO = lookupSpellDAOBean();

    ChampionDAO championDAO = lookupChampionDAOBean();
    private long matchId;
    private Champion champ;
    private String gameType, gameMode, subType;
    private String playerPosition, playerRole;
    private Map<Long, Champion> participants = new HashMap<>();
    private long spell1Id, spell2Id;
    private int assists, deaths, kills, champLevel, gold, minions;
    private String matchDuration;
    private boolean winner;

    public Match() {
    }

    public Match(long matchId, long spell1Id, long spell2Id, int assists, int deaths, int kills, int champLevel, int gold, int minions, boolean winner) {
        this.matchId = matchId;
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
        this.champLevel = champLevel;
        this.gold = gold;
        this.minions = minions;
        this.winner = winner;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public Map<Long, Champion> getParticipants() {
        return participants;
    }

    public void setParticipants(Map<Long, Long> participants) {
        for (Map.Entry<Long, Long> entry : participants.entrySet()) {
            Champion champ = championDAO.readById(entry.getValue());
            this.participants.put(entry.getKey(), champ);
        }
    }

    public Spell getSpell1Id() {
        return spellDAO.readById((this.spell1Id));
    }

    public void setSpell1Id(long spell1Id) {
        this.spell1Id = spell1Id;
    }

    public Spell getSpell2Id() {
        return spellDAO.readById((this.spell2Id));
    }

    public void setSpell2Id(long spell2Id) {
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
        try {
            this.champ = championDAO.readById(id_champ);
        } catch (Exception ex) {
            this.champ = null;
        }
    }

    public int getMinions() {
        return minions;
    }

    public void setMinions(int minions) {
        this.minions = minions;
    }

    public String getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration / 60 + " minutos e" + matchDuration % 60 + " segundos";
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        Map<String, String> types = new HashMap<>();
        types.put("NONE", "None");
        types.put("NORMAL", "Partida Normal");
        types.put("BOT", "Partida Normal");
        types.put("RANKED_SOLO_5x5", "Partida Ranqueada");
        types.put("RANKED_PREMADE_3x3", "Partida Ranqueada 3x3");
        types.put("RANKED_PREMADE_5x5", "Partida Ranqueada");
        types.put("ODIN_UNRANKED", "POdin");
        types.put("RANKED_TEAM_3x3", "Time Ranqueado 3x3");
        types.put("RANKED_TEAM_5x5", "Time Ranqueado 5x5");
        types.put("NORMAL_3x3", "Partida Normal 3x3");
        types.put("BOT_3x3", "Partida Normal 3x3");
        types.put("CAP_5x5", "Cap");
        types.put("ARAM_UNRANKED_5x5", "ARAM");
        types.put("ONEFORALL_5x5", "One for ALL");
        types.put("FIRSTBLOOD_1x1", "FirstBlood");
        types.put("FIRSTBLOOD_2x2", "FirstBlood");
        types.put("SR_6x6", "SR");
        types.put("URF", "URF");
        types.put("URF_BOT", "URF BOT");
        types.put("NIGHTMARE_BOT", "Nightmare Bots");
        types.put("ASCENSION", "Ascension");
        types.put("HEXAKILL", "Hexakill");
        types.put("KING_PORO", "King poro");
        types.put("COUNTER_PICK", "Counter Pick");
        types.put("BILGEWATER", "Bilgewater");
        types.put("SIEGE", "Siege");
        types.put("RANKED_FLEX_SR", "Partida Ranqueada");
        types.put("RANKED_FLEX_TT", "Partida Ranqueada");
        try {
            this.subType = types.get(subType);
        } catch (Exception ex) {
            this.subType = null;
        }

    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        Map<Integer, String> types = new HashMap<>();
        types.put(1, "TOP");
        types.put(2, "MIDDLE");
        types.put(3, "JUNGLE");
        types.put(4, "BOT");
        try {
            this.playerPosition = types.get(playerPosition);
        } catch (Exception ex) {
            this.playerPosition = null;
        }
    }

    public String getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(int playerRole) {
        switch (playerRole) {
            case 3:
                this.playerRole="CARRY";
                break;
            case 2:
                this.playerRole="SUPPORT";
                break;
            default:
                this.playerRole="SOLO";
                break;
        }
    }

    @Override
    public String toString() {
        return "Match{" + "championDAO=" + championDAO + ", matchId=" + matchId + ", champ=" + champ + ", gameType=" + gameType + ", gameMode=" + gameMode + ", subType=" + subType + ", playerPosition=" + playerPosition + ", playerRole=" + playerRole + ", participants=" + participants + ", spell1Id=" + spell1Id + ", spell2Id=" + spell2Id + ", assists=" + assists + ", deaths=" + deaths + ", kills=" + kills + ", champLevel=" + champLevel + ", gold=" + gold + ", minions=" + minions + ", matchDuration=" + matchDuration + ", winner=" + winner + '}';
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

    private SpellDAO lookupSpellDAOBean() {
        try {
            Context c = new InitialContext();
            return (SpellDAO) c.lookup("java:global/Site/Site-ejb/SpellDAO!com.br.kindred.model.dao.SpellDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    

}
