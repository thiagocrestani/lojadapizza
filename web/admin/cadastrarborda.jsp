
<%@page import="beans.UsuarioEstabelecimento"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="beans.Produto"%>
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
        int categoria = 0;
        //Produto produto = new Produto();
        //ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try{
            categoria = Integer.parseInt(request.getParameter("categoria"));
            //produto = produtoDAO.consultarProdutoId(Integer.parseInt(request.getParameter("id")));
        
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

                    <strong> Cadastrar novo tipo de borda</strong>

                    <hr>
            <form class="form-horizontal" role="form" action="../servletCadastrarBorda" method="post">
                <input type="hidden" class="form-control" name="idestabelecimento" value="<%= usuarioestabelecimento.getEstabelecimento()%>">
                <input type="hidden" class="form-control" name="idcategoria" value="<%= categoria%>">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Nome:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="nome">
                    </div>
                </div>             
                <div class="form-group">
                    <label class="col-sm-2 control-label">Preço: R$</label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="preco" placeholder="0.00"  required>
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
           response.sendRedirect("editarprodutoscategoria.jsp?erro=1"); 
        }
        
        }else{
            response.sendRedirect("login.jsp");
        
        }%>
</html>
