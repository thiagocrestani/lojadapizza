
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
        HorarioFuncionamento horariofuncionamento = new HorarioFuncionamento();
        HorarioFuncionamentoDAO horariofuncionamentoDAO = new HorarioFuncionamentoDAO();
       
        SimpleDateFormat horarioantigo = new SimpleDateFormat("HHmm");
        SimpleDateFormat horarionovo = new SimpleDateFormat("HH:mm");
        
        estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(usuarioestabelecimento.getEstabelecimento());
        horariofuncionamento = horariofuncionamentoDAO.consultar(estabelecimento.getId());
        
        %>
        
    </head>
    <body>
       
<jsp:include page = "componentes/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <jsp:include page = "componentes/navlateral.jsp"/>       
        <!-- /col-3 -->
        <div class="col-sm-9"><!-- /ate aki -->
            <jsp:include page = "componentes/erro.jsp"/>
                    <jsp:include page = "componentes/sucesso.jsp"/>
            <hr>
            <strong> Dados gerais</strong>
            <hr>
             <form class="form-horizontal" role="form" action="../servletEditarEstabelecimento" method="post">
                
                <input type="hidden" class="form-control" name="idestabelecimento" value="<%= usuarioestabelecimento.getEstabelecimento()%>">
      
                <div class="form-group">
                    <label class="col-sm-2 control-label">Nome:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="nome"  value="<%= estabelecimento.getNome()%>" readonly="">                    
                    </div> 
                </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Email:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="email"  value="<%= estabelecimento.getEmail()%>">                    
                    </div> 
                </div> 
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Telefone:</label>
                    <div class="col-sm-1">
                        <input type="text" class="form-control" name="ddd"  value="<%= estabelecimento.getDdd()%>">                    
                    </div> 
                    <div class="col-sm-5">
                        <input type="text" class="form-control" name="telefone"  value="<%= estabelecimento.getTelefone()%>">                    
                    </div> 
                </div> 
             
            <hr>
            <strong> Endereço</strong>
            <hr>
            
            
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Rua:</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="rua"  value="<%= estabelecimento.getRua()%>">                    
                    </div> 
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="numero" value="<%= estabelecimento.getNumero()%>">                    
                    </div> 
                </div> 
                    
                <div class="form-group">
                    <label class="col-sm-2 control-label">Bairro:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="bairro"  value="<%= estabelecimento.getBairro()%>">                    
                    </div> 
                </div>
                    
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Complemento:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="complemento"  value="<%= estabelecimento.getComplemento()%>">                    
                    </div> 
                </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Ponto de Referência:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="pontodereferencia"  value="<%= estabelecimento.getPontodereferencia()%>">                    
                    </div> 
                </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Cep:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="cep"  value="<%= estabelecimento.getCep()%>" readonly="">                    
                    </div> 
                </div>
                    
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Cidade:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="cidade"  value="<%= estabelecimento.getCidade()%>" readonly="">                    
                    </div> 
                </div>
                    
                    <div class="form-group">
                    <label class="col-sm-2 control-label">Estado:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="estado" value="<%= estabelecimento.getEstado()%>" readonly="">                    
                    </div> 
                </div>
                    
                    <div class="form-group">
                    <label class="col-sm-2 control-label" >País:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="pais" value="<%= estabelecimento.getPais()%>" readonly="">                    
                    </div> 
                </div>
                    
                    
      
                <button type="submit" class="btn btn-primary col-lg-offset-2">Cadastrar</button>
                <a href="index.jsp" class="btn btn-primary">Cancelar</a>          
             </form>
      
            
             <hr>
            <strong> Horário de Funcionamento</strong>
            <hr>
            <form class="form-horizontal" role="form" action="../servletEditarDadosEstabelecimento" method="post">
                
                <input type="hidden" class="form-control" name="idestabelecimento" value="<%= usuarioestabelecimento.getEstabelecimento()%>">
      
                <div class="form-group">
                    <label class="col-sm-2 control-label">Domingo:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="domingo" value="true" <%if(horariofuncionamento.getDia().get(0).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="adomingo" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(0).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fdomingo" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(0).getF()))%>">    
                    </div>
                </div> 
                
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Segunda:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="segunda" value="true" <%if(horariofuncionamento.getDia().get(1).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="asegunda" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(1).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fsegunda" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(1).getF()))%>">    
                    </div>
                </div> 
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Terça:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="terca" value="true" <%if(horariofuncionamento.getDia().get(2).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="aterca" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(2).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fterca" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(2).getF()))%>">    
                    </div>
                </div> 
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Quarta:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="quarta" value="true" <%if(horariofuncionamento.getDia().get(3).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="aquarta" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(3).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fquarta" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(3).getF()))%>">    
                    </div>
                </div> 
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Quinta:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="quinta" value="true" <%if(horariofuncionamento.getDia().get(4).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="aquinta" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(4).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fquinta" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(4).getF()))%>">    
                    </div>
                </div> 
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Sexta:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="sexta" value="true" <%if(horariofuncionamento.getDia().get(5).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="asexta" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(5).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fsexta" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(5).getF()))%>">    
                    </div>
                </div> 
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Sábado:</label>
                    <div class="col-sm-1">
                        <label class="checkbox-inline"><input type="checkbox" name="sabado" value="true" <%if(horariofuncionamento.getDia().get(6).isFunciona()){%>checked<%}%>>Abre</label>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="asabado" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(6).getA()))%>">
                        
                    </div>
                    
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="fsabado" value="<%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(6).getF()))%>">    
                    </div>
                </div> 
                   
                    
        
                <button type="submit" class="btn btn-primary col-lg-offset-2">Cadastrar</button>
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