/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.json;

import com.br.kindred.model.entities.Page;
import com.br.kindred.model.entities.Rune;
import com.br.kindred.model.entities.Slot;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

/**
 *
 * @author 31535811
 */
public class PagesJSONParser {

    public static List<Page> parserFeed(String content, String summonerId) {
        List<Page> runes = new ArrayList<Page>();

        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject root = reader.readObject();
        reader.close();

        JsonObject info = root.getJsonObject(summonerId);
        JsonArray pages = info.getJsonArray("pages");

        List<Rune> runesTemp = new ArrayList<>();
        for (int i = 0; i < pages.size(); i++) {
            JsonObject page = pages.getJsonObject(i);

            long runeId = page.getJsonNumber("id").longValue();
            List<Slot> slots = new ArrayList<Slot>();
            JsonArray slotsJson = page.getJsonArray("slots");
            if(slotsJson!=null){
            Rune runeAux = null;
            for (int j = 0; j < slotsJson.size(); j++) {
                JsonObject slotJson = slotsJson.getJsonObject(j);

                int runeSlotId = slotJson.getInt("runeSlotId");
                long runeIdSlot = slotJson.getJsonNumber("runeId").longValue();
                boolean veri = true;
                for (Rune rune : runesTemp) {
                    if (runeIdSlot == rune.getIdRune()) {
                        runeAux = rune;
                        veri = false;
                        break;
                    }
                }
                if (veri == true) {
                    String uri = "https://br.api.pvp.net/api/lol/static-data/br/v1.2/rune/" + runeIdSlot + "?runeData=all&";
                    String cont = OpenStream.openURL(uri);
                    Rune runeTemp = RuneJSONParser.parserFeed(cont).get(0);
                    runesTemp.add(runeTemp);
                    runeAux = runeTemp;
                }
//                Rune rune=null;
//                if (slotIdPast != slotId) {
//                    String uri = "https://br.api.pvp.net/api/lol/static-data/br/v1.2/rune/" + slotId + "?runeData=all&";
//                    String cont = OpenStream.openURL(uri);
//                    rune = RuneJSONParser.parserFeed(cont).get(0);
//                    
//                    slotIdPast=slotId;
//                    runeAux=rune;
//                    
//                } else {
//                    rune=runeAux;
//                }

                Slot slot = new Slot(runeIdSlot, runeSlotId, runeAux);
                slots.add(slot);
            }
            }
            String name = page.getString("name");
            boolean current = page.getBoolean("current");

            Page rune = new Page(runeId, name, current, slots);
            runes.add(rune);
        }

        return runes;
    }
}
