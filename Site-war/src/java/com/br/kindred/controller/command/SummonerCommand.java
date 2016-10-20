/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.controller.command;

import com.br.kindred.json.LeagueJSONParser;
import com.br.kindred.json.OpenStream;
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
public class SummonerCommand implements Command{
    
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
                String summonerName= request.getParameter("summonerName");
                String region= request.getParameter("region");
                if(summonerName.contains(" ")){
                    summonerName=summonerName.replaceAll(" ", "%20");
                }
                String uri ="https://br.api.pvp.net/api/lol/"+region+"/v1.4/summoner/by-name/"+summonerName;
                String content = OpenStream.openURL(uri);
                if(content.equals("403")){
                    request.getSession().setAttribute("error", "No summoner data found for any specified inputs");
                    responsePage="error.jsp";
                    break;
                }
                Summoner summoner = SummonerJSONParser.parserFeed(content, summonerName).get(0);
                String uri2="https://br.api.pvp.net/api/lol/"+region+"/v2.5/league/by-summoner/"+summoner.getIdSummoner()+"/entry";
                String content2= OpenStream.openURL(uri2);
                League league=LeagueJSONParser.parserFeed(content2, String.valueOf(summoner.getIdSummoner())).get(0);
                summoner.setTier(league.getTier());
                summoner.setDivision(league.getLines().get(0).getDivision());
                request.getSession().setAttribute("summoner", summoner);
                request.getSession().setAttribute("region", region);
                responsePage="historico.jsp";
                break;
        }
    }

    @Override
    public String getResponsePage() {
        return this.responsePage;
    }

   
}
