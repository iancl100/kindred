/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.controller.api;

import com.br.kindred.model.entities.Summoner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 31535811
 */
public class riotAPI {
    private final String key="?api_key=3c323e05-a26a-4f3c-a56e-7b38430d1ce1";
    private String url="https://br.api.pvp.net/api/lol/br/v1.4/summoner/by-name/";
    private String summonerName;
    private String response;

    public riotAPI(String summonerName){
        this.summonerName = summonerName;
        if(summonerName.contains(" ")){
            summonerName=summonerName.replaceAll(" ", "%20");
        }       
        try {
            URL link = new URL(url+summonerName+key);
            InputStreamReader isr = new InputStreamReader(link.openStream());
            BufferedReader br = new BufferedReader(isr);
            response=br.readLine();
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(riotAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(riotAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    
    
    
    
}
