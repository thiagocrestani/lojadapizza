<% try{
    String sucesso = request.getParameter("sucesso");
if (Integer.parseInt(sucesso) == 1) {%>
<div class="alert alert-success">Mensagem enviada com sucesso!</div>
<%} 
}catch (Exception e){
    
}
%>

