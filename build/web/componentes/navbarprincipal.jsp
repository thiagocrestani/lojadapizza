<%@page import="beans.Endereco"%>
<%@page import="beans.Estabelecimento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.EstabelecimentoDAO"%>
<%

    try {
        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();

        List<Estabelecimento> listCategorias = new ArrayList<Estabelecimento>();
        Endereco endereco = new Endereco();

        listCategorias = estabelecimentoDAO.consultarTodasCategoriasEstabelecimento();
        if (session.getAttribute("localusuario") != null) {
            endereco = (Endereco) session.getAttribute("localusuario");

        } 

%>


<!-- begin template -->
<div class="navbar navbar-custom navbar-fixed-top">
    <div class="navbar-header"><a href="inicio.jsp"><img src="util/img/logonav.png" width="130px" height="auto"></a>
        <a class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class=" glyphicon glyphicon-search"></span>
        </a>
    </div>
    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Categorias <span class="caret"></span></a>
                <ul class="dropdown-menu" style="max-width: 160px">
                    <%for (int i = 0; i < listCategorias.size(); i++) {%>
                    <li><a href="procurarestabelecimento.jsp?categoria=<%= listCategorias.get(i).getCategoria()%>"><%= listCategorias.get(i).getCategoria()%></a></li>
                        <%}%>

                </ul>
            </li>

        </ul>
        <div class="col-sm-6">
            <form class="navbar-form" action="procurarestabelecimento.jsp" method="get">
                <div class="form-group" style="display:inline;">

                    <span class="input-group"> 

                        <input type="text" class="  search-query form-control" <%if (request.getParameter("procurar") != null) {%>value="<%= request.getParameter("procurar")%>"<%}%> id="busca" name="procurar" placeholder="Procurar por estabelecimentos..." style="background-color: #a6441a;border: 0px;color: #ffffff;"/>
                        <span class="input-group-btn">
                            <button class="btn" type="submit"  style="background-color: #a6441a;border-color:#a6441a; color: #ffffff;">
                                <span class=" glyphicon glyphicon-search"></span>
                            </button>      
                        </span>    
                    </span>     
                </div>
            </form>
        </div>

        <ul class="nav navbar-nav navbar-right" style="margin-right: 15px">
       <li >
                
                      <%if (session.getAttribute("localusuario") != null) {%><a href="index.jsp?alterar=true"><strong><%if (endereco.getBairro() != null) {%><%= endereco.getBairro()%> - <%}%><%= endereco.getCidade()%></strong></a><%}%>   
                
              </li>
                
                
                
            </ul>




    </div>
</div>

<%

} catch (Exception e) {
%><META http-equiv="refresh" content="0;URL=index.jsp?erro=1"> <%
}


%>
