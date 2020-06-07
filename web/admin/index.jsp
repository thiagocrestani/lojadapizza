
<%@page import="util.FormataData"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="dao.PedidoDAO"%>
<%@page import="beans.Endereco"%>
<%@page import="beans.Cliente"%>
<%@page import="beans.Pedido"%>
<%@page import="beans.Produto"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <%

            UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
            VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
            if (verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))) {
           
                PedidoDAO pedidoDAO = new PedidoDAO();
                List<Pedido> listPedido = new ArrayList<Pedido>();
                usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
                listPedido = pedidoDAO.consultarPedidosCompleto(usuarioestabelecimento.getEstabelecimento(), 10, 0);
        %>

        <title>Administrador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <body>

        <script type="text/javascript">
            function Ajax() {
                var xmlHttp;
                try {
                    xmlHttp = new XMLHttpRequest();// Firefox, Opera 8.0+, Safari
                }
                catch (e) {
                    try {
                        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // Internet Explorer
                    }
                    catch (e) {
                        try {
                            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        catch (e) {
                            alert("No AJAX!?");
                            return false;
                        }
                    }
                }

                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4) {
                        document.getElementById('ReloadThis').innerHTML = xmlHttp.responseText;
                        setTimeout('Ajax()', 300000);
                    }
                }
                xmlHttp.open("GET", "componentes/novospedidos.jsp?idestabelecimento=<%= usuarioestabelecimento.getEstabelecimento()%>&qtd=<%= pedidoDAO.quantidadepedidos(usuarioestabelecimento.getEstabelecimento())%>", true);
                xmlHttp.send(null);
            }

            window.onload = function () {
                setTimeout('Ajax()', 300000);
            }
        </script>

        <jsp:include page = "componentes/navbar.jsp"/>
        <!-- /Header -->
        <!-- Main -->
        <div class="container-fluid">
            <div class="row">
                <jsp:include page = "componentes/navlateral.jsp"/>       
                <!-- /col-3 -->
                <div class="col-sm-9">
                    <!-- column 2 -->
                    <hr>
                    <ul class="list-inline pull-right">
                        <li><a href="dados.jsp"><i class="glyphicon glyphicon-cog"></i></a></li>

                    </ul>
                    <strong><i class="glyphicon glyphicon-dashboard"></i> Painel de Controle</strong>
                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>


                    <!--/row-->

                    <hr>

                    <a href="index.jsp"><strong><i class="glyphicon glyphicon-comment"></i> Pedidos</strong></a><span id="ReloadThis"></span>
                    <hr>
                    
                    
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="list-group">
                                <%  for (int i = 0; i < listPedido.size(); i++) { %>


                                <li class="list-group-item">
                                    <%if (listPedido.get(i).isAberto()) {%>
                                    <span class="label label-danger">Aberto</span>

                                    <%} else {%>
                                    <span class="label label-success">Concluído</span>
                                    <%}%>
                                    <a href="detalhepedido.jsp?id=<%= listPedido.get(i).getId()%>"><small>
                                            (<%= listPedido.get(i).getDatapedidofomatada()%>) </small> <%= listPedido.get(i).getCliente().getNome()%> - <%if(listPedido.get(i).getIdendereco()> 0){%><%= listPedido.get(i).getEndereco().getRua()%>, <%= listPedido.get(i).getEndereco().getNumero()%><%}else{%>Retira no local<%}%></a></li>
                                            <%}%>
                            </ul>
                        </div>
                    </div>
                </div><!--/ateaki-9-->   
            </div>
        </div>
        <!-- /Main -->
        <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script type='text/javascript' src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    </body>
    <%

        } else {
            response.sendRedirect("login.jsp");
        }
    %>
</html>