<%try{%>

<%@page import="beans.Endereco"%>
<%@page import="beans.Carrinho"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="beans.Cliente"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <title>Loja da Pizza - Finalizar Pedido</title>
        <link rel="shortcut icon" href="util/img/favicon.ico" type="image/x-icon"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="./util/css/bootstrapnav.min.css" rel="stylesheet">     
        <link href="util/css/estilo2.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="./util/css/stylesnav.css" rel="stylesheet">
        <!-- All the files that are required -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
        <%
            
            
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            boolean novo = true;
            Carrinho carrinho = new Carrinho();
           
            

            if (session.getAttribute("cliente") != null) {
                cliente = (Cliente) session.getAttribute("cliente");
            }

            try {
                if (clienteDAO.consultarClienteEmail(cliente.getEmail()).getEmail().equals(cliente.getEmail())) {
                    cliente = clienteDAO.consultarClienteEmail(cliente.getEmail());
                    novo = false;
                } else {
                    novo = true;
                }
            } catch (Exception e) {
                novo = true;
            }
            
            if (session.getAttribute("carrinho") == null) {
                response.sendRedirect("perfil.jsp");
            } else {
                carrinho = (Carrinho) session.getAttribute("carrinho");
                if(carrinho.getQuantidade() <= 0){
                   response.sendRedirect("perfil.jsp"); 
                }
            }
            
            
            Endereco endereco2 = new Endereco();     
            if(session.getAttribute("localusuario") != null){
                endereco2 = (Endereco) session.getAttribute("localusuario");     
            }else{
                response.sendRedirect("index.jsp");

            }

            
        %>

    </head>
    <body>
        
        <jsp:include page = "componentes/navbarprincipal.jsp"/>

        <div class="container">
            <div class="row">
                <div class="board">
                    <div class="board-inner">
                        <ul class="nav nav-tabs">
                            <div class="liner"></div>
                            <li class="active"><a title="Dados Pessoais">
                                    <span class="round-tabs two">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span> 
                                </a>
                            </li>

                            <li>
                                <a title="Endereço">
                                    <span class="round-tabs oneinactive">
                                        <i class="glyphicon glyphicon-home"></i>
                                    </span> 
                                </a>
                            </li>



                            <li>
                                <a   title="Confirmação">
                                    <span class="round-tabs fourinactive">
                                        <i class="glyphicon glyphicon-file"></i>
                                    </span> 
                                </a>
                            </li>

                            <li><a  title="Pedido Completo">
                                    <span class="round-tabs fiveinactive">
                                        <i class="glyphicon glyphicon-ok"></i>
                                    </span> </a>
                            </li>

                        </ul></div>

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="home">
                            <h3 class="head text-center">Dados Pessoais</h3>
                            <form class="form-horizontal col-lg-6 col-lg-offset-3" role="form" action="servletCadastrarCliente" method="post" id="form">
                                <jsp:include page = "componentes/erro.jsp"/>
                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg">
                                        <%if (novo) {%>  
                                        <input type="text" class="form-control" name="nome" id="nome" placeholder="Nome Completo *" required autofocus="">
                                        <%} else {%>  
                                        <input type="text" class="form-control" name="nome" id="nome" placeholder="Nome Completo *" value="<%= cliente.getNome()%>" required>
                                        <%}%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg">
                                        <%if (novo) {%>  
                                        <input type="email" class="form-control" name="email" id="email" placeholder="Email *" required>
                                    <%} else {%> 
                                    <input type="email" class="form-control" name="email" id="email" placeholder="Email *" value="<%= cliente.getEmail()%>" required>
                                    <%}%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <%if (novo) {%>  
                                    <div class="col-sm-3 input-group-lg">
                                        <input type="number" class="form-control" name="ddd" id="ddd" placeholder="DDD *" required>
                                    </div>
                                    <div class="col-sm-9 input-group-lg">
                                        <input type="number" class="form-control" name="telefone" id="telefone" placeholder="Telefone *" required>
                                    </div>
                                    <%} else {%> 
                                    <div class="col-sm-3 input-group-lg">
                                        <input type="number" class="form-control" name="ddd" id="ddd" placeholder="DDD *" value="<%= cliente.getDdd()%>"   required>
                                    </div>
                                    <div class="col-sm-9 input-group-lg">
                                        <input type="number" class="form-control" name="telefone" id="telefone" placeholder="Telefone *" value="<%= cliente.getTelefone() %>"  required>
                                    </div>
                                    <%}%>
                                </div>
                                <p class="text-center">
                                    <button type="submit" class="btn btn-warning btn-outline-rounded green">Continuar <span class="glyphicon glyphicon-chevron-right"></span></button> 
                                </p>
                                <span id="msg"></span>
                            </form>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                </div>
            </div>
        </div>
        <jsp:include page = "componentes/rodape.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./util/js/bootstrap.min.js"></script>
        <script src="./util/js/jquery-1.11.3.min.js"></script>
        <script src="./util/js/jquery.validate.min.js"></script>
        <script src="./util/js/validacao.js"></script>
    </body>
    
</html>
 <%
            }catch(Exception e){
        
               response.sendRedirect("inicio.jsp");

        }%>
