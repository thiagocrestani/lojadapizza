<%-- 
    Document   : LoginCliente
    Created on : 11/04/2014, 08:40:56
    Author     : thiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../util/css/bootstrap.min.css" rel="stylesheet">
        <title>Login</title>
        <style type="text/css">
            /*
        Inspired by http://dribbble.com/shots/917819-iPad-Calendar-Login?list=shots&sort=views&timeframe=ever&offset=461
            */
            body {
                background: url(../util/img/novofundoestab.png);
            }

            .jumbotron {
                text-align: center;
                width: 30rem;
                height: 400px;
                border-radius: 0.5rem;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
                position: absolute;
                margin: 4rem auto;
                background-color: #fff;
                padding: 2rem;
            }

            .container .glyphicon-list-alt {
                font-size: 10rem;
                margin-top: 3rem;
                color: #f96145;
            }

            input {
                width: 100%;
                margin-bottom: 1.4rem;
                padding: 1rem;
                background-color: #ecf2f4;
                border-radius: 0.2rem;
                border: none;
            }
            h2 {
                margin-bottom: 3rem;
                font-weight: bold;
                color: #ababab;
            }
            .btn {
                border-radius: 0.2rem;
            }
            .btn .glyphicon {
                font-size: 3rem;
                color: #fff;
            }
            .full-width {
                background-color: #006dcc;
                width: 100%;
                -webkit-border-top-right-radius: 0;
                -webkit-border-bottom-right-radius: 0;
                -moz-border-radius-topright: 0;
                -moz-border-radius-bottomright: 0;
                border-top-right-radius: 0;
                border-bottom-right-radius: 0;
            }

            .box {
                position: absolute;
                bottom: 0;
                left: 0;
                margin-bottom: 3rem;
                margin-left: 3rem;
                margin-right: 3rem;
            }
        </style>

    </head>
    <body>   
        <div class="jumbotron">
            <div class="container">
                <span class="glyphicon glyphicon-list-alt"></span>
                <h2>Administrador</h2>
                
                <div class="box">
                    
                  
                    <form class="form-horizontal" role="form" action="../sevletLoginUsuarioEstabelecimento" method="post">
                    <jsp:include page = "componentes/erro.jsp"/>
                    <input type="text" placeholder="Usuário" name="usuario">
                    <input type="password" placeholder="Senha" name="senha">
                    <button type="submit" class="btn btn-default full-width"><span class="glyphicon glyphicon-ok"></span></button>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../util/js/bootstrap.min.js"></script>
    </body>
</html>
   



            
        
