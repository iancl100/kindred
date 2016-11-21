/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.controller.command;

import com.br.kindred.json.ChampionsUpdateBDJSONParser;
import com.br.kindred.json.LeagueJSONParser;
import com.br.kindred.json.MasteryJSONParser;
import com.br.kindred.json.MatchListJSONParser;
import com.br.kindred.json.OpenStream;
import com.br.kindred.json.PagesJSONParser;
import com.br.kindred.json.SummonerJSONParser;
import com.br.kindred.model.dao.MasteryItemDAO;
import com.br.kindred.model.entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 31535811
 */
public class SummonerCommand implements Command {

    MasteryItemDAO masteryItemDAO = lookupMasteryItemDAOBean();

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String responsePage;

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        String action = request.getParameter("command").split("\\.")[1];
        String summonerName = request.getParameter("summonerName");
        String region = request.getParameter("region");

        switch (action) {
            case "buscar":
                if (summonerName.contains(" ")) {
                    summonerName = summonerName.replaceAll(" ", "%20");
                }
                String uriSummoner = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + summonerName + "?";
                String contentSummoner = OpenStream.openURL(uriSummoner);
                if (this.errorTreatment(contentSummoner))break;
                
                Summoner summoner = SummonerJSONParser.parserFeed(contentSummoner, summonerName).get(0);
                
                String uriLeague = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v2.5/league/by-summoner/" + summoner.getIdSummoner() + "/entry?";
                String contentLeague = OpenStream.openURL(uriLeague);
                if (this.errorTreatment(contentLeague)){
                    summoner.setTier("UNRANKED");
                    summoner.setDivision("");
                }else{
                    League league = LeagueJSONParser.parserFeed(contentLeague, String.valueOf(summoner.getIdSummoner())).get(0);

                    if (league.getQueue().equals("RANKED_SOLO_5x5")) {
                        summoner.setTier(league.getTier());
                        summoner.setDivision(league.getLines().get(0).getDivision());
                    } else {
                        summoner.setTier("UNRANKED");
                        summoner.setDivision("");
                    }
                }
                
                
                
//                String uriMastery= "https://"+region+".api.pvp.net/api/lol/"+region+"/v1.4/summoner/"+summoner.getIdSummoner()+"/masteries?";
//                String contentMastery = OpenStream.openURL(uriMastery);
//                if(this.errorTreatment(contentMastery))break;              
//                summoner.setMasteries(setMasteryUp(MasteryJSONParser.parserFeed(contentMastery, String.valueOf(summoner.getIdSummoner()))));
                
                String uriMatchList="https://"+region+".api.pvp.net/api/lol/"+region+"/v1.3/game/by-summoner/"+summoner.getIdSummoner()+"/recent?";
                String matchListContent=OpenStream.openURL(uriMatchList);
                if (this.errorTreatment(matchListContent))break;
                summoner.setMatchList(MatchListJSONParser.parserFeed(matchListContent));
                
                
                request.getSession().setAttribute("summoner", summoner);
                request.getSession().setAttribute("region", region);
                responsePage = "historico.jsp";
                break;
            case "buscarRunas":
                summoner = (Summoner)request.getSession().getAttribute("summoner");
                region = (String)request.getSession().getAttribute("region");
                String uriRunes = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/" + summoner.getIdSummoner() + "/runes?";
                String contentRunes = OpenStream.openURL(uriRunes);
                if (this.errorTreatment(contentRunes))break;               
                summoner.setPages(PagesJSONParser.parserFeed(contentRunes, String.valueOf(summoner.getIdSummoner())));
                request.getSession().setAttribute("summoner", summoner);
                request.getSession().setAttribute("region", region);
                responsePage = "runas.jsp";
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }

    public boolean errorTreatment(String codeString) {
        int code;
        try{
           code = Integer.parseInt(codeString);
        }catch(NumberFormatException ex){
            code=200;
        }
        switch (code) {
            case 200:
                return false;
            case 401:
                request.getSession().setAttribute("error", "Unauthorized");
                break;
            case 403:
                request.getSession().setAttribute("error", "No summoner data found for any specified inputs");
                break;
            case 404:
                request.getSession().setAttribute("error", "Not found");
                break;
            case 429:
                request.getSession().setAttribute("error", "Rate limit exceeded");
                break;
            case 500:
                request.getSession().setAttribute("error", "Internal server error");
                break;
            case 503:
                request.getSession().setAttribute("error", "Service unavailable");
                break;
            case 400:
                request.getSession().setAttribute("error", "Bad request");
                break;
            default:
                break;
        }
        responsePage = "error.jsp";
        return true;
    }
    public List<Mastery> setMasteryUp(Map<String, List<MasteryItem>> mapMastery){
        List<Mastery> masteries = new ArrayList<>();
        
        for (Map.Entry<String, List<MasteryItem>> entry: mapMastery.entrySet()) {
        Mastery m = new Mastery();
        Ferocity f = new Ferocity();
        Cunning c = new Cunning();
        Resolve r = new Resolve();
            m.setName(entry.getKey());
            for (MasteryItem  mi: entry.getValue()) {
                for (MasteryItem miReal : f.getLines()) {
                    if(mi.getIdMasteryid()==miReal.getIdMasteryid()){
                        miReal.setRank(mi.getRank());
                    }
                }
                for (MasteryItem miReal : c.getLines()) {
                    if(mi.getIdMasteryid()==miReal.getIdMasteryid()){
                        miReal.setRank(mi.getRank());
                    }
                }
                for (MasteryItem miReal : r.getLines()) {
                    if(mi.getIdMasteryid()==miReal.getIdMasteryid()){
                        miReal.setRank(mi.getRank());
                    }
                }
            }
            m.setFerocity(f);
            m.setCunning(c);
            m.setResolve(r);
            masteries.add(m);
        }
        return masteries;
    }

    private MasteryItemDAO lookupMasteryItemDAOBean() {
        try {
            Context c = new InitialContext();
            return (MasteryItemDAO) c.lookup("java:global/Site/Site-ejb/MasteryItemDAO!com.br.kindred.model.dao.MasteryItemDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    

}
