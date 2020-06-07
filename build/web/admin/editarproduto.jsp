
<%@page import="util.CategoriaUsa"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="beans.Produto"%>
<!DOCTYPE html>

<html>
    <head>
    
   
        <link href="../util/css/bootstrap.min.css" rel="stylesheet">
        
        
        <title>Administrador</title>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%     
        UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();   
        VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
        if(verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))){    
        usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");     
        Produto produto = new Produto();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaUsa categoriausa = new CategoriaUsa();
 
        Boolean alterarfoto = false;
        
        try{
            produto = produtoDAO.consultarProdutoId(Integer.parseInt(request.getParameter("id")));
            
        }catch (Exception e){
            
        }
        
        try{
        if(request.getParameter("alterarfoto") != null){
            alterarfoto = true;
        }
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

                    <strong> Editar Produto</strong>

                    <hr>
       
            <%if(!alterarfoto){%>
            <form class="form-horizontal" role="form" action="../servletEditarProduto" method="post">
             <%}else{%>
             <form class="form-horizontal" action="http://localhost:8080/pizzaimagens/servletEditarProduto" method="post" enctype="multipart/form-data">
             <%}%>
            <input type="hidden" name="id" value="<%= produto.getId()%>">
            
            <input type="hidden" name="categoria" value="<%= produto.getIdcategoria()%>">
                
            <div class="form-group">
                    <label class="col-sm-2 control-label">Nome:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="nome" value="<%= produto.getNome()%>">
                    </div>
                </div>
                
                          
                           
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Descrição</label>
                    <div class="col-sm-6">
                        <textarea rows="3" class="form-control" name="descricao" ><%= produto.getDescricao()%></textarea>
                    </div>
                </div>

                <%if (!categoriausa.tamanho(produto.getIdcategoria())) {%>       
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Preço: R$</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="preco" placeholder="0.00" value="<%= produto.getPreco()%>"  required>
                            </div>
                        </div>
                        <%}%>
                        

               
             

              
                <%
                         String nome = produto.getFoto().substring(produto.getFoto().lastIndexOf("/"));
                         nome = nome.replace("/", "");
                        int ponto = nome.indexOf(".");
                        %>
                <%if(!alterarfoto){%>
                
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Foto:</label>
                    <div class="col-sm-6">
                        <input type="hidden" name="alterarfoto" value="false">
                         <input type="hidden" name="foto" value="<%= nome%>">
                        <img src="<%= produto.getFoto()%>" width="100px" height="100px"><br>
                        <a href="editarproduto.jsp?id=<%= produto.getId()%>&alterarfoto=true">alterar foto</a>
                    </div>
                </div>
                        <%}else{%>
                        <input type="hidden" name="alterarfoto" value="true">
                        
                        <input type="hidden" name="nomefoto" value="<%= nome.substring(0, ponto) %>">
                        <div class="form-group">
    <label class="col-sm-2 control-label">Selecione uma foto</label>
     <div class="col-sm-6">
                             
    <input id="fileName" class="form-control" type="file" name="fileName"/>
                            </div>
  </div>
                        
                        <%}%>
                
                
                
                <button type="submit" class="btn btn-primary col-lg-offset-2">Cadastrar</button>
                <a href="consultarprodutos.jsp" class="btn btn-primary">Cancelar</a>
               
            </form>
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