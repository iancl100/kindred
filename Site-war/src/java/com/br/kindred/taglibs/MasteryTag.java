/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.taglibs;

import com.br.kindred.model.entities.Mastery;
import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author First Place
 */
public class MasteryTag extends SimpleTagSupport {

    List<Mastery> masteries = null;

    public void setMasteries(List<Mastery> masteries) {
        this.masteries = masteries;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        out.println("<ul class='nav nav-tabs'>\n");
        for (int i = 1; i <= masteries.size(); i++) {
            if (i != 1) {
                out.println("<li ><a data-toggle='tab' href='#mastery" + i + "'>" + masteries.get(i - 1).getName() + "</a></li>");
            } else {
                out.println("<li class='active'><a data-toggle='tab' href='#mastery1'>" + masteries.get(i - 1).getName() + "</a></li>");

            }
        }
        out.println("</ul>");
//        out.println("<section class='container inner'>");
        for (int i = 1; i <= masteries.size(); i++) {
            Mastery mastery = masteries.get(i - 1);
            if (i != 1) {
                out.println("<section id='mastery" + i + "' class='tab-pane fade inner'>");
            } else {
                out.println("<section id=\"mastery1\" class=\"tab-pane fade in active inner\">");
            }
            out.println("<section class='masteries block-1'>");
            
            
            out.println("</section>");
            out.println("<section class='masteries block-2'>");
            
            
            out.println("</section>");
            out.println("<section class='masteries block-3'>");
            
            
            out.println("</section>");
            
            out.println("</section>");
        }
//        out.println("</section>");

    }

}
