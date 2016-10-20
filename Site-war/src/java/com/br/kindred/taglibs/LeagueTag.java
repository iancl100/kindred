/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.taglibs;

import com.br.kindred.json.LeagueJSONParser;
import com.br.kindred.json.OpenStream;
import com.br.kindred.model.entities.League;
import com.br.kindred.model.entities.LeagueLine;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author 31535811
 */
public class LeagueTag extends SimpleTagSupport {

    String summonerId = "";
    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
    String region = "";
    public void setRegion(String region) {
        this.region = region;
    }
    String styleMark="";
    public void setStyleMark(String styleMark){
        this.styleMark=styleMark;
    }
    String styleMarkAttribute="bg-danger";

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        String content = OpenStream.openURL("https://br.api.pvp.net/api/lol/" + region + "/v2.5/league/by-summoner/" + summonerId);
        if (content.equals("403")) {
            out.println("<h3>No summoner data found for any specified inputs</h3>");
        }
        League league=LeagueJSONParser.parserFeed(content, summonerId).get(0);
        
        
        out.println("<section id=\"show1\" class=\"tab-pane fade in active text-center\">");
        out.println("<section class=\"row bg-info text-center\" style='margin-bottom:15px;color:black;'>\n" +
"                            <section class=\"col-sm-1\"><b>Posição</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>Invocador</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Emblemas</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Jogos</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>Vitórias</b></section>\n" +
"                            <section class=\"col-sm-1\"><b>Derrotas</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>Série</b></section>\n" +
"                        </section>");
        boolean veri=false;
        for (int i=1;i<=50;i++) {
            LeagueLine line= league.getLines().get(i-1);
            if(line.getPlayerOrTeamId()==Long.parseLong(summonerId)){
                this.styleMark=styleMarkAttribute;
            }
            if(veri==true){
                out.println("<br><article class=\"row bg-info text-center\" style='margin-bottom:15px;color:black;'>" +
"                            <section class=\"col-sm-1\"><b>Posição</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>Invocador</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Emblemas</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Jogos</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>Vitórias</b></section>\n" +
"                            <section class=\"col-sm-1\"><b>Derrotas</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>Pontos</b></section>\n" +
"                        </article>");
            }
            if(line.getLeaguePoints()==100){
                out.println("<section class=\"row "+styleMark+" text-center\" style='margin-bottom:15px;'>"
                        + "<section class=\"col-sm-1\"><b>"+i+"</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>"+line.getPlayerOrTeamName()+"</b></section>\n" +
"                            <section class=\"col-sm-2\">"
                        + "<span class='glyphicon glyphicon-fire' style='color: "+setOpacity(line.isIsHotStreak(), 1)+";'></span>"
                        + " <span class='glyphicon glyphicon-star' style='color: "+setOpacity(line.isIsVeteran(), 2)+";'></span>"
                        + " <span class='glyphicon glyphicon-certificate' style='color: "+setOpacity(line.isIsFreshBlood(), 3)+";'></span>"
                        + "</section>\n" +
"                            <section class=\"col-sm-2\"><b>"+(line.getWins()+line.getLosses())+"</b> ("+((line.getWins()*100)/(line.getWins()+line.getLosses()))+"%)</section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getWins()+"</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getLosses()+"</b></section>\n" +
"                            <section class=\"col-sm-2\" >"+this.setMiniSerieSequence(line.getMiniSerie().getProgress())+"</section>"
                        + "</section>");
                if(league.getLines().get(i).getLeaguePoints()!=100)veri=true;
            }else{
                veri=false;
                out.println("<section class=\"row "+styleMark+" text-center\" style='margin-bottom:15px;'>"
                        + "<section class=\"col-sm-1\"><b>"+i+"</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>"+line.getPlayerOrTeamName()+"</b></section>\n" +
"                            <section class=\"col-sm-2\">"
                        + "<span class='glyphicon glyphicon-fire' style='color: "+setOpacity(line.isIsHotStreak(), 1)+";'></span>"
                        + " <span class='glyphicon glyphicon-star' style='color: "+setOpacity(line.isIsVeteran(), 2)+";'></span>"
                        + " <span class='glyphicon glyphicon-certificate' style='color: "+setOpacity(line.isIsFreshBlood(), 3)+";'></span>"
                        + "</section>\n" +
"                            <section class=\"col-sm-2\"><b>"+(line.getWins()+line.getLosses())+"</b> ("+((line.getWins()*100)/(line.getWins()+line.getLosses()))+"%)</section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getWins()+"</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getLosses()+"</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>"+line.getLeaguePoints()+"</b></section>"
                        + "</section>");
            }
            this.styleMark="";
        }
        out.println("</section>");
        
        
        out.println("<section id=\"show2\" class=\"tab-pane fade text-center\">");
        out.println("<br><article class=\"row bg-info text-center\" style='margin-bottom:15px;'>" +
"                            <section class=\"col-sm-1\"><b>Posição</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>Invocador</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Emblemas</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Jogos</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>Vitórias</b></section>\n" +
"                            <section class=\"col-sm-1\"><b>Derrotas</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>Pontos</b></section>\n" +
"                        </article>");
        for (int i=51;i<=100;i++) {
            LeagueLine line= league.getLines().get(i-1);
            if(line.getPlayerOrTeamId()==Long.parseLong(summonerId)){
                this.styleMark=styleMarkAttribute;
            }
            out.println("<section class=\"row "+styleMark+" text-center\" style='margin-bottom:15px;'>"
                        + "<section class=\"col-sm-1\"><b>"+i+"</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>"+line.getPlayerOrTeamName()+"</b></section>\n" +
"                            <section class=\"col-sm-2\">"
                        + "<span class='glyphicon glyphicon-fire' style='color: "+setOpacity(line.isIsHotStreak(), 1)+";'></span>"
                        + " <span class='glyphicon glyphicon-star' style='color: "+setOpacity(line.isIsVeteran(), 2)+";'></span>"
                        + " <span class='glyphicon glyphicon-certificate' style='color: "+setOpacity(line.isIsFreshBlood(), 3)+";'></span>"
                        + "</section>\n" +
"                            <section class=\"col-sm-2\"><b>"+(line.getWins()+line.getLosses())+"</b> ("+((line.getWins()*100)/(line.getWins()+line.getLosses()))+"%)</section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getWins()+"</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getLosses()+"</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>"+line.getLeaguePoints()+"</b></section>"
                        + "</section>");
            this.styleMark="";
        }
        out.println("</section>");
        
        
        out.println("<section id=\"show3\" class=\"tab-pane fade text-center\">");
        out.println("<br><article class=\"row bg-info text-center\" style='margin-bottom:15px;'>" +
"                            <section class=\"col-sm-1\"><b>Posição</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>Invocador</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Emblemas</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Jogos</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>Vitórias</b></section>\n" +
"                            <section class=\"col-sm-1\"><b>Derrotas</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>Pontos</b></section>\n" +
"                        </article>");
        for (int i=101;i<=150;i++) {
            LeagueLine line= league.getLines().get(i-1);
            if(line.getPlayerOrTeamId()==Long.parseLong(summonerId)){
                this.styleMark=styleMarkAttribute;
            }
            out.println("<section class=\"row "+styleMark+" text-center\" style='margin-bottom:15px;'>"
                        + "<section class=\"col-sm-1\"><b>"+i+"</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>"+line.getPlayerOrTeamName()+"</b></section>\n" +
"                            <section class=\"col-sm-2\">"
                        + "<span class='glyphicon glyphicon-fire' style='color: "+setOpacity(line.isIsHotStreak(), 1)+";'></span>"
                        + " <span class='glyphicon glyphicon-star' style='color: "+setOpacity(line.isIsVeteran(), 2)+";'></span>"
                        + " <span class='glyphicon glyphicon-certificate' style='color: "+setOpacity(line.isIsFreshBlood(), 3)+";'></span>"
                        + "</section>\n" +
"                            <section class=\"col-sm-2\"><b>"+(line.getWins()+line.getLosses())+"</b> ("+((line.getWins()*100)/(line.getWins()+line.getLosses()))+"%)</section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getWins()+"</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getLosses()+"</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>"+line.getLeaguePoints()+"</b></section>"
                        + "</section>");
            this.styleMark="";
        }
        out.println("</section>");
        
        
        out.println("<section id=\"show4\" class=\"tab-pane fade text-center\">");
        out.println("<br><article class=\"row bg-info text-center\" style='margin-bottom:15px;'>" +
"                            <section class=\"col-sm-1\"><b>Posição</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>Invocador</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Emblemas</b></section>\n" +
"                            <section class=\"col-sm-2\"><b>Jogos</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>Vitórias</b></section>\n" +
"                            <section class=\"col-sm-1\"><b>Derrotas</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>Pontos</b></section>\n" +
"                        </article>");
        for (int i=151;i<=200;i++) {
            LeagueLine line= league.getLines().get(i-1);
            if(line.getPlayerOrTeamId()==Long.parseLong(summonerId)){
                this.styleMark=styleMarkAttribute;
            }
            out.println("<section class=\"row "+styleMark+" text-center\" style='margin-bottom:15px;'>"
                        + "<section class=\"col-sm-1\"><b>"+i+"</b></section>\n" +
"                            <section class=\"col-sm-3\"><b>"+line.getPlayerOrTeamName()+"</b></section>\n" +
"                            <section class=\"col-sm-2\">"
                        + "<span class='glyphicon glyphicon-fire' style='color: "+setOpacity(line.isIsHotStreak(), 1)+";'></span>"
                        + " <span class='glyphicon glyphicon-star' style='color: "+setOpacity(line.isIsVeteran(), 2)+";'></span>"
                        + " <span class='glyphicon glyphicon-certificate' style='color: "+setOpacity(line.isIsFreshBlood(), 3)+";'></span>"
                        + "</section>\n" +
"                            <section class=\"col-sm-2\"><b>"+(line.getWins()+line.getLosses())+"</b> ("+((line.getWins()*100)/(line.getWins()+line.getLosses()))+"%)</section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getWins()+"</b></section>\n" +
"                            <section class=\"col-sm-1\" ><b>"+line.getLosses()+"</b></section>\n" +
"                            <section class=\"col-sm-2\" ><b>"+line.getLeaguePoints()+"</b></section>"
                        + "</section>");
            this.styleMark="";
        }
        out.println("</section>");
    }
    
    public String setOpacity(boolean veri, int op){
        if(veri==false){
            return "gray";
        }else if(op==1){
            return "red";
        }else if(op==2){
            return "yellow";
        }else{
            return "lightblue";
        }
    }
    public String setMiniSerieSequence(String senquence){
        char[] c = senquence.toCharArray();
        String w ="<span class='glyphicon glyphicon-ok' style='color:lightgreen'></span>";
        String l ="<span class='glyphicon glyphicon-remove' style='color:red'></span>";
        String n ="<span class='glyphicon glyphicon-minus' style='color:lightgray'></span>";
        senquence="";
        for (char letter : c) {
            if(letter=='W'){
                senquence+=w;
            }else if(letter=='L'){
                senquence+=l;
            }else{
                senquence+=n;
            }
        }
        return senquence;
    }

}
