
<%
    String erro = request.getParameter("erro");
    if (erro == null) {
        erro = "10";
    }
    if (Integer.parseInt(erro) == 1) {%>
<div class="alert alert-danger">Ocorreu algo errado! Tente Novamente</div>
<%} else if (Integer.parseInt(erro) == 0) {%>
<div class="alert alert-success">Opera��o Realizada com Sucesso!</div>
<%} else if (Integer.parseInt(erro) == 2) {%>
<div class="alert alert-danger">Houve um erro! Havia produtos em seu Carrinho que j� n�o est�o mais dispon�veis em estoque, Por Favor refa�a suas compras de maneira correta!</div>
<%}%>