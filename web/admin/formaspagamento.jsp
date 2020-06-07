
<%@page import="dao.FormaPagamentoEstabelecimentoDAO"%>
<%@page import="dao.FormaPagamentoDAO"%>
<%@page import="beans.FormaPagamento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.HorarioFuncionamentoDAO"%>
<%@page import="beans.HorarioFuncionamento"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        
        List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
        List<Integer> listIdFormaPagamentoEstabelecimento = new ArrayList<Integer>();
        
        FormaPagamentoEstabelecimentoDAO formaPagamentoestabelecimentoDAO = new FormaPagamentoEstabelecimentoDAO();
        FormaPagamentoDAO formapagamentoDAO = new FormaPagamentoDAO();
        listFormaPagamento = formapagamentoDAO.consultarTodas();
       
        estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(usuarioestabelecimento.getEstabelecimento());
        listIdFormaPagamentoEstabelecimento = formaPagamentoestabelecimentoDAO.consultarIdFormasPagamentoEstabelecimento(usuarioestabelecimento.getEstabelecimento());
        
        %>
        
    </head>
    <body>
       
<jsp:include page = "componentes/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page = "componentes/navlateral.jsp"/>       
        <!-- /col-3 -->
        <div class="col-sm-9"><!-- /ate aki -->
            
      
            <form class="form-horizontal" role="form" action="../servletEditarFormasPagamento" method="post">
                
                <input type="hidden" class="form-control" name="idestabelecimento" value="<%= usuarioestabelecimento.getEstabelecimento()%>">
       
                    <hr>
            <strong> Formas de pagamento</strong>
            <hr>
            
            <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
            <% for(int i =0;i < listIdFormaPagamentoEstabelecimento.size();i++){ %>
            

            
            <%}%>
            
            <% for(int i =0;i < listFormaPagamento.size();i++){ %>
            <div class="checkbox">
                <label><input type="checkbox" name="forma<%=listFormaPagamento.get(i).getId()%>" value="<%=listFormaPagamento.get(i).getId()%>" <%if(listIdFormaPagamentoEstabelecimento.contains(listFormaPagamento.get(i).getId())){%>checked<%}%>><%= listFormaPagamento.get(i).getNome() %></label>
            </div>     
            <%}%>
                    
                <hr>
                <button type="submit" class="btn btn-primary">Editar</button>
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