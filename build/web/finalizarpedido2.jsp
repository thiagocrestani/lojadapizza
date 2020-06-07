
<%@page import="beans.Carrinho"%>
<%@page import="beans.Endereco"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EnderecoDAO"%>
<%@page import="java.util.List"%>
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
        <link href="util/css/estilo.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="util/css/estilo2.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="./util/css/stylesnav.css" rel="stylesheet">
        <!-- All the files that are required -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
        <%
            
            try{
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            List<Endereco> listEndereco = new ArrayList<Endereco>();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            boolean novo = false;
            Carrinho carrinho = new Carrinho();
            Endereco endereco = new Endereco(); 
            
            if(session.getAttribute("localusuario") != null){
                endereco = (Endereco) session.getAttribute("localusuario");     
            }else{
                response.sendRedirect("index.jsp");
            }

            if (session.getAttribute("carrinho") != null) {
                carrinho = (Carrinho) session.getAttribute("carrinho");
                if (carrinho.getQuantidade() <= 0) {
                    response.sendRedirect("perfil.jsp");
                }
                if (session.getAttribute("cliente") != null) {
                    cliente = (Cliente) session.getAttribute("cliente");
                    if (request.getParameter("novoend") != null) {
                        novo = true;
                    }else{
                        try {
                            if (clienteDAO.consultarClienteEmail(cliente.getEmail()).getEmail().equals(cliente.getEmail())) {
                                cliente = clienteDAO.consultarClienteEmail(cliente.getEmail());
                                listEndereco = enderecoDAO.consultarEnderecosAtivo(cliente.getId(), endereco.getBairro(), endereco.getCidade());
                            } else {
                                response.sendRedirect("finalizarpedido1.jsp");
                            }
                        } catch (Exception e) {
                            response.sendRedirect("finalizarpedido1.jsp");
                        }
                   
                   //     response.sendRedirect("perfil.jsp");
                    }
                } else {
                    response.sendRedirect("finalizarpedido1.jsp");
                }
            } else {
                response.sendRedirect("perfil.jsp");
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
                            <li><a href="finalizarpedido1.jsp" title="Dados Pessoais">
                                    <span class="round-tabs two">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span> 
                                </a>
                            </li>
                            <li class="active">
                                <a   title="Endereço">
                                    <span class="round-tabs one">
                                        <i class="glyphicon glyphicon-home"></i>
                                    </span> 
                                </a>
                            </li>
                            <li>
                                <a title="Confirmação">
                                    <span class="round-tabs fourinactive">
                                        <i class="glyphicon glyphicon-file"></i>
                                    </span> 
                                </a>
                            </li>
                            <li><a title="Pedido Completo">
                                    <span class="round-tabs fiveinactive">
                                        <i class="glyphicon glyphicon-ok"></i>
                                    </span> </a>
                            </li>

                        </ul></div>

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="home">

                            <h3 class="head text-center">Endereço</h3>

                            <%if (listEndereco.size() <= 0 || novo) {%>
                            <form class="form-horizontal col-lg-6 col-lg-offset-3" id="form" action="servletCadastrarEndereco" method="post" >
                                <jsp:include page = "componentes/erro.jsp"/>
                                <input type="hidden" class="form-control" name="idcliente" value="<%= cliente.getId()%>">
                                <div class="form-group">    
                                    <div class="col-sm-9 input-group-lg">
                                        <input type="text" class="form-control" name="rua" id="nome" placeholder="Rua *"  required >
                                    </div>
                                    <div class="col-sm-3 input-group-lg">
                                        <input type="number" class="form-control" name="numero" id="numero" placeholder="Nº *" required>
                                    </div>    
                                </div>             
                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="text" class="form-control" name="bairro" placeholder="Bairro" value="<%=endereco.getBairro()%>" readonly="">                                   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="text" class="form-control" name="complemento" placeholder="Complemento">                                   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <textarea rows="3" class="form-control" name="pontodereferencia" placeholder="Ponto de Referência"></textarea>                                  
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="text" class="form-control" name="cep" placeholder="Cep" value="<%=endereco.getCep() %>" required readonly="">                                   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="text" class="form-control" name="cidade" placeholder="Cidade" value="<%=endereco.getCidade()%>" required readonly="">                                   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="text" class="form-control" name="estado" placeholder="Estado" value="<%=endereco.getEstado()%>" required readonly="">                                   
                                    </div>
                                </div>
                                     <p class="text-center">
                                         <a href="index.jsp?url=finalizarpedido2.jsp&alterar=true">Alterar meu endereço</a>
                                </p>
                                <p class="text-center">
                                    <button type="submit" class="btn btn-warning btn-outline-rounded green">Continuar <spam class="glyphicon glyphicon-chevron-right"></spam> </button> 
                                </p>
                            </form>
                            <%} else {%>

                            <div class="col-lg-12" style="padding-bottom: 20px">
                                <%for (int i = 0; i < listEndereco.size(); i++) {%>


                                <div class="col-lg-6">
                                    <div class="col-lg-12 col-lg-offset-1 fundoenderecos text-left">
                                        <p><b>Rua:</b> <%= listEndereco.get(i).getRua()%>, <%= listEndereco.get(i).getNumero()%> </p>
                                        <p><b>Bairro:</b> <%= listEndereco.get(i).getBairro()%> </p>
                                        <p><b>Complemento:</b> <%= listEndereco.get(i).getComplemento()%></p>
                                        <p><b>Ponto de Refêrencia:</b> <%= listEndereco.get(i).getPontodereferencia()%></p>
                                        <p><b>Cep:</b> <%= listEndereco.get(i).getCep()%></p>
                                        <p><b>Cidade:</b> <%= listEndereco.get(i).getCidade()%></p>
                                        <p><b>Estado:</b> <%= listEndereco.get(i).getEstado()%></p>
                                        <center>
                                            <form action="finalizarpedido3.jsp" method="get" id="fomulario">
                                                <input type="hidden" name="idendereco" value="<%= listEndereco.get(i).getId()%>">
                                                <button type="submit" class="btn btn-warning btn-outline-rounded green">Utilizar <spam class="glyphicon glyphicon-chevron-right"></spam> </button> 
                                            </form>

                                            <form action="servletExcluirEndereco" method="post" id="fomulario">
                                                <input type="hidden" name="idendereco" value="<%= listEndereco.get(i).getId()%>">
                                                <button type="submit" class="btn btn-link"> Excluir</button>
                                            </form></center> 
                                    </div>
                                </div>
                                <%}%>
                                <div class="col-lg-6">
                                    <div class="col-lg-12 col-lg-offset-1 fundoenderecos text-left">
                                        <center><form action="finalizarpedido2.jsp" method="post" id="fomulario">
                                                <input type="hidden" name="novoend" value="true">
                                                <button type="submit" class="btn btn-warning btn-outline-rounded green"><spam class="glyphicon glyphicon-plus-sign"></spam> Novo Endereço</button>
                                            </form></center> 
                                    </div>
                                </div>
                            </div>
                            <%}%>
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
    
    <%
            }catch(Exception e){
        
               response.sendRedirect("inicio.jsp");

        }%>
</html>
