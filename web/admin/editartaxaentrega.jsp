
<%@page import="dao.TaxaEntregaDAO"%>
<%@page import="beans.TaxaEntrega"%>
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
        usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
        
        //Produto produto = new Produto();
        //ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try{
            TaxaEntrega taxaentrega = new TaxaEntrega();
        TaxaEntregaDAO taxaentregaDAO = new TaxaEntregaDAO();
          
           taxaentrega = taxaentregaDAO.consultarTaxaEntregaExata(usuarioestabelecimento.getEstabelecimento(), Float.parseFloat(request.getParameter("distancia")));
        
          //=       
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
                    <strong> Editar Taxa Entrega</strong>
                    <hr>
            <form class="form-horizontal" role="form" action="../servletEditarTaxaEntrega" method="post">
                <input type="hidden" value="<%= taxaentrega.getDistancia() %>" name="distancia">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Distância:</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="novadistancia" value="<%= taxaentrega.getDistancia() %>" required>
                    </div>
                    <label class="col-sm-1 control-label text-left">Km</label>
                </div>             
                <div class="form-group">
                    <label class="col-sm-2 control-label">Preço: R$</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="preco" placeholder="0.00" value="<%= taxaentrega.getPreco() %>"  required>
                    </div>
                </div>            
                <button type="submit" class="btn btn-primary col-lg-offset-2">Cadastrar</button>
                <a href="index.jsp" class="btn btn-primary">Cancelar</a>     
            </form>
        </div>
                     </div><!--/ateaki-9-->
        
    </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../util/js/bootstrap.min.js"></script>
    </body>
    <%
        }catch (Exception e){
           response.sendRedirect("taxadeentrega.jsp?erro=1"); 
        }
        
        }else{
            response.sendRedirect("login.jsp");
        
        }%>
</html>

