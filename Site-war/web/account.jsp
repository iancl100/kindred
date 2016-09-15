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
        <title>Account</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/cover.css" rel="stylesheet" type="text/css"/>
        <link href="css/historico.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <c:choose>
            <c:when test="${account == null}"> <%--User -> Objeto que representa o usuário --%>
                <c:redirect url="index.jsp"></c:redirect>
            </c:when>
            <c:otherwise >
                <%@include file="inc/navbarWithLogoAuthenticated.jspf" %>
            </c:otherwise>
        </c:choose>
        <section class="container-fluid">
            <section class="container inner">
                <section class="row inner jumbotron table-responsive" id="summonerHeader">
                    <section class="col-sm-12 text-center">
                        <h3>Minha Conta</h3>
                    </section>
                </section>
                <section class="container-fluid" style="min-height: 330px;">
                    <section class="row inner">
                        <form action="Controller" method="GET" class="form-inline">
                            <input type="hidden" name="command" value="Summoner.buscar">
                            <p class="lead">
                            <section class="form-group">
                                <input id="summonerInput" type="text" placeholder="Summoner's name" name="summonerName" style="min-width: 300px" class="form-control input-lg" required/>
                            </section>
                            <button type="submit" class="btn  btn-primary btn-lg">Adicionar</button>
                            </p>
                        </form>
                    </section>

                </section>
            </section>

        </section><%@include file="inc/footer.jspf" %>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
