
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoriaProdutoDAO"%>
<%@page import="beans.CategoriaProduto"%>
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
        
        List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
        CategoriaProdutoDAO categoriaprodutoDAO = new CategoriaProdutoDAO();
        
        try{
            listCategoriaProduto = categoriaprodutoDAO.consultarCategoriaProduto();
        }catch (Exception e){
            
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

                 <strong>Categorias</strong>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
            
            
            <div class="list-group"> 
            <%  for (int i = 0; i < listCategoriaProduto.size(); i++) {%>
            <form class="list-group-item" role="form" action="cadastroproduto.jsp" method="post">
             <input type="hidden" name="categoria" value="<%= listCategoriaProduto.get(i).getId()%>">                 
                <button type="submit" class="btn btn-link" ><%= listCategoriaProduto.get(i).getNome()%></button>    
               </form>   
            <%}%>
           </div>
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
