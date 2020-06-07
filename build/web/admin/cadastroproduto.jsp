
<%@page import="dao.CategoriaProdutoDAO"%>
<%@page import="util.CategoriaUsa"%>
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
            if (verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))) {
                try {
                    usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
                    CategoriaUsa categoriausa = new CategoriaUsa();
                    CategoriaProdutoDAO categoriaprodutoDAO = new CategoriaProdutoDAO();
                    if (categoriausa.tamanho(Integer.parseInt(request.getParameter("categoria")))) {
                        if (categoriaprodutoDAO.consultarCategoriaProdutoEstabelecimentoBuscaCompleto(Integer.parseInt(request.getParameter("categoria")), usuarioestabelecimento.getEstabelecimento()).getTamanho().size() <= 0) {
                            response.sendRedirect("editarprodutoscategoria.jsp?idcategoria=" + request.getParameter("categoria") + "&erro=6");
                        }
                        
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

                    <strong> Cadastrar novo Produto</strong>

                    <hr>

                    <form class="form-horizontal" action="http://localhost:8080/pizzaimagens/servletCadastroProduto" method="post" enctype="multipart/form-data">
                        <input type="hidden" class="form-control" name="idestabelecimento" value="<%= usuarioestabelecimento.getEstabelecimento()%>">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Nome:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="nome">
                            </div>
                        </div>


                        <input type="hidden" class="form-control" name="categoria" value="<%= request.getParameter("categoria")%>">


                        <div class="form-group">
                            <label class="col-sm-2 control-label">Descrição</label>
                            <div class="col-sm-6">
                                <textarea rows="3" class="form-control" name="descricao"></textarea>
                            </div>
                        </div>


                        <%if (!categoriausa.tamanho(Integer.parseInt(request.getParameter("categoria")))) {%>       

                        <div class="form-group">
                            <label class="col-sm-2 control-label">Preço: R$</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" name="preco" placeholder="0.00"  required>
                            </div>
                        </div>
                        <%} else {%>
                        <input type="hidden"  name="preco"  value="0" >
                        <%}%>

                        
                       
<div class="form-group">
    <label class="col-sm-2 control-label">Selecione uma foto</label>
     <div class="col-sm-6">
                             
    <input id="fileName" class="form-control" type="file" name="fileName"/>
                            </div>
  </div>
             
                        <button type="submit" class="btn btn-primary col-lg-offset-2">Cadastrar</button>
                        <a href="consultarprodutos.jsp" class="btn btn-primary">Cancelar</a>     
                    </form>
                </div>
            </div><!--/ateaki-9-->

        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../util/js/bootstrap.min.js"></script>
    </body>
    <%
            } catch (Exception e) {
                response.sendRedirect("vercategorias.jsp?erro=1");
            }
        } else {
            response.sendRedirect("login.jsp");

        }%>
</html>