/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.entities.Rune;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 *
 * @author First Place
 */
public class RuneJSONParser {

    public static List<Rune> parserFeed(String content) {
        List<Rune> runes = new ArrayList<Rune>();

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();

        long runeId = root.getJsonNumber("id").longValue();
        String name = root.getString("name");
        String description = root.getString("description");
        String image = root.getJsonObject("image").getString("full");
        JsonObject statsJson = root.getJsonObject("stats");
        String[] v = getRuneVector(statsJson);
        String type = v[0];
        double stats=0.0;
        try {
            stats = Double.parseDouble(v[1]);
            Rune rune = new Rune(runeId, name, description, image, type, stats);
            runes.add(rune);
        } catch (NumberFormatException ex) {
            String[] v2 = getRuneVectorWithMoreAtt(statsJson);
            type=v2[0];
            stats=Double.parseDouble(v2[1]);
            Rune rune = new Rune(runeId, name, description, image, type, stats);
            runes.add(rune);
            type=v2[2];
            stats=Double.parseDouble(v2[3]);
            Rune rune2 = new Rune(runeId, name, description, image, type, stats);
            runes.add(rune2);
            
        }


        return runes;
    }

    public static String[] getRuneVector(JsonObject obj) {
        String[] valueString = obj.toString().split(":");
        valueString[0] = valueString[0].replace("{\"", "");
        valueString[0] = valueString[0].replace("\"", "");
        valueString[1] = valueString[1].replace("}", "");

        return valueString;
    }
    public static String[] getRuneVectorWithMoreAtt(JsonObject obj){
        String[] valueString = obj.toString().split(",");
        String[] valueStringAux= valueString[0].split(":");
        valueStringAux[0] = valueStringAux[0].replace("{\"", "");
        valueStringAux[0] = valueStringAux[0].replace("\"", "");
        valueStringAux[1] = valueStringAux[1].replace("}", "");
        
        String[] valueStringAux2= valueString[1].split(":");
        valueStringAux2[0] = valueStringAux2[0].replace("{\"", "");
        valueStringAux2[0] = valueStringAux2[0].replace("\"", "");
        valueStringAux2[1] = valueStringAux2[1].replace("}", "");
        
        String[] v = {valueStringAux[0], valueStringAux[1], valueStringAux2[0], valueStringAux2[1]};
        for (String v1 : v) {
            System.out.println(v1);
        }
        
        return v;
    }
}
