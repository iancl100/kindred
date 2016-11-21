/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author First Place
 */
public class SpellUpdateBDJSONParser {
    public static Map<Long, String> parserFeed(String content) {
        Map<Long, String> spells =new HashMap<>();
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root=reader.readObject();
        reader.close(); 
        JsonObject data = root.getJsonObject("data");
        for (Map.Entry<String, JsonValue> entry: data.entrySet()){
           JsonObject spellData = (JsonObject) entry.getValue();
           Long spellId = spellData.getJsonNumber("id").longValue();
           spells.put(spellId, entry.getKey());
        }
        return spells;
    }
}
