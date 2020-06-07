
<%
    String erro = request.getParameter("erro");
    if (erro == null) {
        erro = "10";
    }
    if (Integer.parseInt(erro) == 1) {%>
<div class="alert alert-danger">Ocorreu algo errado! Tente Novamente</div>
<%} else if (Integer.parseInt(erro) == 0) {%>
<div class="alert alert-success">Operação Realizada com Sucesso!</div>
<%} else if (Integer.parseInt(erro) == 2) {%>
<div class="alert alert-danger">Houve um erro! Havia produtos em seu Carrinho que já não estão mais disponíveis em estoque, Por Favor refaça suas compras de maneira correta!</div>
<%}%>