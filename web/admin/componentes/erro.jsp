<% try{
    String erro = request.getParameter("erro");
if (Integer.parseInt(erro) == 1) {%>
<div class="alert alert-danger">Ocorreu algo errado! Tente Novamente</div>
<%} else if (Integer.parseInt(erro) == 2) {%>
<div class="alert alert-danger">Houve um erro! Havia produtos em seu Carrinho que já não estão mais disponíveis em estoque, Por Favor refaça suas compras de maneira correta!</div>
<%} else if (Integer.parseInt(erro) == 3) {%>
<div class="alert alert-danger">Usuário ou senha incorretos</div>
<%} else if (Integer.parseInt(erro) == 6) {%>
<div class="alert alert-danger">Você deve cadastrar pelo menos 1 TAMANHO antes de cadastrar produtos nessa categoria!</div>
<%} else if (Integer.parseInt(erro) == 7) {%>
<div class="alert alert-danger">Você deve cadastrar pelo menos 1 tipo de BORDA antes de cadastrar produtos nessa categoria!</div>
<%}
}catch (Exception e){
    
}
%>
