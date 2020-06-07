

  <%
       
     try{ 
         
         %>
         <%@page import="beans.Endereco"%>
<%@page import="util.VerificaAberto"%>
<%@page import="beans.Estabelecimento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.EstabelecimentoDAO"%>
         
         <%
            EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
            List<Estabelecimento> listEstabelecimento = new ArrayList<Estabelecimento>();
            listEstabelecimento = estabelecimentoDAO.consultarEstabelecimentoMaisPedidos(4);
            VerificaAberto verificaaberto = new VerificaAberto();
 
            Endereco endereco = new Endereco();     
            
            if(session.getAttribute("localusuario") != null){
                endereco = (Endereco) session.getAttribute("localusuario");    
            }else{
                response.sendRedirect("index.jsp?url=inicio.jsp"); 
            }  
        %>

<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Loja da Pizza</title>
        <link rel="shortcut icon" href="util/img/favicon.ico" type="image/x-icon"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Le styles -->
        
      
     
  <link href="./util/css/prettyPhoto.css" rel="stylesheet">
        <link href="./util/css/custom.css" rel="stylesheet">
        <link href="./util/css/bootstrap-responsive.css" rel="stylesheet">
        
        <link href="./util/css/bootstrapnav.min.css" rel="stylesheet">
        <link href="./util/css/stylesnav.css" rel="stylesheet">
       
        <link href="./util/css/estilo2.css" rel="stylesheet">
         <link href="./util/css/estilo.css" rel="stylesheet">


        <script src="./util/js/jquery-1.9.0.min.js"></script>

        <script>
            
             
                var progressElem, statusElem;
                var supportsProgress;
                var loadedImageCount, imageCount;
               
               
                function loadcarregar(){
                    
                <%for (int j = 0; j < listEstabelecimento.size(); j++) {

        
        
        %>
    if (document.getElementById('image-container<%= listEstabelecimento.get(j).getId()%>') != null) {
          
    carregar('#image-container<%= listEstabelecimento.get(j).getId()%>','<%= listEstabelecimento.get(j).getFoto()%>');
  }
             <%
        
        
    
}%>
        }
                
                function carregar(container3,link){
                    var demo = document.querySelector('#demo');
                    var container = demo.querySelector(container3);
                    //statusElem = demo.querySelector('#status');
                    //progressElem = demo.querySelector('progress');
                    supportsProgress = progressElem && progressElem.toString().indexOf('Unknown') === -1;
                    var fragment = getItemsFragment(link);
                    container.insertBefore(fragment, container.firstChild);
                    var imgLoad = imagesLoaded(container);
                    imgLoad.on('progress', onProgress);
                    imgLoad.on('always', onAlways);
                    imageCount = imgLoad.images.length;
                    resetProgress();
                    updateProgress(0);
                    fomularisotope();
                }
                
                
                var docElem = document.documentElement;
                var textSetter = docElem.textContent !== undefined ? 'textContent' : 'innerText';
                
                function setText(elem, value) {
                    elem[textSetter] = value;
                }
                
                function empty(elem) {
                    while (elem.firstChild) {
                        if (window.CP.shouldStopExecution(1)) {
                            break;
                        }
                        elem.removeChild(elem.firstChild);
                    }
                    window.CP.exitedLoop(1);
                }
                function getItemsFragment(link) {
                    var fragment = document.createDocumentFragment();
                    var item = getImageItem(link);
                    fragment.appendChild(item);
                    window.CP.exitedLoop(2);
                    return fragment;
                }

                function getImageItem(link) {
                    var item = document.createElement('li');
                    item.className = 'is-loading';    
                    var img = document.createElement('img');
                    img.src = link;
                    img.className = 'img-responsive';
                    item.appendChild(img);
                    return item;
                }

                function resetProgress() {
                    //statusElem.style.opacity = 1;
                    loadedImageCount = 0;
                    if (supportsProgress) {
                        //progressElem.setAttribute('max', imageCount);
                    }
                }
                function updateProgress(value) {
                    if (supportsProgress) {
                        //progressElem.setAttribute('value', value);
                    } else {
                        //setText(statusElem, value + ' / ' + imageCount);
                    }
                }
                function onProgress(imgLoad, image) {
                    image.img.parentNode.className = image.isLoaded ? '' : 'is-broken';
                    loadedImageCount++;
                    updateProgress(loadedImageCount);
                   
                }
                function onAlways() {
                    //statusElem.style.opacity = 0;
                    fomularisotope();
                }
                window.onload = function(e){ 
               
                    //alert("ok");
                    loadcarregar();
                }
                
                function fomularisotope(){
             $('.isotope').isotope({ filter: ':not()'});
            }
           
           
        </script>
    
        


    </head>
    <body background="util/img/novofundoestab.png" style="padding-top: 0px">

       
        
        <div class="buscainicioback">
           <div class="buscainicio">         
                
               <center><img src="util/img/logoinicio.png" class="img-responsive" style="padding: 10px"></center>
                 <div class="textologoinicio h1 text-center">Pedir é fácil!</div>
              
                 <div class="col-lg-12">
                 <div class="text-center col-lg-8 col-lg-offset-2">
                     
                     <form class="navbar-form" action="procurarestabelecimento.jsp" method="get">
    <div class="input-group input-group-lg">
        <input type="text" class="form-control" name="procurar" placeholder="Procurar restaurantes...">
      <span class="input-group-btn">
          <button class="btn btn-default" type="submit"><span class=" glyphicon glyphicon-search"></span></button>
      </span>
    </div><!-- /input-group -->
    
  </div><!-- /.col-lg-6 -->   
                
                 </div>       
                 <div class="text-center h4" > 
                     <a href="procurarestabelecimento.jsp" style="color: #000"><strong>Todos os restaurantes em <%=endereco.getCidade() %></strong></a>
        </div>
           </div>
            
        </div>
        
        
                
        <div class="container">
            <div class="col-lg-10 col-lg-offset-1 text-left">
                <h3 style="margin-left: 5%">Em destaque</h3>
          <div id="demo">
            <div id="status">
                <progress max="7" value="0"></progress>
            </div>
                
            <div id="content" class="isotope" style="position: relative; overflow: hidden; height: 1440px; margin-left: 5%">
          
                <%  for (int i = 0; i < listEstabelecimento.size(); i++) {%>


                <div class="boxportfolio4 <%=listEstabelecimento.get(i).getNome()%> isotope-item" style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px);">
                    <div class="boxcontainer">
                       
                            <div id="image-container<%= listEstabelecimento.get(i).getId()%>"></div>
                        <h1><span class="iniciodestaques"><%= listEstabelecimento.get(i).getNome()%></span></h1>
                        <p><%if (verificaaberto.verificar(listEstabelecimento.get(i).getId())) {%><span class="label label-success">Aberto Agora</span><%} else {%><span class="label label-danger">Fechado Agora</span><%}%></p>
                   <p class="text-center">
           <%if(verificaaberto.verificar(listEstabelecimento.get(i).getId())){%>
                                    <a href="perfil.jsp?id=<%= listEstabelecimento.get(i).getId()%>" class="btn btn-warning btn-outline-rounded green">Pedir <span class="glyphicon glyphicon-chevron-right"></span></a>
                                    <%}else{%>
                                    <a href="perfil.jsp?id=<%= listEstabelecimento.get(i).getId()%>" class="btn btn-danger btn-outline-rounded">Ver Cardápio</a>
                                    <%}%>
                                </p>
                    
                    </div>
                </div>
                <%}%>
                </div>
            </div>
            <!-- MASONRY ITEMS END -->
            
            </div>
