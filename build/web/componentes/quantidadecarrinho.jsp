

<%@page import="beans.Carrinho"%>

<%

    Carrinho carrinho = new Carrinho();
    int quantidade = 0;
    
        if (session.getAttribute("carrinho") != null) {
            carrinho = (Carrinho) session.getAttribute("carrinho");
            quantidade = carrinho.getQuantidade();
        }

   
    


%>
<%= quantidade%>
<input id="quantidadequantidade" type="hidden" value="<%= quantidade%>">
<input id="estabelecimentoquantidade" type="hidden" value="<%= carrinho.getEstabelecimento()%>">

