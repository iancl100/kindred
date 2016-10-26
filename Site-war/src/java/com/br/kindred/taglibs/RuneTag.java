/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.kindred.taglibs;

import com.br.kindred.model.entities.Page;
import com.br.kindred.model.entities.Rune;
import com.br.kindred.model.entities.Slot;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author 31535811
 */
public class RuneTag extends SimpleTagSupport {

    String summonerId = "";

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
    List<Page> pages = null;

    public void setpages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        out.println("<ul class='nav nav-tabs'>\n");
        for (int i = 1; i <= pages.size(); i++) {
            if (i != 1) {
                out.println("<li class='runePage' ><a data-toggle='tab' href='#rune" + i + "'>" + pages.get(i - 1).getName() + "</a></li>");
            } else {
                out.println("<li class='runePage active'><a data-toggle='tab' href='#rune1'>" + pages.get(i - 1).getName() + "</a></li>");

            }
        }
        out.println("</ul>");

        for (int i = 1; i <= pages.size(); i++) {
            Page page = pages.get(i - 1);
            if (i != 1) {
                out.println("<section id='rune" + i + "' class='tab-pane fade'>");
            } else {
                out.println("<section id=\"rune1\" class=\"tab-pane fade in active\">");
            }
            out.println("<section class=\"runes-disposal\" style=\"margin:10px;\">\n");
            System.out.println(page.getSlots().size());
            if (page.getSlots().size()!=0) {
                for (int j = 1; j <= page.getSlots().size(); j++) {
                    Slot slot = page.getSlots().get(j - 1);
                    if (slot.getRuneSlotId() < 28) {
                        out.println("<img data-toggle='tooltip' title='" + slot.getRune().getName() + " " + slot.getRune().getDescription() + "' class='rune rune-" + slot.getRuneSlotId() + "' src='http://ddragon.leagueoflegends.com/cdn/6.21.1/img/rune/" + slot.getRune().getImage() + "'><span></span></img>");
                    } else {
                        out.println("<span data-toggle='tooltip' title='" + slot.getRune().getName() + "         "
                                + "" + slot.getRune().getDescription() + "' class='rune-quint rune-" + slot.getRuneSlotId() + "'style='background: url(http://ddragon.leagueoflegends.com/cdn/6.21.1/img/rune/" + slot.getRune().getImage() + ") 50% / 90% no-repeat;' ></span>");
                    }
                }
            }
            out.println("</section>\n");
            out.println("<section class='runes-total text-left'>\n");
            this.separeteTypesOfRunes(page, out);
            out.println("</section>");
            out.println("</section>");

        }
    }

    public void separeteTypesOfRunes(Page page, JspWriter out) throws IOException {
        List<Rune> runesMark = new ArrayList<>();
        List<Rune> runesSeals = new ArrayList<>();
        List<Rune> runesGlyphs = new ArrayList<>();
        List<Rune> runesQuint = new ArrayList<>();
        for (int j = 0; j < page.getSlots().size(); j++) {
            if (j < 9) {
                runesMark.add(page.getSlots().get(j).getRune());
            } else if (j >= 9 && j < 18) {
                runesSeals.add(page.getSlots().get(j).getRune());
            } else if (j >= 18 && j < 27) {
                runesGlyphs.add(page.getSlots().get(j).getRune());
            } else {
                runesQuint.add(page.getSlots().get(j).getRune());
            }
        }

        Collections.sort(runesMark, new Rune());
        Collections.sort(runesSeals, new Rune());
        Collections.sort(runesGlyphs, new Rune());
        Collections.sort(runesQuint, new Rune());

        DecimalFormat df = new DecimalFormat("#0.000");

        Rune runeAux = runesMark.get(0);
        double stats = runesMark.get(0).getStats();
        out.println("<b style='color: #dd5656; margin-top:10px;'>MARKS</b><br>");
        for (int i = 1; i < runesMark.size(); i++) {
            Rune rune = runesMark.get(i);
            if (rune.getRuneId() != runeAux.getRuneId() || i == runesMark.size() - 1) {
                if (i == runesMark.size() - 1) {
                    stats += rune.getStats();
                }
                out.println("<p style='margin-left:10px;'>+ " + df.format(stats * runeAux.getStatsByType()) + " " + runeAux.getSpecificType() + "</p>");
                runeAux = rune;
                stats = runeAux.getStats();
            } else {
                stats += runeAux.getStats();
            }
        }
        runeAux = runesSeals.get(0);
        stats = runesSeals.get(0).getStats();
        out.println("<b style='color: ORANGE;'>SEALS</b>");
        for (int i = 1; i < runesSeals.size(); i++) {
            Rune rune = runesSeals.get(i);
            if (rune.getRuneId() != runeAux.getRuneId() || i == runesSeals.size() - 1) {
                if (i == runesSeals.size() - 1) {
                    stats += rune.getStats();
                }
                out.println("<p style='margin-left:10px;'>+ " + df.format(stats * runeAux.getStatsByType()) + " " + runeAux.getSpecificType() + "</p>");
                runeAux = rune;
                stats = runeAux.getStats();
            } else {
                stats += runeAux.getStats();
            }
        }
        runeAux = runesGlyphs.get(0);
        stats = runesGlyphs.get(0).getStats();
        out.println("<b style='color: #5496d3;'>GLYPHS</b>");
        for (int i = 1; i < runesGlyphs.size(); i++) {
            Rune rune = runesGlyphs.get(i);
            if (rune.getRuneId() != runeAux.getRuneId() || i == runesGlyphs.size() - 1) {
                if (i == runesGlyphs.size() - 1) {
                    stats += rune.getStats();
                }
                out.println("<p style='margin-left:10px;'>+ " + df.format(stats * runeAux.getStatsByType()) + " " + runeAux.getSpecificType() + "</p>");
                runeAux = rune;
                stats = runeAux.getStats();
            } else {
                stats += runeAux.getStats();
            }
        }
        runeAux = runesQuint.get(0);
        stats = runesQuint.get(0).getStats();
        out.println("<b style='color: #a05ce0;'>QUINTESSENCES</b>");
        for (int i = 1; i < runesQuint.size(); i++) {
            Rune rune = runesQuint.get(i);
            if (rune.getRuneId() != runeAux.getRuneId() || i == runesQuint.size() - 1) {
                if (i == runesQuint.size() - 1) {
                    stats += rune.getStats();
                }
                out.println("<p style='margin-left:10px;'>+ " + df.format(stats * runeAux.getStatsByType()) + " " + runeAux.getSpecificType() + "</p>");
                runeAux = rune;
                stats = runeAux.getStats();
            } else {
                stats += runeAux.getStats();
            }
        }
    }

}
