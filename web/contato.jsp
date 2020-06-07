
<!DOCTYPE html>
<html lang="pt">
    <head>
    <meta charset="utf-8">
    <title>Contato</title>
        <link rel="shortcut icon" href="util/img/favicon.ico" type="image/x-icon"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="./util/css/bootstrapnav.min.css" rel="stylesheet">   
        <link href="util/css/estilo.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="util/css/estilo2.css" rel="stylesheet" id="bootstrap-css"> 
        <link href="./util/css/stylesnav.css" rel="stylesheet">
        <!-- All the files that are required -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
      <%
          String assunto = "";
      if(request.getParameter("assunto") != null){
          assunto = request.getParameter("assunto") + ": <Digite o nome de seu bairro>";
      }
      
      %>
       
         

    </head>
    <body >
        <jsp:include page = "componentes/navbarprincipal.jsp"/>
        <div class="container">
            <div class="row">
                <div class="board">
                   
                        

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="home">

                            <h3 class="head text-center">Contato</h3>
                            <form class="form-horizontal col-lg-6 col-lg-offset-3" id="form" action="servletContato" method="post" >
                                <jsp:include page = "componentes/erro.jsp"/>
                                <jsp:include page = "componentes/sucesso.jsp"/>
                                <div class="form-group">    
                                    <div class="col-sm-12 input-group-lg">
                                        <input type="text" class="form-control" name="nome" id="nome" placeholder="Nome *" required>
                                    </div>
                                      
                                </div>             
                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="email" class="form-control" name="email" placeholder="Email *" required>                                   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <input type="text" class="form-control" name="telefone" placeholder="Telefone">                                   
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-12 input-group-lg"> 
                                        <textarea class="form-control" name="mensagem" placeholder="Mensagem *" rows="3" style="height: 90px" required=""><%=assunto%></textarea>                                
                                    </div>
                                </div>
                             
                                    
                                <p class="text-center">
                                    <button type="submit" class="btn btn-warning btn-outline-rounded green">Continuar <span class="glyphicon glyphicon-chevron-right"></span> </button> 
                                </p>
                            </form>
                            
                           
                        </div>
                        <div class="clearfix"></div>
                    </div>

                </div>
            </div>
        </div>
                                
                                    
                                    <jsp:include page = "componentes/rodape.jsp"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./util/js/bootstrap.min.js"></script>
    </body>
    
   
</html>
