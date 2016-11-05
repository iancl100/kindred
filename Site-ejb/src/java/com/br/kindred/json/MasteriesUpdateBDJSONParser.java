/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.dao.MasteryItemDAO;
import com.br.kindred.model.entities.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author 31535811
 */
public class MasteriesUpdateBDJSONParser {
    public static List<MasteryItem> parserFeed(String content) {
        List<MasteryItem> itens = new ArrayList<>();
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();
        
        List<Long> ids= new ArrayList<>();
        JsonObject trees = root.getJsonObject("tree");
        
        JsonArray fero = trees.getJsonArray("Ferocity");
        for (int i = 0; i < fero.size(); i++) {
            JsonObject obj = fero.getJsonObject(i);
            JsonArray masteryTree= obj.getJsonArray("masteryTreeItems");
            for (int j = 0; j < masteryTree.size(); j++) {
                if(masteryTree.get(j)!=null){
                    System.out.println(masteryTree.getJsonObject(j));
                    JsonObject masteryTreeObj = masteryTree.getJsonObject(j);
                    Long id = masteryTreeObj.getJsonNumber("masteryId").longValue();
                    ids.add(id);
                }
            }
        }
        JsonArray cunn = trees.getJsonArray("Cunning");
        for (int i = 0; i < cunn.size(); i++) {
            JsonObject obj = cunn.getJsonObject(i);
            JsonArray masteryTree= obj.getJsonArray("masteryTreeItems");
            for (int j = 0; j < masteryTree.size(); j++) {
                if(masteryTree.get(j).getValueType().equals("OBJECT")){
                    JsonObject masteryTreeObj = masteryTree.getJsonObject(j);
                    Long id = masteryTreeObj.getJsonNumber("MasteryId").longValue();
                    ids.add(id);
                }
            }
        }
        JsonArray reso = trees.getJsonArray("Resolve");
        for (int i = 0; i < reso.size(); i++) {
            JsonObject obj = reso.getJsonObject(i);
            JsonArray masteryTree= obj.getJsonArray("masteryTreeItems");
            for (int j = 0; j < masteryTree.size(); j++) {
                if(masteryTree.get(j).getValueType().equals("OBJECT")){
                    JsonObject masteryTreeObj = masteryTree.getJsonObject(j);
                    Long id = masteryTreeObj.getJsonNumber("MasteryId").longValue();
                    ids.add(id);
                }
            }
        }
        
        JsonObject data = root.getJsonObject("data");
        
        for (Long id: ids) {
            System.out.println(id);
        }
        for (Long id : ids) {
            JsonObject masteryItemObj = data.getJsonObject(String.valueOf(id));
            String masteryName = masteryItemObj.getString("name");
            String masteryTree = masteryItemObj.getString("MasteryTree");
            JsonArray descriptionItemJson = masteryItemObj.getJsonArray("description");
            List<DescriptionItem> descriptions = new ArrayList<>();
            for (int i = 0; i < descriptionItemJson.size(); i++) {
                String text = descriptionItemJson.getString(i);
                descriptions.add(new DescriptionItem(text));
            }
            MasteryItem mi = new MasteryItem(id, masteryName, masteryTree);
            mi.setDescriptionItemList(descriptions);
            itens.add(mi);
        }
        
        return itens;
        
    }
    
    
}
