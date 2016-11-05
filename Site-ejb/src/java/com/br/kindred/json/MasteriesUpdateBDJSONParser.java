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

        List<Long> ids = new ArrayList<>();
        JsonObject trees = root.getJsonObject("tree");

        JsonArray fero = trees.getJsonArray("Ferocity");
        for (int i = 0; i < fero.size(); i++) {
            JsonObject obj = fero.getJsonObject(i);
            JsonArray masteryTree = obj.getJsonArray("masteryTreeItems");
            for (int j = 0; j < masteryTree.size(); j++) {
                try {
                    JsonObject masteryTreeObj = masteryTree.getJsonObject(j);
                    Long id = masteryTreeObj.getJsonNumber("masteryId").longValue();
                    ids.add(id);
                } catch (Exception e) {
                }
            }
        }
        JsonArray cunn = trees.getJsonArray("Cunning");
        for (int i = 0; i < cunn.size(); i++) {
            JsonObject obj = cunn.getJsonObject(i);
            JsonArray masteryTree = obj.getJsonArray("masteryTreeItems");
            for (int j = 0; j < masteryTree.size(); j++) {
                try {
                    JsonObject masteryTreeObj = masteryTree.getJsonObject(j);
                    Long id = masteryTreeObj.getJsonNumber("masteryId").longValue();
                    ids.add(id);
                } catch (Exception e) {
                }
            }
        }
        JsonArray reso = trees.getJsonArray("Resolve");
        for (int i = 0; i < reso.size(); i++) {
            JsonObject obj = reso.getJsonObject(i);
            JsonArray masteryTree = obj.getJsonArray("masteryTreeItems");
            for (int j = 0; j < masteryTree.size(); j++) {
                try {
                    JsonObject masteryTreeObj = masteryTree.getJsonObject(j);
                    Long id = masteryTreeObj.getJsonNumber("masteryId").longValue();
                    ids.add(id);
                } catch (Exception e) {
                }
            }
        }

        JsonObject data = root.getJsonObject("data");

        
        for (Long id : ids) {
            JsonObject masteryItemObj = data.getJsonObject(String.valueOf(id));
            String masteryName = masteryItemObj.getString("name");
            String masteryTree = masteryItemObj.getString("masteryTree");
            JsonArray descriptionItemJson = masteryItemObj.getJsonArray("description");
            int rank = masteryItemObj.getInt("ranks");
            List<DescriptionItem> descriptions = new ArrayList<>();
            MasteryItem mi = new MasteryItem(id, masteryName, masteryTree, rank);
            for (int i = 0; i < descriptionItemJson.size(); i++) {
                String text = descriptionItemJson.getString(i);
                DescriptionItem di = new DescriptionItem(text);
                di.setFkMasteryitem(mi);
                descriptions.add(di);
            }
            mi.setDescriptionitemList(descriptions);
            itens.add(mi);
        }
        return itens;

    }

}
