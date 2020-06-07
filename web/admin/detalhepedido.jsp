<%-- 
    Document   : detalhepedido
    Created on : 27/07/2015, 19:15:36
    Author     : thiagocrestani
--%>


<%@page import="util.CategoriaUsa"%>
<%@page import="dao.FormaPagamentoDAO"%>
<%@page import="dao.PedidoDAO"%>
<%@page import="beans.Pedido"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"> 
        <meta charset="utf-8">
        <%
          
            UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
            FormaPagamentoDAO formapagamentoDAO = new FormaPagamentoDAO();
            VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
            if (verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))) {
                Pedido pedido = new Pedido();
                PedidoDAO pedidoDAO = new PedidoDAO();
                usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
                CategoriaUsa categoriausa = new CategoriaUsa();
                
                try {
                    pedido = pedidoDAO.consultarPedidoIdCompleto(request.getParameter("id"),usuarioestabelecimento.getEstabelecimento());
                } catch (Exception e) {
                    response.sendRedirect("pedidos.jsp");
                }

        %>
        <title>Administrador</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <body>

        <jsp:include page = "componentes/navbar.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <jsp:include page = "componentes/navlateral.jsp"/>       
                <!-- /col-3 -->
                <div class="col-sm-9"><!-- /ate aki -->

                    <hr>

                    <a href="#"><strong><i class="glyphicon glyphicon-comment"></i> Detalhe Pedido #<%= pedido.getId()%></strong></a>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4>Cliente</h4></div>
                                <div class="panel-body">
                                    <p>Nome: <%= pedido.getCliente().getNome()%></p>
                                    <p>Email: <%= pedido.getCliente().getEmail()%></p>
                                    <p>Telefone: (<%= pedido.getCliente().getDdd()%>) <%= pedido.getCliente().getTelefone()%></p>
                                    <hr>
                                    <p>Forma de Pagamento: <%= pedido.getFormapagamento()%></p>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <%if(pedido.getIdendereco()> 0){%>
                                <div class="panel-heading">
                                    <h4>Endereço para entrega</h4></div>
                                <div class="panel-body">
                                    <p>Rua: <%= pedido.getEndereco().getRua()%>, <%= pedido.getEndereco().getNumero()%></p>
                                    <p>Bairro:  <%= pedido.getEndereco().getBairro()%></p>
                                    <p>Complemento:  <%= pedido.getEndereco().getComplemento()%></p>
                                    <p>Ponto de referêcia:  <%= pedido.getEndereco().getPontodereferencia()%></p>
                                    <p>Cep:  <%= pedido.getEndereco().getCep()%></p>
                                    <p>Cidade:  <%= pedido.getEndereco().getCidade()%></p>
                                    <p>Estado:  <%= pedido.getEndereco().getEstado()%></p>
                                    <p>Pais:  <%= pedido.getEndereco().getPais()%></p>
                                </div>
                                <%}else{%>
                                
                                <center><h4>Retira no local</h4></center>
                                <%}%>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4>Produtos</h4></div>
                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <tr><th>Categoria</th><th>Nome</th><th>Preço</th><th>Complemento</th></tr>
                                                <%  for (int i = 0; i < pedido.getProdutos().size(); i++) {%>
                                        <tr>
                                            <td><%= pedido.getProdutos().get(i).getCategoria()%></td>

                                            <td><%= pedido.getProdutos().get(i).getNome()%></td>

                                            <td>R$ <%= String.format("%.2f", (double) pedido.getProdutos().get(i).getPreco())%></td>

                                            <td><%= pedido.getProdutos().get(i).getTamanho()%> - <%= pedido.getProdutos().get(i).getSaborborda()%> <%if(pedido.getProdutos().get(i).getSabores() > 1){%>- <%= pedido.getProdutos().get(i).getSabores()%> Sabores<%}%></td>

                                        </tr>
                                        <%
                                            }
                                        %>
                                    </table>
                                    <p>Taxa de entrega: <%if(pedido.getIdendereco()> 0){%>R$ <%= String.format("%.2f", (double) Double.parseDouble(pedido.getTaxaentrega()))%><%}else{%>Retira no local<%}%></p>
                                    <p>Total Pedido: R$ <%= String.format("%.2f", (double) pedido.getValor())%></p>
                                </div>
                            </div>

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4>Outros</h4></div>
                                <div class="panel-body">

                                    <p>Tempo de entrega: <%= pedido.getTempoentrega()%> min</p>
                                </div>
                            </div>
                                
                                <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4>Status</h4></div>
                                <div class="panel-body">
                                    <%if(pedido.isAberto()){%>
                                    <h4><span class="label label-danger">Pedido em aberto</span></h4>
                                         <form action="../servletFecharPedido" onsubmit="return confirm('Deseja finalizar esse pedido?');" method="post">
                        <input type="hidden" name="id" value="<%= pedido.getId()%>">
                        <button type="submit" class="btn btn-sm btn-default"><span class="glyphicon glyphicon-ok"></span> Marcar pedido como concluído</button>
                    </form> 
                        <%}else{%>
                        <h4><span class="label label-success">Pedido concluído</span></h4>
                        <%}%>
                                    
                                </div>
                            </div>
                        </div>
                    </div>

                </div><!--/ateaki-9-->     
            </div>
        </div>

        <script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


        <script type='text/javascript' src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <%} else {
                response.sendRedirect("login.jsp");
            }%>
    </body>
   
</html>
