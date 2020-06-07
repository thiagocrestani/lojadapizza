
<%@page import="dao.LocaisDAO"%>
<%@page import="beans.Locais"%>

<%
    boolean alterar = false;

    try {
        if (request.getParameter("alterar").equals("true")) {
            alterar = true;
        }
    } catch (Exception e) {

    }

    if (session.getAttribute("localusuario") != null && !alterar) {

        response.sendRedirect("inicio.jsp");

    } else {%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <title>Loja da Pizza</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="util/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
         <link rel="shortcut icon" href="util/img/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" href="./util/css/jquery-ui.css">
        <link rel="stylesheet" type="text/css" href="util/css/animate.css">
        <link href="util/css/estilo.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="util/css/estilo2.css" rel="stylesheet" id="bootstrap-css"> 
        
        <%

            Locais local = new Locais();
            LocaisDAO localDAO = new LocaisDAO();
            local = localDAO.consultarLocaisComBairros(1);

        %>
        <style type="text/css">
            * { margin: 0; padding: 0; }




            body {
             

                font-family: 'Open Sans', sans-serif!important;
            }
            .well{
                background-color:#fff!important;
                border-radius:0!important;
                border:none!important;
            }

            .well.login-box {
                //width:100px;
                margin:0 auto;
                display:none;
            }
            .well.login-box legend {
                font-size:26px;
                text-align:center;
                font-weight:300;
            }
            .well.login-box label {
                font-weight:300;
                font-size:16px;

            }
            .well.login-box {
                box-shadow:none;
                border-color:#ddd;
                border-radius:0;
            }

            .well.welcome-text{
                font-size:21px;
            }
            
            .corpo{
                height:100vh;
                width:100%;
            }




        </style>
        <script src="util/js/jquery-1.11.3.min.js"></script>
        <script src="util/js/bootstrap.min.js"></script>
        <script type="text/javascript">
           
            
            $(document).ready(function () {
                var iframe_height = parseInt($('html').height());
                

                $("#btnEnviar").click(function () {
                    //alert("ok");
                    carregarNoMapa($("#txtEndereco").val());
                });
                
                $("#btnRetirar").click(function () {
                    document.getElementById('formRetirar').submit();
                });

                $(window).keydown(function (event) {
                    if (event.keyCode == 13) {
                        carregarNoMapa($("#txtEndereco").val());
                        return false;
                    }
                });


                var bairros = [
            <%for (int i = 0; i < local.getListBairros().size(); i++) {%>

            <%if (i >= local.getListBairros().size() - 1) {%>
                "<%= local.getListBairros().get(i).getNome()%>"
            <%} else {%>
                "<%= local.getListBairros().get(i).getNome()%>",
            <%}%>
            <%}%>
                ];
                        $("#txtEndereco").autocomplete({
                    source: bairros
                });

                function carregarNoMapa(endereco) {
                    geocoder.geocode({'address': endereco + ' Videira - SC', componentRestrictions: {country: "BR"}, 'region': 'BR'}, function (results, status) {
                        if (status == google.maps.GeocoderStatus.OK) {
                            if (results[0]) {
                                var latitude = results[0].geometry.location.lat();
                                var longitude = results[0].geometry.location.lng();
                                
                                var bairro = "";
                                var cidade = "";
                                
                                for (var n = 0, len = results[0].address_components.length; n < len; n++) {
                                    var ac = results[0].address_components[n];
                                    //alert(ac.types);
                                    
                                    if (ac.types.indexOf("sublocality_level_1") >= 0) {
                                         bairro = ac.long_name;
                                        //alert(bairro);
                                    }
                                    
                                    if (ac.types.indexOf("administrative_area_level_2") >= 0) {
                                         cidade = ac.long_name;
                                        //alert(cidade);
                                    }

                                }
                                //alert(bairro);
                                //bairro = "Centro";

                                if (bairro == "" && $("#txtEndereco").val().toUpperCase() === "Centro".toUpperCase()) {
                              
                                    bairro = "Centro";
                                    alert(bairro);
                                }

                                if (cidade == "Videira" && bairro != "") {

                                    $('#bairro').val(bairro);
                                    $('#cidadeendereco').val(cidade);
                                    $('#txtLatitude').val(latitude);
                                    $('#txtLongitude').val(longitude);

                                    document.getElementById('form').submit();
                                } else {
                                    $('#aviso').text("Endereço não encontado")
                                }
                            }
                        }
                    });
                }

            });
        </script>

        


    </head>
    <body class="indexback">

       
        
        <div class="corpo buscainicio">
        <div class="container">
<div id="mapa" style="height: 0px; width: 0px"></div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="well login-box">
                        <center><img src="util/img/logoindex.png" class="img-responsive" style="padding: 10px"></center>
                            <jsp:include page = "componentes/erro.jsp"/>
                        <center><h3>Onde você está agora?</h3></center>
                        <form method="post" action="servletAtribuiEndereco" id="form">    
                            <div class="form-group col-sm-12"> 
                                <div class="col-sm-4 input-group-lg">
                                    <select class="form-control" name="cidade">
                                        <option value="1"><%=local.getCidade()%> - <%=local.getUfestado()%></option>
                                    </select>
                                </div> 
                                <div class="col-sm-8 input-group-lg">
                                    <input type="text" class="form-control" id="txtEndereco" name="txtEndereco" placeholder="Digite seu bairro" required>
                                    <strong><div class="text-danger" id="aviso"></div></strong>
                                    <a href="contato.jsp?assunto=N%E3o+encontrei+meu+bairro">Não encontrei meu bairro</a>         
                                    <input type="hidden" id="bairro" name="bairro" />
                                    <input type="hidden" id="cidadeendereco" name="cidadeendereco"/>               
                                    <input type="hidden" id="txtLatitude" name="latitude" />
                                    <input type="hidden" id="txtLongitude" name="longitude" />
                                    <input type="hidden" name="url" value="<%= request.getParameter("url")%>"/>
                    
                                    
                                </div>
                        
                            </div>  
                            <div class="form-group text-center">
                                <input type="button"  class="btn btn-warning btn-outline-rounded green" id="btnEnviar" value="Entrar">
                                 <input type="button"  class="btn btn-warning btn-outline-rounded green" id="btnRetirar" value="Quero Retirar">              
                            </div>
                        </form>
                    </div>

                </div>
            </div>
                                    <form action="servletAtribuiEndereco" method="post" id="formRetirar">
                                        <input type="hidden"  name="retirar" value="true"/>
                                        <input type="hidden" name="url" value="<%= request.getParameter("url")%>"/>
                                    </form>
                                                                          
        </div>
        </div>
        
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAdwuPxYRkbVwnJT0kHRfRGdLAUpYp4WAg&sensor=false"></script>

    
        <script src="./util/js/jquery-ui.js"></script>
        <script src="./util/js/mapa.js"></script>
        <script type="text/javascript">
            function varticalCenterStuff() {
                var windowHeight = $(window).height();
                var loginBoxHeight = $('.login-box').innerHeight();
                var mathLogin = (windowHeight / 2) - (loginBoxHeight / 2);
                $('.login-box').css('margin-top', mathLogin);
                $('.login-box').show();
            }
            $(window).resize(function () {
                varticalCenterStuff();
            });
            varticalCenterStuff();


        </script>
    </body>
</html>
<%}%>
