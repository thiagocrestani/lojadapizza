
<%@page import="util.CategoriaUsa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoriaProdutoDAO"%>
<%@page import="beans.CategoriaProduto"%>
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
            if (verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))) {
                try {
                    usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
                    List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
                    CategoriaProdutoDAO categoriaprodutoDAO = new CategoriaProdutoDAO();
                    CategoriaUsa categoriausa = new CategoriaUsa();
                    if (request.getParameter("idcategoria") != null) {

                        listCategoriaProduto.add(categoriaprodutoDAO.consultarCategoriaProdutoEstabelecimentoBuscaCompleto(Integer.parseInt(request.getParameter("idcategoria")), usuarioestabelecimento.getEstabelecimento()));
                    } else if (request.getParameter("nomecategoria") != null) {
                        listCategoriaProduto = categoriaprodutoDAO.consultarCategoriaProdutoEstabelecimentoCompleto(usuarioestabelecimento.getEstabelecimento(), request.getParameter("nomecategoria"));
                    } else {
                        listCategoriaProduto = categoriaprodutoDAO.consultarCategoriaProdutoEstabelecimentoCompleto(usuarioestabelecimento.getEstabelecimento());
                    }

        %>
    </head>
    <body>
        <jsp:include page = "componentes/navbar.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <jsp:include page = "componentes/navlateral.jsp"/>       
                <!-- /col-3 -->
                <div class="col-sm-9">

                     <hr>

                 <strong>Categorias Cadastradas</strong>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
                    
                    <form class="col-lg-12" action="editarprodutoscategoria.jsp" method="post">
                        <div class="input-group">
                            <input type="text" name="nomecategoria" class="form-control" placeholder="Procurar Categoria...">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button">Procurar</button>
                            </span>
                        </div>
                    </form>
                    <br>
                    <hr>

                    <div class="row">
                        <div class="col-md-12">
                                
                                         
                    <%for (int i = 0; i < listCategoriaProduto.size(); i++) {%>
                    <div class="panel panel-default">
                    <div class="panel-heading">
                    <h4><%= listCategoriaProduto.get(i).getNome()%></h4></div>
                     <div class="panel-body">
                    
                    <%if (categoriausa.tamanho(listCategoriaProduto.get(i).getId())) {%>
                    <h4>Tamanho</h4>
                    <%for (int j = 0; j < listCategoriaProduto.get(i).getTamanho().size(); j++) {%>
                    <%if (listCategoriaProduto.get(i).getTamanho().size() > 1) {%>
                    <form action="../servletExcluirTamanho" onsubmit="return confirm('Deseja excluir este item?');"  method="post">
                        <input type="hidden" name="id" value="<%= listCategoriaProduto.get(i).getTamanho().get(j).getId()%>">
                        <b><%= listCategoriaProduto.get(i).getTamanho().get(j).getNome()%>:</b> R$ <%= String.format("%.2f", (double) listCategoriaProduto.get(i).getTamanho().get(j).getPreco())%> | <b>Sabores:</b> <%= listCategoriaProduto.get(i).getTamanho().get(j).getQuantidadesabores()%> 
                        <a href="editartamanho.jsp?id=<%= listCategoriaProduto.get(i).getTamanho().get(j).getId()%>">Editar</a>
                        <button type="submit" class="btn btn-link"> Excluir</button>
                    </form>  
                    <%} else {%>
                    <b><%= listCategoriaProduto.get(i).getTamanho().get(j).getNome()%>:</b> R$ <%= String.format("%.2f", (double) listCategoriaProduto.get(i).getTamanho().get(j).getPreco())%>  | <b>Sabores:</b> <%= listCategoriaProduto.get(i).getTamanho().get(j).getQuantidadesabores()%>
                    <a href="editartamanho.jsp?id=<%= listCategoriaProduto.get(i).getTamanho().get(j).getId()%>">Editar</a><br>
                    <%}%>
                    <%}%>
                    <a  class="btn btn-default" href="cadastrartamanho.jsp?categoria=<%= listCategoriaProduto.get(i).getId()%>">Novo Tamanho</a><br><br>
                    <hr>
                    <%}%>
                     
                    <%if (categoriausa.borda(listCategoriaProduto.get(i).getId())) {%>
                    <h4>Borda</h4>
                    <%for (int j = 1; j < listCategoriaProduto.get(i).getBorda().size(); j++) {%>
                    <form action="../servletExcluirBorda" onsubmit="return confirm('Deseja excluir este item?');" method="post">
                        <input type="hidden" name="id" value="<%= listCategoriaProduto.get(i).getBorda().get(j).getId()%>">
                        <b><%= listCategoriaProduto.get(i).getBorda().get(j).getNome()%></b> - R$ <%= String.format("%.2f", (double) listCategoriaProduto.get(i).getBorda().get(j).getPreco())%>  
                        <a href="editarborda.jsp?id=<%= listCategoriaProduto.get(i).getBorda().get(j).getId()%>">Editar</a>
                        <button type="submit" class="btn btn-link"> Excluir</button>
                    </form>                
                    <%}%>
                    <a  class="btn btn-default" href="cadastrarborda.jsp?categoria=<%= listCategoriaProduto.get(i).getId()%>">Nova Borda</a><br><br>
                     <hr>
                    <%}%>
                    
                    <form class="form-horizontal" role="form" action="cadastroproduto.jsp" method="post">
                        
                        <input type="hidden" name="categoria" value="<%= listCategoriaProduto.get(i).getId()%>">
                        <div class="btn-group" role="group" aria-label="...">
                        <a href="consultarprodutos.jsp?categoria=<%= listCategoriaProduto.get(i).getId()%>" class="btn btn-warning">Ver produtos dessa categoria</a>    
                        <button type="submit" class="btn btn-warning">Novo produto nessa categoria</button>    
                    </form>
                    </div>
                    </div>
                            </div>       
                    <%}%>
                    </div>
                    </div>
                </div>
            </div><!--/ateaki-9-->

        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../util/js/bootstrap.min.js"></script>
        <script src="../util/js/funcoes.js"></script>
    </body>
    <%
            } catch (Exception e) {
                response.sendRedirect("index.jsp?erro=1" + e);
            }
        } else {
            response.sendRedirect("login.jsp");

        }%>
</html>
