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
    public static List<Match> parserFeed(String region, String summonerId) {
        String content = OpenStream.openURL("https://"+region+".api.pvp.net/api/lol/"+region+"/v2.2/matchlist/by-summoner/"+summonerId+"?");
        List<Match> matchList= new ArrayList<>();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root=reader.readObject();
        reader.close();
        JsonArray matchesRefer = root.getJsonArray("matches");
        for (int i = 0; i < 5; i++) {
            JsonObject matchRefer = matchesRefer.getJsonObject(i);
            long matchId = matchRefer.getJsonNumber("matchId").longValue();
            content = OpenStream.openURL("https://br.api.pvp.net/api/lol/br/v2.2/match/"+matchId+"?");
            reader = Json.createReader(new StringReader(content));
            JsonObject matchRoot = reader.readObject();
            reader.close();
            int matchDuration = matchRoot.getInt("matchDuration");
            String queue = matchRoot.getString("queueType");
            Map<Long, String> participants = new HashMap();
            long participantId=1;
            JsonArray partIdsJson = matchRoot.getJsonArray("participantIdentities");
            for (int j = 0; j < partIdsJson.size(); j++) {
                JsonObject partIdJson = partIdsJson.getJsonObject(j);
                JsonObject player = partIdJson.getJsonObject("player");
                long partSummonerId = player.getJsonNumber("summonerId").longValue();
                if(partSummonerId==Long.parseLong(summonerId)){
                    participantId=partIdJson.getJsonNumber("participantId").longValue();
                }
                String summonerName = player.getString("summonerName");
                participants.put(partSummonerId, summonerName);
            }
            JsonArray partsJson = matchRoot.getJsonArray("participants");
            int index = ((int) (participantId))-1;
            JsonObject partJson = partsJson.getJsonObject(index);
            JsonObject stats = partJson.getJsonObject("stats");
            int spell1Id = partJson.getInt("spell1Id");
            int spell2Id = partJson.getInt("spell2Id");
            Long championId = partJson.getJsonNumber("championId").longValue();
            int assists = stats.getInt("assists");
            int deaths = stats.getInt("deaths");
            int kills = stats.getInt("kills");
            int champLevel = stats.getInt("champLevel");
            int gold = stats.getInt("goldEarned");
            int minions = stats.getInt("minionsKilled");
            boolean winner = stats.getBoolean("winner");
            
            Match match = new Match(matchId, queue, spell1Id, spell2Id, assists, deaths, kills, champLevel, gold, minions, matchDuration, winner);
            match.setChamp(championId);
            match.setParticipants(participants);
            matchList.add(match);
        }
//        for (Match match : matchList) {
//            System.out.println(match);
//        }
        
        return matchList;
    }
}
