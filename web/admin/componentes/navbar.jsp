<%@page import="dao.PedidoDAO"%>
<%@page import="util.VerificaUsuarioEstabelecimento"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<%@page import="beans.UsuarioEstabelecimento"%>
<%

            UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
            VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
            if (verificausuarioestabelecimento.verificar((UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento"))) {
            
            
%>
   
        

<!-- header -->
<div id="top-nav" class="navbar navbar-inverse navbar-static-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">Painel de Controle</a>
        </div>
        <div class="navbar-collapse collapse">
            
            <ul class="nav navbar-nav navbar-right">
               
                <li><a href="../sevletLogoutUsuarioEstabelecimento?"><i class="glyphicon glyphicon-lock"></i> Sair</a></li>
            </ul>
        </div>
    </div>
    <!-- /container -->
</div>

<%}%>
