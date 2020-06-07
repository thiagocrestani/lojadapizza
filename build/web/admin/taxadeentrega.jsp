
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.TaxaEntregaDAO"%>
<%@page import="beans.TaxaEntrega"%>
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
        TaxaEntregaDAO taxaEntregaDAO = new TaxaEntregaDAO();
        List<TaxaEntrega> listTaxaEntrega = new ArrayList<TaxaEntrega>();
        estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(usuarioestabelecimento.getEstabelecimento());
        listTaxaEntrega = taxaEntregaDAO.consultarTaxaEntregaEstabelecimento(estabelecimento.getId());
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

                    <strong> Taxa de Entrega</strong>

                    <hr>
                    <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
                    
                    
                    
                    <%for (int i = 1; i < listTaxaEntrega.size(); i++) {%>
                    <form action="../servletExcluirTaxaEntrega" onsubmit="return confirm('Deseja excluir este item?');" method="post">
                        <b><%= listTaxaEntrega.get(i).getDistancia()%> Km</b> - R$ <%= String.format("%.2f", (double) listTaxaEntrega.get(i).getPreco())%>  
                        <input type="hidden" name="distancia" value="<%= listTaxaEntrega.get(i).getDistancia()%>">
                        <a href="editartaxaentrega.jsp?distancia=<%= listTaxaEntrega.get(i).getDistancia()%>">Editar</a> 
                        <button type="submit" class="btn btn-link"> Excluir</button>
                    </form>                
                    <%}%>
                    <a  class="btn btn-default" href="cadastrartaxaentrega.jsp">Nova Distância</a><br><br>
                    
           
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