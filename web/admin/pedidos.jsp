
<%@page import="beans.Pedido"%>
<%@page import="util.FormataData"%>
<%@page import="dao.CategoriaProdutoDAO"%>

<%@page import="dao.PedidoDAO"%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>

<!DOCTYPE html>

<html>
    <head>
    
   
        <link href="../util/css/bootstrap.min.css" rel="stylesheet">
        
        
        <title>Administrador</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%
       
        
        UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();   
        VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
        if(verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))){    
            
        
        
        
        PedidoDAO pedidoDAO = new PedidoDAO();
        FormataData formatadata = new FormataData();
        List<Pedido> listPedido = new ArrayList<Pedido>();
        CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO(); 
        int quantidadepedidos = 0;
        int quantidadepagina = 15;
        int pagina;
        int paginaparametro = 1;
        String status = "";
            try{       
                paginaparametro = Integer.parseInt(request.getParameter("pagina"));
                pagina = (Integer.parseInt(request.getParameter("pagina"))-1) * quantidadepagina;
                if(pagina <= 1){
                    pagina = 0;
                }
            }catch(Exception e){
                pagina = 0;
            }
        
        usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");      
       
        if(request.getParameter("aberto") != null){ 
            try{        
                listPedido = pedidoDAO.consultarPedidosCompleto(usuarioestabelecimento.getEstabelecimento(),quantidadepagina,pagina,Boolean.parseBoolean(request.getParameter("aberto")));
                quantidadepedidos = pedidoDAO.quantidadepedidos(usuarioestabelecimento.getEstabelecimento(),Boolean.parseBoolean(request.getParameter("aberto")));
                status="aberto="+request.getParameter("aberto")+"&";
            }catch(Exception e){
                quantidadepedidos = pedidoDAO.quantidadepedidos(usuarioestabelecimento.getEstabelecimento());
                listPedido = pedidoDAO.consultarPedidosCompleto(usuarioestabelecimento.getEstabelecimento(),quantidadepagina,pagina);
            }
        }else{
            quantidadepedidos = pedidoDAO.quantidadepedidos(usuarioestabelecimento.getEstabelecimento());
            listPedido = pedidoDAO.consultarPedidosCompleto(usuarioestabelecimento.getEstabelecimento(),quantidadepagina,pagina);
        }
                
        %>
    </head>
    <body>
       <jsp:include page = "componentes/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page = "componentes/navlateral.jsp"/>       
        <!-- /col-3 -->
        <div class="col-sm-9"><!-- /ate aki -->   
            <hr>

            <strong>Pedidos <span class="badge"><%= quantidadepedidos%></span></strong>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
            
            
            <div class="row col-lg-12">
                            <table class="table table-striped table-hover">
                                <tr><th>Número</th><th>Situação</th><th>Data</th><th>Cliente</th><th>Itens</th><th>Valor</th></tr>
                                        <%  for (int i = 0; i < listPedido.size(); i++) {%>
                                <tr onclick="location.href='detalhepedido.jsp?id=<%= listPedido.get(i).getId()%>';"><td>#<%= listPedido.get(i).getId()%></td>
                                    <td><%if(listPedido.get(i).isAberto()){%><span class="label label-danger">Aberto</span><%}else{%><span class="label label-success">Concluído</span><%}%></td>
                                    <td><%= listPedido.get(i).getDatapedidofomatada()%></td>
                                    <td><%=  listPedido.get(i).getCliente().getNome() %></td>
                                    <td><%= String.format("%.0f", (double) listPedido.get(i).getItens())%></td>
                                    <td>R$ <%= String.format("%.2f", (double) listPedido.get(i).getValor())%></td><tr>
                                    <%}%>
                            </table>
                            <nav>
  <div class="col-lg-12 text-center"><center><ul class="pagination">
    <%if(paginaparametro > 1){%>  
      <li>      
          <a href="pedidos.jsp?<%=status%>pagina=<%= (paginaparametro-1)%>"  aria-label="Previous">    
        <span aria-hidden="true">&laquo;</span>
      </a>     
    </li>
    <%}else{%>
     <li class="disabled">     
      <a href="#"  aria-label="Previous">    
        <span aria-hidden="true">&laquo;</span>
      </a>   
    </li>
    <%}%>
    
    <%if(paginaparametro-1 > 1){%>
    <li><a href="pedidos.jsp?<%=status%>pagina=<%= paginaparametro-2%>"><%= paginaparametro-2%></a></li>
    <%}%>
    <%if(paginaparametro > 1){%>
    <li><a href="pedidos.jsp?<%=status%>pagina=<%= paginaparametro-1%>"><%= paginaparametro-1%></a></li>
    <%}%> 
    <li class="active"><a href="#"><%= paginaparametro%></a></li>
    <%if(paginaparametro*quantidadepagina < quantidadepedidos){%>
    <li><a href="pedidos.jsp?<%=status%>pagina=<%= paginaparametro+1%>"><%= paginaparametro+1%></a></li>
    <%}%>
    <%if(((paginaparametro+1)*quantidadepagina) < quantidadepedidos){%>
    <li><a href="pedidos.jsp?<%=status%>pagina=<%= paginaparametro+2%>"><%= paginaparametro+2%></a></li>
    <%}%>  
    <%if(paginaparametro*quantidadepagina < quantidadepedidos){%>  
      <li>
        <a href="pedidos.jsp?<%=status%>pagina=<%= (paginaparametro+1)%>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    <%}else{%>
     <li class="disabled">     
      <a href="#"  aria-label="Previous">    
        <span aria-hidden="true">&raquo;</span>
      </a>   
    </li>
    <%}%>
    
          </ul></center></div>
</nav>
            </div>
                     </div><!--/ateaki-9-->
        
    </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../util/js/bootstrap.min.js"></script>
    </body>
    <%}else{
            response.sendRedirect("login.jsp");
        
        }%>
</html>
