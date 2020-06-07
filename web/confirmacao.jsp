

<%@page import="beans.Endereco"%>
<%@page import="beans.PedidoFinalizado"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <title>Loja da Pizza - Finalizar Pedido</title>
        <link rel="shortcut icon" href="util/img/favicon.ico" type="image/x-icon"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="./util/css/bootstrapnav.min.css" rel="stylesheet">   
        <link href="util/css/estilo.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="util/css/estilo2.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="./util/css/stylesnav.css" rel="stylesheet">
        <!-- All the files that are required -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
      <%
      
      try{
      if(session.getAttribute("pedidofinalizado") != null){
          PedidoFinalizado pedidofinalizado = new PedidoFinalizado();
          pedidofinalizado = (PedidoFinalizado) session.getAttribute("pedidofinalizado");
          session.setAttribute("pedidofinalizado",null);
          
          Endereco endereco = new Endereco();     
            if(session.getAttribute("localusuario") != null){
                endereco = (Endereco) session.getAttribute("localusuario");         
            }else{
                response.sendRedirect("index.jsp");

            }
      
      
      %>
       
         

    </head>
    <body >
        <jsp:include page = "componentes/navbarprincipal.jsp"/>
        <div class="container">
            <div class="row">
                <div class="board">
                    <div class="board-inner">
                        <ul class="nav nav-tabs">
                            <div class="liner"></div>
                            <li ><a  title="Dados Pessoais">
                                    <span class="round-tabs two">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span> 
                                </a>
                            </li>

                            <li>
                                <a title="Endereço">
                                    <span class="round-tabs one">
                                        <i class="glyphicon glyphicon-home"></i>
                                    </span> 
                                </a>
                            </li>



                            <li>
                                <a title="Confirmação">
                                    <span class="round-tabs four">
                                        <i class="glyphicon glyphicon-file"></i>
                                    </span> 
                                </a>
                            </li>

                            <li class="active"><a  title="Pedido Completo">
                                    <span class="round-tabs five">
                                        <i class="glyphicon glyphicon-ok"></i>
                                    </span> </a>
                            </li>

                        </ul></div>

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="home">

                            <h3 class="head text-center">Pedido Realizado com Sucesso!</h3>
                            <div class="col-lg-12">
                                <div class="col-lg-offset-2 col-lg-8">
                                    <%if(pedidofinalizado.getPedido().getIdendereco() > 0){%>
                                    <center><h4>O seu pedido foi enviado para <%= pedidofinalizado.getEstabelecimento().getNome() %></h4></center>
                                    <center><h4 class="text-warning">Você receberá sua encomenta em aproximadamente <%= pedidofinalizado.getPedido().getTempoentrega()%> minutos</h4></center>
                                    
                                    <%}else{%>
                                    <center><h4>Você deve retirar seu pedido em <%= pedidofinalizado.getEstabelecimento().getNome() %></h4></center>
                                    <center><h4 class="text-warning">Seu pedido estará pronto em aproximadamente <%= pedidofinalizado.getPedido().getTempoentrega()%> minutos</h4></center>
                                    <div class="col-lg-12">
                                <div class="col-lg-offset-1 col-lg-10 fundoconfirmarpedido">
                                    <h4>Endereço para retirada</h4>
                                    <p><b>Rua:</b> <%= pedidofinalizado.getEstabelecimento().getRua()%>, <%= pedidofinalizado.getEstabelecimento().getNumero()%> </p>
                                    <p><b>Bairro:</b> <%= pedidofinalizado.getEstabelecimento().getBairro()%> </p>
                                    <p><b>Complemento:</b> <%= pedidofinalizado.getEstabelecimento().getComplemento()%></p>
                                    <p><b>Ponto de Refêrencia:</b> <%= pedidofinalizado.getEstabelecimento().getPontodereferencia()%></p>
                                    <p><b>Cep:</b> <%= pedidofinalizado.getEstabelecimento().getCep()%></p>
                                    <p><b>Cidade:</b> <%= pedidofinalizado.getEstabelecimento().getCidade()%></p>
                                    <p><b>Estado:</b> <%= pedidofinalizado.getEstabelecimento().getEstado()%></p>
                          
                                </div>
                                </div>
                                    <%}%>
                                    
                                    <center><h6>Em caso de dúvida ligue para (<%= pedidofinalizado.getEstabelecimento().getDdd()%>) <%= pedidofinalizado.getEstabelecimento().getTelefone()%></h6></center>
                                    <center><h6>Uma cópia de seu pedido (#<%= pedidofinalizado.getPedido().getId()%>) foi enviado para  <%= pedidofinalizado.getPedido().getCliente().getEmail()%></h6></center>
                                    <br>
                                    <br>
                                    <br>
                                </div>
                            </div>
                            
                           
                        </div>
                        <div class="clearfix"></div>
                    </div>

                </div>
            </div>
        </div>
                                    
                                    <jsp:include page = "componentes/rodape.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./util/js/bootstrap.min.js"></script>
    </body>
    
    <%
    }else{
         response.sendRedirect("inicio.jsp?1");
      }
      }catch(Exception e){
           response.sendRedirect("inicio.jsp?2");
          
      }
    
    
    %>
</html>
