<%-- 
    Document   : painel
    Created on : 22/08/2016, 08:50:50
    Author     : 31535811
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="r" uri="/WEB-INF/tlds/rune.tld"%>
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
        <link href="css/runas.css" rel="stylesheet" type="text/css"/>
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
                <section style="margin:7%;">
                    <section class="row"><h2 class="text-left">Páginas de Runas</h2></section>

                    <article class="tab-content">
                        <r:rune pages="${summoner.pages}" summonerId="${summoner.idSummoner}"/>
                    </article>



                </section>
            </section>
        </section>

    </section><%@include file="inc/footer.jspf" %>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</body>
</html>
