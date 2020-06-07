<%@page import="dao.PedidoDAO"%>
<%
    try{
    PedidoDAO pedidoDAO = new PedidoDAO();
    int quantidade = 0, diferenca= 0;
    
        if (request.getParameter("qtd") != null) {
            quantidade = Integer.parseInt(request.getParameter("qtd"));
        }
        diferenca = pedidoDAO.quantidadepedidos(Integer.parseInt(request.getParameter("idestabelecimento"))) - quantidade;
        
    


%>

<%if(diferenca > 0){%>
<%if(diferenca > 1){%>
<a href="index.jsp"><span class="badge"><%= diferenca%> Novos</span></a>
<%}else{%>
<a href="index.jsp"><span class="badge"><%= diferenca%> Novo</span></a>
<%}}
    }catch(Exception e){
        
    }

%>
