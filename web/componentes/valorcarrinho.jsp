

<%@page import="beans.Carrinho"%>

<%

    Carrinho carrinho = new Carrinho();
    double valor = 0;
    
        if (session.getAttribute("carrinho") != null) {

            carrinho = (Carrinho) session.getAttribute("carrinho");
            valor = carrinho.getTotal();
        }

        out.print(String.format("%.2f", (double) valor));
    


%>
