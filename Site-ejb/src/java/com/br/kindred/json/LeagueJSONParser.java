/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.entities.League;
import com.br.kindred.model.entities.LeagueLine;
import com.br.kindred.model.entities.MiniSeries;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 *
 * @author 31535811
 */
public class LeagueJSONParser {

    public static List<League> parserFeed(String content, String summonerId) {
        List<League> leagues = new ArrayList<League>();

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        //Criação do League
        JsonArray leaguesJson = root.getJsonArray(summonerId);
        JsonObject leagueJson = leaguesJson.getJsonObject(0);
        String queue = leagueJson.getString("queue");
        String name = leagueJson.getString("name");
        String tier = leagueJson.getString("tier");

        //Busca das linhas da league
        List<LeagueLine> leagueLines = new ArrayList<LeagueLine>();
        JsonArray entries = leagueJson.getJsonArray("entries");
        for (int i = 0; i < entries.size(); i++) {
            JsonObject entry = entries.getJsonObject(i);

            int leaguePoints = entry.getInt("leaguePoints"); 
            int losses = entry.getInt("losses"); 
            int wins = entry.getInt("wins");
            String playerOrTeamId = entry.getString("playerOrTeamId");
            String division = entry.getString("division");
            String playerOrTeamName = entry.getString("playerOrTeamName");
            boolean isFreshBlood = entry.getBoolean("isFreshBlood"); 
            boolean isHotStreak = entry.getBoolean("isHotStreak");
            boolean isInactive = entry.getBoolean("isInactive"); 
            boolean isVeteran = entry.getBoolean("isVeteran");
            LeagueLine leagueLine=null;
            
            JsonObject miniSerieObj = entry.getJsonObject("miniSeries");
            if(miniSerieObj==null){
                leagueLine = new LeagueLine(leaguePoints, losses, wins, playerOrTeamId, division, playerOrTeamName, isFreshBlood, isHotStreak, isInactive, isVeteran);
            }
            else{
                int target = miniSerieObj.getInt("target");
                int winsInMini = miniSerieObj.getInt("wins");
                int lossesInMini = miniSerieObj.getInt("losses");
                String progress = miniSerieObj.getString("progress");
                //Verificando miniseries
                MiniSeries miniSerie=new MiniSeries(lossesInMini, target, winsInMini, progress);
                leagueLine = new LeagueLine(leaguePoints, losses, wins, playerOrTeamId, division, playerOrTeamName, isFreshBlood, isHotStreak, isInactive, isVeteran, miniSerie);
                
            }
            leagueLines.add(leagueLine);
        }
        League league = new League(queue, name, tier, Long.parseLong(summonerId), leagueLines);
        leagues.add(league);

        return leagues;
    }
}
