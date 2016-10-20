/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.entities.Summoner;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 *
 * @author First Place
 */
public class SummonerJSONParser {
    public static List<Summoner> parserFeed(String content, String summonerBusca){
        List<Summoner> summoners = new ArrayList<>();
        
        JsonReader reader= Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        JsonObject summonerJson = root.getJsonObject(correctName(summonerBusca));
        long id=summonerJson.getJsonNumber("id").longValue();
        String name=summonerJson.getString("name");
        long profileIcon=summonerJson.getJsonNumber("profileIconId").longValue();
        int summonerLevel=summonerJson.getInt("summonerLevel");
        
        Summoner summoner = new Summoner(id, name, summonerLevel, profileIcon);
        summoners.add(summoner);
        
        return summoners;
    }
    
    public static String correctName(String name){
        String correctName = name.toLowerCase();
        correctName =  correctName.replaceAll("%20", "");
        return correctName;
    }
    
    
        
    
}
