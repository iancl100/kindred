/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.controller.command;

import com.br.kindred.json.LeagueJSONParser;
import com.br.kindred.json.OpenStream;
import com.br.kindred.json.PagesJSONParser;
import com.br.kindred.json.SummonerJSONParser;
import com.br.kindred.model.entities.League;
import com.br.kindred.model.entities.Summoner;
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

        switch (action) {
            case "buscar":
                String summonerName = request.getParameter("summonerName");
                String region = request.getParameter("region");
                if (summonerName.contains(" ")) {
                    summonerName = summonerName.replaceAll(" ", "%20");
                }
                String uriSummoner = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/by-name/" + summonerName + "?";
                String contentSummoner = OpenStream.openURL(uriSummoner);
                if (this.errorTreatment(contentSummoner))break;
                
                Summoner summoner = SummonerJSONParser.parserFeed(contentSummoner, summonerName).get(0);

                String uriLeague = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v2.5/league/by-summoner/" + summoner.getIdSummoner() + "/entry?";
                String contentLeague = OpenStream.openURL(uriLeague);
                System.out.println(contentLeague);
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
                

                String uriRunes = "https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/" + summoner.getIdSummoner() + "/runes?";
                String contentRunes = OpenStream.openURL(uriRunes);
                if (this.errorTreatment(contentRunes))break;
                
                summoner.setPages(PagesJSONParser.parserFeed(contentRunes, String.valueOf(summoner.getIdSummoner())));

                request.getSession().setAttribute("summoner", summoner);
                request.getSession().setAttribute("region", region);
                responsePage = "historico.jsp";
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

}
