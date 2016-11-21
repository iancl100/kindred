<%-- 
    Document   : painel
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
        <form action="Controller" method="POST" class="form-inline">
            <input type="hidden" name="command" value="Summoner.buscar">
            <p class="lead">
            <section class="form-group" >
                <select class="form-control input-lg summonerSelect" name="region">
                    <option value="br">BR</option>
                    <option value="kr">KR</option>
                    <option value="na">NA</option>
                    <option value="eune">EUNE</option>
                    <option value="euw">EUW</option>
                    <option value="jp">JP</option>
                    <option value="lan">LAN</option>
                    <option value="las">LAS</option>
                    <option value="oce">OCE</option>
                    <option value="ru">RU</option>
                    <option value="tr">TR</option>
                </select>
            </section>
            <section class="form-group ">
                <input  type="text" placeholder="Summoner's name" name="summonerName" style="min-width: 300px" class="form-control input-lg summonerInput" required/>
            </section>
            <button type="submit" class="btn  btn-primary btn-lg summonerButton">Buscar</button>
        </p>
    </form>
    <section class="container-fluid">
        <section class="container inner">
            <section class="row inner jumbotron table-responsive" id="summonerHeader">
                <section class="col-sm-3" style="padding-left: 0;text-align: left;"><img src="http://ddragon.leagueoflegends.com/cdn/6.17.1/img/profileicon/${summoner.profileicon}.png" class="img-rounded"></section>
                <section class="col-sm-9 text-left">
                    <h3>${summoner.summonername}</h3>
                    <h5>${summoner.getTierTransleted()} ${summoner.division}</h5>
                </section>
            </section>
            <%@include file="inc/painelNavbar.jspf" %>
            <section class="container-fluid">
                <section class="tab-content" style="margin:7%;">
                    <section class="row"><h2 class="text-left">Partidas Recente</h2></section>
                    <section id="show1" class="tab-pane fade in active">
                        <c:forEach items="${summoner.matchList}" var="match">
                                <c:if test="${match.winner==true}">
                                    <section class="row bg-success" style="text-align: justify;">
                                </c:if>
                                <c:if test="${match.winner==false}">
                                    <section class="row bg-danger" style="text-align: justify;">
                                </c:if>
                                    <section class="col-sm-2" style="padding-left:0;padding-right: 0;text-align: left;"><img  src="http://ddragon.leagueoflegends.com/cdn/6.22.1/img/champion/${match.champion.champname}.png"></section>
                                    <section class="col-sm-2" style="border:5px solid black;">Lorem ipsum dolor </section>
                                    <section class="col-sm-6" style="border:5px solid black;">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean lacinia ex odio, a dignissim sapien</section>
                                    <section class="col-sm-2" style="border:5px solid black;">Lorem ipsum dolor sit amet, </section>
                                </section>
                                </c:forEach>
                            </section>
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#show1">1</a></li>
                                <li><a data-toggle="tab" href="#show2">2</a></li>
                                <li><a data-toggle="tab" href="#show3">3</a></li>
                                <li><a data-toggle="tab" href="#show4">4</a></li>
                            </ul>
                        </section>
                    </section>
                </section>

            </section><%@include file="inc/footer.jspf" %>
            <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
            <script src="js/bootstrap.min.js" type="text/javascript"></script>
        </body>
    </html>
