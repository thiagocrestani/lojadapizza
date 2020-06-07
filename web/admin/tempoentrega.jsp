
<%@page import="dao.EstabelecimentoDAO"%>
<%@page import="beans.Estabelecimento"%>
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
        Estabelecimento estabelecimento = new Estabelecimento();
        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
        estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(usuarioestabelecimento.getEstabelecimento());
        
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

                    <strong> Tempo até a entrega</strong>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
            
            <form class="form-horizontal" role="form" action="../servletCadastrarTempoEntrega" method="post">
                <input type="hidden" class="form-control" name="idestabelecimento" value="<%= usuarioestabelecimento.getEstabelecimento()%>">
       <%if(estabelecimento.isEntrega()){%>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Tempo de entrega:</label>
                    <div class="col-sm-1">
                        <input type="text" class="form-control" name="tempoentrega" value="<%= estabelecimento.getTempoentrega()%>">
                    </div>
                    <label class=" control-label">Minutos</label>
                </div>  
                    <%}else{%>
                    <input type="hidden" name="tempoentrega" value="0">
                    <%}%>
                    
                    <%if(estabelecimento.isRetirada()){%>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Tempo de retirada:</label>
                    <div class="col-sm-1">
                        <input type="text" class="form-control" name="temporetirada" value="<%= estabelecimento.getTemporetirada()%>">
                    </div>
                    <label class=" control-label">Minutos</label>
                </div> 
                    <%}else{%>
                    <input type="hidden" name="temporetirada" value="0">
                    <%}%>
                <button type="submit" class="btn btn-primary">Cadastrar</button>
                <a href="index.jsp" class="btn btn-primary">Cancelar</a>     
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