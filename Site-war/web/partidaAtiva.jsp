<%-- 
    Document   : partidaAtiva
    Created on : 22/08/2016, 08:50:50
    Author     : 31535811
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Summoner's painel</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/cover.css" rel="stylesheet" type="text/css"/>
        <link href="css/historico.css" rel="stylesheet" type="text/css"/>
        <link href="css/partivaAtiva.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:choose>
        <c:when test="${account == null}"> <%--User -> Objeto que representa o usuário --%>
            <%@include  file="inc/navbarWithLogo.jspf" %>
        </c:when>
        <c:otherwise >
            <%@include file="inc/navbarWithLogoAuthenticated.jspf" %>
        </c:otherwise>
        </c:choose>
        <section class="container-fluid">
            <section class="container inner">
                <section class="row inner jumbotron table-responsive" id="summonerHeader">
                    <section class="col-sm-3" style="padding-left: 0;text-align: left;"><img src="http://ddragon.leagueoflegends.com/cdn/6.17.1/img/profileicon/1111.png" class="img-rounded"></section>
                    <section class="col-sm-9 text-left">
                        <h3>Leoka Mechanics</h3>
                        <h5>Diamante IV</h5>
                    </section>
                </section>
                <%@include file="inc/painelNavbar.jspf" %>
                <section class="container-fluid">
                    <section class="tab-content" style="margin:7%;">
                        <section class="row"><h4 class="text-left">Partida Ativa</h4></section>
                        <section class="row" id="partidaNaoAtiva">
                            
                        </section>
                       
                    </section>
                </section>
            </section>

        </section><%@include file="inc/footer.jspf" %>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