</div>

       
      <jsp:include page = "componentes/rodape.jsp"/>
        <!-- Placed at the end of the document so the pages load faster -->
  
        <script src="./util/js/twitter-bootstrap-hover-dropdown.js"></script>
   
        <script src="./util/js/common.js"></script>
        <script src="./util/js/jquery.prettyPhoto.js"></script>
        <script src="./util/js/jquery.isotope.min.js"></script>
        <script src="./util/js/bootstrapnav.min.js"></script>
        <script src="./util/js/scripts.js"></script>

        <!-- SmartMenus jQuery plugin -->
        <script type="text/javascript" src="./util/js/jquery.smartmenus.js"></script>

        <!-- SmartMenus jQuery Bootstrap Addon -->
        <script type="text/javascript" src="./util/js/addons/bootstrap/jquery.smartmenus.bootstrap.js"></script>
        <script src="./util/js/bootstrap.min.js"></script> 
        <script src='./util/js/stopExecutionOnTimeout.js?t=1'></script>
        <script src='./util/js/imagesloaded.pkgd.js'></script>
        <script src='./util/js/css_live_reload_init.js'></script>
    </body>
   


</html>

 <%
        }catch(Exception e){
            
               response.sendRedirect("index.jsp?url=inicio.jsp");            
        }
    %>
