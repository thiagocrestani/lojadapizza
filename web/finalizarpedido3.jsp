

<%@page import="dao.EstabelecimentoDAO"%>
<%@page import="beans.Estabelecimento"%>
<%@page import="dao.FormaPagamentoEstabelecimentoDAO"%>
<%@page import="beans.FormaPagamento"%>
<%@page import="beans.Carrinho"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.EnderecoDAO"%>
<%@page import="beans.Endereco"%>
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

            try {
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();
                List<Endereco> listEndereco = new ArrayList<Endereco>();
                Endereco endereco = new Endereco();
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                Carrinho carrinho = new Carrinho();
                Estabelecimento estabelecimento = new Estabelecimento();
                EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
                List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
                FormaPagamentoEstabelecimentoDAO formaPagamentoestabelecimentoDAO = new FormaPagamentoEstabelecimentoDAO();

                if (session.getAttribute("cliente") != null) {
                    cliente = (Cliente) session.getAttribute("cliente");
                    try {
                        if (session.getAttribute("carrinho") != null) {
                            carrinho = (Carrinho) session.getAttribute("carrinho");
                            try {
                                
                                if ((!carrinho.isRetirar() && !carrinho.isRecarregar()) || (carrinho.isRetirar() && carrinho.isRecarregar())) {     
                                    if (Integer.parseInt(request.getParameter("idendereco")) == enderecoDAO.consultarEnderecoCliente(Integer.parseInt(request.getParameter("idendereco")), cliente.getId()).getId()) {
                                        endereco = enderecoDAO.consultarEnderecoCliente(Integer.parseInt(request.getParameter("idendereco")), cliente.getId());
                                    } else {
                                        response.sendRedirect("finalizarpedido2.jsp");
                                    }
                                }else{
                                    estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(carrinho.getEstabelecimento()); 
                                }
                                listFormaPagamento = formaPagamentoestabelecimentoDAO.consultarFormasPagamentoEstabelecimento(carrinho.getEstabelecimento());
                                
                            } catch (Exception e) {

                                response.sendRedirect("finalizarpedido2.jsp");
                            }
                            if (carrinho.getQuantidade() <= 0) {
                                response.sendRedirect("perfil.jsp");
                            }
                        } else {
                            response.sendRedirect("finalizarpedido1.jsp");
                        }

                    } catch (Exception e) {
                        response.sendRedirect("perfil.jsp");
                    }

                } else {
                    response.sendRedirect("perfil.jsp");
                }


        %>

        <script>
            var request;
            var request2;
            var request3;
            var qtdcarrinho = <%= carrinho.getListProduto().size()%>;
            var estab = <%= carrinho.getEstabelecimento()%>;



            function sendInfo(v, l, e)
            {
                if (v != null) {
                    var url = "componentes/carrinho.jsp?estab=" + estab + "&id=" + v;
                } else
                if (l != null) {
                    var url = "componentes/carrinho.jsp?l=" + l;
                } else
                if (e != null) {
                    var url = "componentes/carrinho.jsp?eli=" + e;
                } else {
                    var url = "componentes/carrinho.jsp?";
                }

                if (window.XMLHttpRequest) {
                    request = new XMLHttpRequest();
                }
                else if (window.ActiveXObject) {
                    request = new ActiveXObject("Microsoft.XMLHTTP");
                }

                try
                {
                    request.onreadystatechange = getInfo;
                    request.open("GET", url, true);
                    request.send();
                }
                catch (e)
                {
                    alert("Unable to connect to server");
                }
            }
            function alterarentrega(v) {
                //alert("ok");
                window.location.href = "servletAtribuiEndereco?recarregar=true&retirar=" + v + "&url=finalizarpedido3.jsp";
            }


            function getInfo() {
                if (request.readyState == 4) {
                    document.getElementById('cart').innerHTML = request.responseText;

                }
            }




            window.onload = function () {
                sendInfo(null, null, null);
            };


            function limparcarinho() {
                sendInfo(null, true, null);
                qtdcarrinho = 0;
                window.location = "perfil.jsp";

            }

            function excluiritem(e) {
                sendInfo(null, null, e);
                qtdcarrinho--;
                if (qtdcarrinho <= 0) {
                    window.location = "perfil.jsp";
                }
            }
        </script>

    </head>
    <body>
        <jsp:include page = "componentes/navbarprincipal.jsp"/>
        <div class="container">
            <div class="row">
                <div class="board">
                    <div class="board-inner">
                        <ul class="nav nav-tabs">
                            <div class="liner"></div>
                            <li ><a href="finalizarpedido1.jsp"  title="Dados Pessoais">
                                    <span class="round-tabs two">
                                        <i class="glyphicon glyphicon-user"></i>
                                    </span> 
                                </a>
                            </li>

                            <li>
                                <a href="finalizarpedido2.jsp"  title="Endereço">
                                    <span class="round-tabs one">
                                        <i class="glyphicon glyphicon-home"></i>
                                    </span> 
                                </a>
                            </li>



                            <li class="active">
                                <a title="Confirmação">
                                    <span class="round-tabs four">
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

                            <h3 class="head text-center">Confirmação</h3>
                            <div class="col-lg-12">
                                <div class="col-lg-offset-2 col-lg-8 fundoconfirmarpedido">
                                    <h4>Usuário</h4>   
                                    <p><b>Nome:</b> <%= cliente.getNome()%> </p>
                                    <p><b>Email:</b> <%= cliente.getEmail()%></p>
                                    <p><b>Telefone:</b> (<%= cliente.getDdd()%>) <%= cliente.getTelefone()%></p>
                                    <br>
                                    <center><a href="finalizarpedido1.jsp">Editar <span class="glyphicon glyphicon-pencil"></span></a></center>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="col-lg-offset-2 col-lg-8 fundoconfirmarpedido">
                                    
                                    
                                    <%if ((!carrinho.isRetirar() && !carrinho.isRecarregar()) || (carrinho.isRetirar() && carrinho.isRecarregar())) {%>       

                                    <h4>Endereço de Entrega</h4>   
                                    <p><b>Rua:</b> <%= endereco.getRua()%>, <%= endereco.getNumero()%> </p>
                                    <p><b>Bairro:</b> <%= endereco.getBairro()%> </p>
                                    <p><b>Complemento:</b> <%= endereco.getComplemento()%></p>
                                    <p><b>Ponto de Refêrencia:</b> <%= endereco.getPontodereferencia()%></p>
                                    <p><b>Cep:</b> <%= endereco.getCep()%></p>
                                    <p><b>Cidade:</b> <%= endereco.getCidade()%></p>
                                    <p><b>Estado:</b> <%= endereco.getEstado()%></p>
                                    <br>
                                    <center><a href="finalizarpedido2.jsp">Editar <span class="glyphicon glyphicon-pencil"></span></a></center>


                                    <%} else {%>
                                    <h4>Endereço para Retirada</h4> 
                                    <p><b>Nome:</b> <%= estabelecimento.getNome()%> </p>
                                    <p><b>Rua:</b> <%= estabelecimento.getRua()%>, <%= estabelecimento.getNumero()%> </p>
                                    <p><b>Bairro:</b> <%= estabelecimento.getBairro()%> </p>
                                    <p><b>Complemento:</b> <%= estabelecimento.getComplemento()%></p>
                                    <p><b>Ponto de Refêrencia:</b> <%= estabelecimento.getPontodereferencia()%></p>
                                    <p><b>Cep:</b> <%= estabelecimento.getCep()%></p>
                                    <p><b>Cidade:</b> <%= estabelecimento.getCidade()%></p>
                                    <p><b>Estado:</b> <%= estabelecimento.getEstado()%></p>
                                    <%}%>
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="col-lg-offset-2 col-lg-8">
                                    <h4>Produtos</h4>   
                                    <span id="cart"></span>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="col-lg-offset-2 col-lg-8">
                                    <form class="form-horizontal" role="form" action="servletFinalizarPedido" method="post" id="form">

                                        <input type="hidden" class="form-control" name="idcliente" value="<%= cliente.getId()%>">
                                        <input type="hidden" class="form-control" name="idendereco" value="<%= endereco.getId()%>">
                                        <div class="form-group">

                                            <label class="col-lg-4 control-label">Forma de Pagamento:</label>
                                            <div class="col-lg-8">


                                                <% for (int i = 0; i < listFormaPagamento.size(); i++) {%>
                                                <div class="radio">

                                                    <input type="radio" name="formapagamento" value="<%=listFormaPagamento.get(i).getId()%>"><%=listFormaPagamento.get(i).getNome()%></br>
                                                </div>
                                                <label for="formapagamento" class="error text-error" style="display:none;"></label>
                                                <%}%>


                                            </div>
                                        </div>

                                        <center><button type="submit" class="btn btn-warning btn-outline-rounded green">Fazer Pedido <span class="glyphicon glyphicon-ok"></span></button></center> 
                                    </form>
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
        <script src="./util/js/jquery-1.11.3.min.js"></script>
        <script src="./util/js/jquery.validate.min.js"></script>
        <script src="./util/js/validacao.js"></script>
    </body>

    <%
                                                
        //carrinho.setRecarregar(false);
        //session.setAttribute("carrinho", carrinho);                                        
        } catch (Exception e) {

            response.sendRedirect("inicio.jsp");

        }

    %>
</html>
