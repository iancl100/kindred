<%-- 
    Document   : register
    Created on : 25/08/2016, 07:59:22
    Author     : 31559042
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Register</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/cover.css" rel="stylesheet" type="text/css"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <style>
            .navbar-brand {
                padding: 0px;
                height: 100px;
            }
            .navbar-brand>img {
                height: 100%;
                width: auto;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-default transparent navbar-inverse">
            <section class="container inner">
                <section class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp"><img class="" src="img/logo_1.png"></a>
                </section>
                <section class="navbar-collapse collapse" id="navbar">
                    <form class="navbar-form navbar-right">
                        <a href="login.jsp" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-log-in"></span> Login</a>
                    </form>
                </section>
            </section>
        </nav>
        <section class="container inner" id="containerLogin">
            <section class="jumbotron">
                <h1>Sign In</h1>
                <form action="Controller" method="POST">
                    <input type="hidden" name="command" value="Account.register">
                    <section class="form-group">
                        <input class="form-control input-sm" type="text" placeholder="Username" name="username" required/>
                    </section>
                    <section class="form-group">
                        <input class="form-control input-sm" type="email" placeholder="E-mail" name="email" required/>
                    </section>
                    <section class="form-group">
                        <input class="form-control input-sm" type="password" placeholder="Password" name="password" required/>
                    </section>
                    <section class="form-group">
                        <input class="form-control input-sm" type="password" placeholder="Repeat Password" name="password2" required/>
                    </section>
                    <button type="submit" class="btn btn-primary btn-sm">Register</button>
                </form>
            </section>
        </section>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
