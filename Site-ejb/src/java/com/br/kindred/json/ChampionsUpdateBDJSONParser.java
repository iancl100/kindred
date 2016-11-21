/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author First Place
 */
public class ChampionsUpdateBDJSONParser {
   public static Map<Long, String> parserFeed(String content) {
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root=reader.readObject();
        reader.close(); 
        
        Map<Long, String> keys = new HashMap();
        
        JsonObject keysJson = root.getJsonObject("keys");
        for (Map.Entry<String, JsonValue> entry: keysJson.entrySet()){
            keys.put(Long.parseLong(entry.getKey()), entry.getValue().toString().replaceAll("\"", ""));
        }
        
        return keys;
   }
}
