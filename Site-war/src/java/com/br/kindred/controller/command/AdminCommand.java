/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.controller.command;

import com.br.kindred.json.MasteriesUpdateBDJSONParser;
import com.br.kindred.json.OpenStream;
import com.br.kindred.model.dao.MasteryItemDAO;
import com.br.kindred.model.entities.MasteryItem;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author First Place
 */
public class AdminCommand implements Command {
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
        
        switch (action) {
            case "updateMasteries":
               String uri = "https://br.api.pvp.net/api/lol/static-data/br/v1.2/mastery?masteryListData=all&";
               String content = OpenStream.openURL(uri);
               if(errorTreatment(content))break;
               
               List<MasteryItem> itens = MasteriesUpdateBDJSONParser.parserFeed(content);
                for (MasteryItem item : itens) {
                    try{
                        masteryItemDAO.update(item);
                    }catch(Exception ex){
                        masteryItemDAO.create(item);
                    }
                }
                responsePage = "talentos.jsp";
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
