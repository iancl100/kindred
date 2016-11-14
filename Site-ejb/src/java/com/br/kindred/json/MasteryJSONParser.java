/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.entities.MasteryItem;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.*;

/**
 *
 * @author 31535811
 */
public class MasteryJSONParser {
    public static Map<String, List<MasteryItem>> parserFeed(String content, String idSummoner) {
        List<MasteryItem> itens = new ArrayList<>();
        Map<String, List<MasteryItem>> mapMastery= new HashMap<String, List<MasteryItem>>();
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        JsonObject summonerPageData= root.getJsonObject(idSummoner);
        JsonArray pagesJson = summonerPageData.getJsonArray("pages");
        for (int i = 0; i < pagesJson.size(); i++) {
            JsonArray masteryPages = pagesJson.getJsonObject(i).getJsonArray("masteries");
            for (int j = 0; j < masteryPages.size(); j++) {
                JsonObject masteryItemObj = masteryPages.getJsonObject(j);
                long id = masteryItemObj.getJsonNumber("id").longValue();
                int rank = masteryItemObj.getInt("rank");
                MasteryItem mi = new MasteryItem();
                mi.setIdMasteryid(id);
                mi.setRank(rank);
                itens.add(mi);
            }
            String namePage = pagesJson.getJsonObject(i).getString("name");
            mapMastery.put(namePage, itens);
        }
        
        return mapMastery;
    }
    
}
