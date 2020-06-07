
<%@page import="dao.CategoriaProdutoDAO"%>
<%@page import="dao.CategoriaDAO"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>
<%@page import="dao.UsuarioEstabelecimentoDAO"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
            if (verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))) {
                try{
                Produto produto = new Produto();
                ProdutoDAO produtoDAO = new ProdutoDAO();
                CategoriaProdutoDAO categoriaDAO = new CategoriaProdutoDAO();
                List<Produto> listProduto = new ArrayList<Produto>();
                usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");    
                if (request.getParameter("categoria") != null) {
                    listProduto = produtoDAO.consultarProdutoCategoria(usuarioestabelecimento.getEstabelecimento(),Integer.parseInt(request.getParameter("categoria")));
                }else{
                    listProduto = produtoDAO.consultarProdutos(usuarioestabelecimento.getEstabelecimento());
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

                 <strong>Produtos Cadastrados</strong>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
               
                    
                        <div class="row">
                            
                            <div class="col-lg-8">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Procurar">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">Procurar</button>
                                        
                                    </span>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-4">
                            <a href="editarprodutoscategoria.jsp" class="btn btn-primary">Categorias</a>
                            <a href="vercategorias.jsp" class="btn btn-primary">Novo Produto</a>
                            </div>
                        </div>
                        <div class="row col-lg-12">
                            <table class="table table-striped">
                                <tr><th>ID</th><th>Categoria</th><th>Nome</th><th>Excluir</th><th>Editar</th></tr>
                                        <%  for (int i = 0; i < listProduto.size(); i++) {%>
                                <tr><td><%= listProduto.get(i).getId()%></td>
                                    <td><%=  categoriaDAO.consultarCategoriaProdutoEstabelecimentoId(listProduto.get(i).getIdcategoria()).getNome() %></td>
                                    <td>
                                        <%= listProduto.get(i).getNome()%></td>
                                   

                                    <td><form action="../servletExcluirProduto" onsubmit="return confirm('Deseja excluir este item?');" method="post"><input type="hidden" name="id" value="<%= listProduto.get(i).getId()%>"><button type="submit" class="btn btn-primary">Excluir</button></form></td>
                                    <td><a href="editarproduto.jsp?id=<%= listProduto.get(i).getId()%>">Editar</a></td></tr>
                                    <%
                                        }
                                    %>
                            </table>
                        </div>
                    </div>
                </div><!--/ateaki-9-->

            </div>
      
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../util/js/bootstrap.min.js"></script>
        <%
                                        }catch(Exception e){
             response.sendRedirect("vercategorias.jsp?erro=1"); 
          }
            } else {
                response.sendRedirect("login.jsp");
            }

        %>
    </body>
</html>