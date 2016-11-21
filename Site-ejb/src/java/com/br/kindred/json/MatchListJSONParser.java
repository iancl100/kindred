/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.entities.Match;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.*;

/**
 *
 * @author First Place
 */
public class MatchListJSONParser {
    public static List<Match> parserFeed(String content) {
        
        List<Match> matchList= new ArrayList<>();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root=reader.readObject();
        reader.close();
        
        JsonArray games = root.getJsonArray("games");
        for (int i = 0; i < games.size(); i++) {
            JsonObject game = games.getJsonObject(i);
            JsonObject stats = game.getJsonObject("stats");
            int assists;
            int kills;
            int deaths;
            try{
                assists = stats.getInt("assists");
            }catch(Exception ex){assists=0;}
            try{
                deaths = stats.getInt("numDeaths");
            }catch(Exception ex){deaths=0;}
            try{
                kills = stats.getInt("championsKilled");
            }catch(Exception ex){kills=0;}
            int champLevel = stats.getInt("level");
            int goldEarned=stats.getInt("goldEarned");
            int playerPosition;
            int playerRole;
            try{
               playerPosition=stats.getInt("playerPosition");
               playerRole =stats.getInt("playerRole");
            }catch(Exception ex){
                playerPosition=0;
                playerRole=0;
            }
            int minionsKilled = stats.getInt("minionsKilled");
            try{
                minionsKilled+=stats.getInt("neutralMinionsKilled");
            }catch(Exception ex){}//As vezes um jogador não mata monstros da selva
            boolean winner = stats.getBoolean("win");
            int matchDuration = stats.getInt("timePlayed");
            
            long matchId=game.getJsonNumber("gameId").longValue();
            long spell1Id = game.getJsonNumber("spell1").longValue();
            long spell2Id = game.getJsonNumber("spell2").longValue();
            Long champId= game.getJsonNumber("championId").longValue();
            String gameType=game.getString("gameType");
            String gameMode = game.getString("gameMode");
            String subType = game.getString("subType");
            
            Map<Long, Long> participants = new HashMap<>();
            JsonArray fellowPlayers = game.getJsonArray("fellowPlayers");
            for (int j = 0; j < fellowPlayers.size(); j++) {
                JsonObject fellowPlayer = fellowPlayers.getJsonObject(j);
                participants.put(fellowPlayer.getJsonNumber("summonerId").longValue(),
                        fellowPlayer.getJsonNumber("championId").longValue());
            }
            
            Match match = new Match(matchId, spell1Id, spell2Id, assists, deaths, kills, champLevel, goldEarned, minionsKilled, winner);
            match.setChamp(champId);
            match.setGameMode(gameMode);
            match.setGameType(gameType);
            match.setParticipants(participants);
            match.setMatchDuration(matchDuration);
            match.setSubType(subType);
            match.setPlayerPosition(playerPosition);
            match.setPlayerRole(playerRole);
            
            matchList.add(match);
        }
//        
        
        return matchList;
    }
}
