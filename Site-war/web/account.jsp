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
        <!--Teste de Commit-->
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
                            <input type="hidden" name="command" value="Summoner.adicionar">
                            <p class="lead">
                            <section class="form-group" >
                                <select class="form-control input-lg summonerSelect">
                                    <option>BR</option>
                                    <option>EUNE</option>
                                    <option>EUW</option>
                                    <option>JP</option>
                                    <option>KR</option>
                                    <option>LAN</option>
                                    <option>LAS</option>
                                    <option>NA</option>
                                    <option>OCE</option>
                                    <option>RU</option>
                                    <option>TR</option>
                                </select>
                            </section>
                            <section class="form-group ">
                                <input  type="text" placeholder="Summoner's name" name="summonerName" style="min-width: 300px" class="form-control input-lg summonerInput" required/>
                            </section>
                            <button type="submit" class="btn  btn-primary btn-lg summonerButton">Adicionar</button>
                            </p>
                        </form>
                    </section>
                    <section class="row inner" style="width: 40%;margin:auto;">
                        <h2>Teste da Persistência</h2>
                        <form action="Controller" method="post" class="jumbotron">
                            <input type="hidden" name="command" value="Account.updatePassword">
                            <input type="hidden" name="idAccount" value="${account.idAccount}">
                            <section class="form-group">
                            <input class="form-control input-sm" type="password" name="oldPassword" placeholder="Senha antiga">
                            </section>
                            <section class="form-group">
                            <input class="form-control input-sm" type="password" name="newPassword" placeholder="Nova senha">
                            </section>
                            <section class="form-group">
                            <input class="form-control input-sm" type="password" name="newPasswordConfirm" placeholder="Nova senha novamente">
                            </section>
                            <button class="btn btn-danger" btn-sm type="submit">Trocar senha</button>
                        </form>
                        
                        <form action="Controller" method="post" class="form-inline">
                            <input type="hidden" name="command" value="Account.delete">
                            <input type="hidden" name="idAccount" value="${account.idAccount}">
                            <button class="btn btn-danger" type="submit">Deletar Conta</button>
                        </form>
                    </section>

                </section>
            </section>

        </section><%@include file="inc/footer.jspf" %>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
