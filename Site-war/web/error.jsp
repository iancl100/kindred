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
            <section class="row inner jumbotron table-responsive" id="summonerHeader">
                    <section class="col-sm-12">
                        ${error}
                    </section>
                </section>
        </section>
        <%@include file="inc/footer.jspf"%>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
