<%-- 
    Document   : home2
    Created on : 19/08/2016, 21:32:45
    Author     : First Place
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/cover.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <c:choose>
            <c:when test="${account==null}"> <%--User -> Objeto que representa o usuário --%>
                <%@include  file="inc/navbarWithoutLogo.jspf" %>
            </c:when>
            <c:otherwise >
                <%@include file="inc/navbarWithoutLogoAuthenticated.jspf" %>
            </c:otherwise>
        </c:choose>
        <section class="container-fluid inner"></section>
        <section class="container-fluid inner">
            <section class="">
                <img src="img/logo_1.png" class="center-block img-responsive">
            </section>
            <form action="Controller" method="GET" class="form-inline">
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
        </section>
        <%@include file="inc/footer.jspf"%>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
