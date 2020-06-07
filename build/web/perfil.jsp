
<%@page import="dao.TaxaEntregaDAO"%>
<%@page import="beans.TaxaEntrega"%>
<%@page import="util.Utilidades"%>
<%@page import="beans.Endereco"%>
<%@page import="beans.Borda"%>
<%@page import="beans.Tamanho"%>
<%@page import="dao.BordaDAO"%>
<%@page import="dao.TamanhoDAO"%>
<%@page import="util.CategoriaUsa"%>
<%@page import="dao.CategoriaProdutoDAO"%>
<%@page import="beans.CategoriaProduto"%>
<%@page import="dao.FormaPagamentoEstabelecimentoDAO"%>
<%@page import="beans.FormaPagamento"%>
<%@page import="util.VerificaAberto"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dao.HorarioFuncionamentoDAO"%>
<%@page import="beans.HorarioFuncionamento"%>
<%@page import="beans.Carrinho"%>
<%@page import="dao.EstabelecimentoDAO"%>
<%@page import="beans.Estabelecimento"%>
<%@page import="dao.CategoriaDAO"%>
<%@page import="beans.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="beans.Produto"%>
<!DOCTYPE html>



<html lang="pt">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="util/img/favicon.ico" type="image/x-icon"/>

        <%

            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            CategoriaProdutoDAO categoriaprodutoDAO = new CategoriaProdutoDAO();
            List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
            Estabelecimento estabelecimento = new Estabelecimento();
            EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
            HorarioFuncionamento horariofuncionamento = new HorarioFuncionamento();
            HorarioFuncionamentoDAO horariofuncionamentoDAO = new HorarioFuncionamentoDAO();
            SimpleDateFormat horarioantigo = new SimpleDateFormat("HHmm");
            SimpleDateFormat horarionovo = new SimpleDateFormat("HH:mm");
            VerificaAberto verificaaberto = new VerificaAberto();
            boolean disponivel = true;
            String naodisponivelmsg = "";
            Carrinho carrinho = new Carrinho();
            CategoriaUsa categoriausa = new CategoriaUsa();
            Tamanho objtamanho = new Tamanho();
            TamanhoDAO tamanhoDAO = new TamanhoDAO();
            Borda objborda = new Borda();
            BordaDAO bordaDAO = new BordaDAO();
            TaxaEntrega taxaentrega = new TaxaEntrega();
            TaxaEntregaDAO taxaentregaDAO = new TaxaEntregaDAO();
            Utilidades utilidades = new Utilidades();
            int resultados = 0;
            boolean escolhamultisabor = false;
            session.setAttribute("listadesabores", null);
            List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
            FormaPagamentoEstabelecimentoDAO formaPagamentoestabelecimentoDAO = new FormaPagamentoEstabelecimentoDAO();
            try {
                estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(Integer.parseInt(request.getParameter("id")));
                horariofuncionamento = horariofuncionamentoDAO.consultar(estabelecimento.getId());
                if (estabelecimento != null) {
                    listCategoriaProduto = categoriaprodutoDAO.consultarCategoriaProdutoEstabelecimentoCompleto(estabelecimento.getId());
                    for (int j = 0; j < listCategoriaProduto.size(); j++) {
                        if (request.getParameter("procurar") != null) {
                            listCategoriaProduto.get(j).setProduto(produtoDAO.consultarProdutoCategoria(estabelecimento.getId(), listCategoriaProduto.get(j).getId(), request.getParameter("procurar")));
                        } else {
                            listCategoriaProduto.get(j).setProduto(produtoDAO.consultarProdutoCategoria(estabelecimento.getId(), listCategoriaProduto.get(j).getId()));
                        }
                    }
                    listFormaPagamento = formaPagamentoestabelecimentoDAO.consultarFormasPagamentoEstabelecimento(estabelecimento.getId());

                } else {
                    response.sendRedirect("procurarestabelecimento.jsp");
                }

                if (session.getAttribute("carrinho") == null) {
                    carrinho.setEstabelecimento(estabelecimento.getId());
                } else {
                    carrinho = (Carrinho) session.getAttribute("carrinho");
                }
                Endereco endereco = new Endereco();
                if (session.getAttribute("localusuario") != null) {
                    endereco = (Endereco) session.getAttribute("localusuario");
                    
                    if (!endereco.isRetirar()) {
                        float dist = utilidades.getDistanciaPointsMeters(endereco.getLatitude(), endereco.getLongitude(), estabelecimento.getLatitude(), estabelecimento.getLongitude());
                        taxaentrega = taxaentregaDAO.consultarTaxaEntrega(estabelecimento.getId(), dist);
                       
                        if (taxaentrega == null) {

                            estabelecimento.setTaxaentrega(-1);
                        } else {
                            estabelecimento.setTaxaentrega(taxaentrega.getPreco());
                        }
                    }
                } else {
                    response.sendRedirect("index.jsp?url=perfil.jsp?id=" + estabelecimento.getId());
                }

                if (!verificaaberto.verificar(estabelecimento.getId())) {
                    disponivel = false;
                    naodisponivelmsg = "Este estabelecimento está fechado neste momento, os itens estarão dísponiveis apenas para visulização";
                } else if (estabelecimento.getTaxaentrega() < 0 && estabelecimento.isRetirada()) {
                    disponivel = false;
                    naodisponivelmsg = "Este estabelecimento não faz entregas para sua região, os itens estarão disponíveis apenas na opção <a href='servletAtribuiEndereco?retirar=true&url=perfil.jsp?id=" + estabelecimento.getId() + "'>retirar</a>";
                } else if (!estabelecimento.isRetirada() && endereco.isRetirar()) {
                    disponivel = false;
                    naodisponivelmsg = "Este estabelecimento não permite retiradas, <a href='servletAtribuiEndereco?retirar=false&url=perfil.jsp?id=" + estabelecimento.getId() + "'>clique aqui</a> para ver a taxa de entrega";
                } else if (!estabelecimento.isEntrega() && !endereco.isRetirar()) {
                    disponivel = false;
                    naodisponivelmsg = "Este estabelecimento não faz entregas, você poderá apenas <a href='servletAtribuiEndereco?retirar=true&url=perfil.jsp?id=" + estabelecimento.getId() + "'>retirar</a> os itens no local";
                }
        %>

        <link href="./util/css/prettyPhoto.css" rel="stylesheet">
        <link href="./util/css/icons.css" rel="stylesheet" media="screen">
        <link href="./util/css/custom.css" rel="stylesheet">
        <link href="./util/css/skindefault.css" rel="stylesheet">
        <link href="./util/css/bootstrap-responsive.css" rel="stylesheet">
        <link href="./util/css/bootstrap.min.css" rel="stylesheet">
        <link href="./util/css/freelancer.css" rel="stylesheet">
        <link href="./util/css/estilo.css" rel="stylesheet">
        <link href="./util/css/estilo2.css" rel="stylesheet">
        <!-- Jquery - The rest of the scripts at the bottom-->
        <script src="./util/js/jquery-1.11.3.min.js"></script>



<script>
        var progressElem, statusElem;
        var supportsProgress;
        var loadedImageCount, imageCount;
        
        function loadcarregar(){
            <%for (int j = 0; j < listCategoriaProduto.size(); j++) {
                for (int p = 0; p < listCategoriaProduto.get(j).getProduto().size(); p++) {%>
                if (document.getElementById('image-container<%= listCategoriaProduto.get(j).getProduto().get(p).getId()%>') != null) {                          
                    carregar('#image-container<%= listCategoriaProduto.get(j).getProduto().get(p).getId()%>', '<%= listCategoriaProduto.get(j).getProduto().get(p).getFoto()%>');
                }
            <%}}%>
        }

        function carregar(container3, link){
            var demo = document.querySelector('#demo');
            var container = demo.querySelector(container3);
            supportsProgress = progressElem && progressElem.toString().indexOf('Unknown') === - 1;
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
            loadedImageCount = 0;
            
        }
        
        function updateProgress(value) {
            
        }
        
        function onProgress(imgLoad, image) {
            image.img.parentNode.className = image.isLoaded ? '' : 'is-broken';
            loadedImageCount++;
            updateProgress(loadedImageCount);
        }
            
        function onAlways() {
            fomularisotope();
        }


</script>


<script>
        var request1;
        var request2;
        var request3;
        var requestsabor;
        var link;
        var saboresescolhidos = 0;
        var estab;
        var esconde = [];
        esconde.push('nada');
        var qtdcarrinho = <%= carrinho.getListProduto().size()%>;
            
        if (qtdcarrinho > 0) {
            estab = <%= carrinho.getEstabelecimento()%>;
        } else {
            estab = <%= estabelecimento.getId()%>;
        }

        function fomularisotope(){
            var i;
            var vesconde = '';
            for (i = 0; i < esconde.length; i++) {
                if (i >= 1){
                    vesconde += ', ';
                }
                    vesconde += '.' + esconde[i];
            }
            <%if (request.getParameter("procurar") != null) {%>
                $('.isotope').isotope({ filter: ':not(' + vesconde + ')'});
            <%}else{%>
                $('.isotope').isotope({ filter: vesconde});
            <%}%>
 
        }

        function mostrar(categoria){
            removeItem(esconde, categoria);
            fomularisotope();
            document.getElementById('btnminimizar' + categoria).setAttribute('onClick', 'esconder(\'' + categoria + '\')');
            document.getElementById('btnminimizarlink' + categoria).setAttribute('onClick', 'esconder(\'' + categoria + '\')');
            document.getElementById('btnminimizar' + categoria).innerHTML = '<span class=\"glyphicon glyphicon-plus-sign\"></span>';
        }

        function esconder(categoria){
            esconde.push(categoria);
            fomularisotope();
            document.getElementById('btnminimizar' + categoria).setAttribute('onClick', 'mostrar(\'' + categoria + '\')');
            document.getElementById('btnminimizarlink' + categoria).setAttribute('onClick', 'mostrar(\'' + categoria + '\')');
            document.getElementById('btnminimizar' + categoria).innerHTML = '<span class=\"glyphicon glyphicon-minus-sign\"></span>';
        }

        function removeItem(array, item){
            for (var i in array){
                if (array[i] == item){
                    array.splice(i, 1);
                    break;
                }
            }
        }

        function sendInfo(v, l, e){
            request1 = null;
            var url;
            if (v != null) {
                if (document.getElementById('produtotamanho' + v) != null) {
                    var tam = document.getElementById('produtotamanho' + v).value;
                    if (document.getElementById('produtoborda' + v) != null){
                        var borda = document.getElementById('produtoborda' + v).value;
                        url = "componentes/carrinho.jsp?estab=" + estab + "&id=" + v + "&tam=" + tam + "&borda=" + borda;
                    }else{
                        url = "componentes/carrinho.jsp?estab=" + estab + "&id=" + v + "&tam=" + tam;
                    }  
                } else {
                    url = "componentes/carrinho.jsp?estab=" + estab + "&id=" + v;
                }
            } else if (l != null) {
                url = "componentes/carrinho.jsp?l=" + l;
            } else if (e != null) {
                url = "componentes/carrinho.jsp?eli=" + e;
            } else {
                url = "componentes/carrinho.jsp?";
            }
     
            if (window.XMLHttpRequest) {
                request1 = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                request1 = new ActiveXObject("Microsoft.XMLHTTP");
            }

            try{
                request1.onreadystatechange = getInfo;
                request1.open("GET", url, true);
                request1.send();
                
            }catch (e){
                alert("Unable to connect to server");
            }
        }
            
        function getInfo() {
            if (request1.readyState == 4 && request1.status==200) {
                document.getElementById('cart').innerHTML = request1.responseText;
                //alert(request.responseText);
                //alert("recebeu");
                quantidade();
            }
        }

        function quantidade(){
            var url2 = "componentes/quantidadecarrinho.jsp?id=";
            var url3 = "componentes/valorcarrinho.jsp?id=";
            if (window.XMLHttpRequest) {
                request2 = new XMLHttpRequest();
                request3 = new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                request2 = new ActiveXObject("Microsoft.XMLHTTP");
                request3 = new ActiveXObject("Microsoft.XMLHTTP");
            }
            try{
                request2.onreadystatechange = getInfo2;
                request2.open("GET", url2, true);
                request2.send();
                request3.onreadystatechange = getInfo2;
                request3.open("GET", url3, true);
                request3.send();
            }catch (e){
                alert("Unable to connect to server");
            }
        }

        function getInfo2() {
            if (request2.readyState == 4 && request3.readyState == 4) {
                document.getElementById('quantidade').innerHTML = request2.responseText;
                document.getElementById('quantidade2').innerHTML = request2.responseText;
                document.getElementById('valor').innerHTML = request3.responseText;
                btfinaliza();
            }
        }

        function limparcarinho() {
            sendInfo(null, true, null);
            qtdcarrinho = 0;
        }

        function excluiritem(e) {
            sendInfo(null, null, e);
            qtdcarrinho--;
        }


<%if (disponivel) {%>
        function adicionarCarrinho(v) {
            if (estab != <%= estabelecimento.getId()%> && qtdcarrinho > 0) {
                decisao = confirm("Você escolheu o produto de outro estabelecimento, deseja esvaziar o carrinho e inserir os novos produtos?");
                if (decisao) {
                    estab = <%= estabelecimento.getId()%>;
                    sendInfo(v, null, null);
                    qtdcarrinho++;
                 }
            } else {
                sendInfo(v, null, null);
                qtdcarrinho++;
            }
            $("#testeanimate").animate({ "top": "-=80px"}, 100);
            $("#testeanimate").animate({ "top": "+=80px" }, 100);
            $("#testeanimate").animate({ "top": "-=15px" }, 80);
            $("#testeanimate").animate({ "top": "+=15px" }, 80);
            $("#animation2").animate({ "top": "-=80px"}, 100);
            $("#animation2").animate({ "top": "+=80px" }, 100);
            $("#animation2").animate({ "top": "-=17px" }, 80);
            $("#animation2").animate({ "top": "+=17px" }, 80);
        }
<%}%>

        $(document).ready(function () {
            fomularisotope();
            $('#dropdowncarrinho .dropdown-menu').on({"click": function (e) {
                e.stopPropagation();
            }});
            
            jQuery('body').bind('click', function(e) {
                if (jQuery(e.target).closest('.navbar').length == 0) {
                    var opened = jQuery('.navbar-collapse').hasClass('collapse in');
                    if (opened === true) {
                        jQuery('.navbar-collapse').collapse('hide');
                    }
                }
            });
            
            sendInfo(null, null, null);
            loadcarregar();
            formularisotope();
            });
               
    
        function btfinaliza() {
            var qtd = document.getElementById('quantidadequantidade').value;
            if (qtd !== qtdcarrinho){
                qtdcarrinho = qtd;
                var testando = document.getElementById('estabelecimentoquantidade').value;
                if (testando > 0){
                    estab = testando;
                }
                sendInfo(null, null, null);
            }
            if (qtd <= 0) {
                document.getElementById('btfinaliza').href = "javascript: void(0)";
                document.getElementById('btfinaliza').style.color = "#d9d9d9";
            } else {
                document.getElementById('btfinaliza').href = "finalizarpedido1.jsp";
                document.getElementById('btfinaliza').style.color = "#d4542d";
            }
        }

        function inserirsabor(id){
            var url = "componentes/escolhesabor.jsp?id=" + id;
            if (window.XMLHttpRequest) {
                requestsabor = new XMLHttpRequest();
            }else if (window.ActiveXObject) {
                requestsabor = new ActiveXObject("Microsoft.XMLHTTP");
            }

            try{
                requestsabor.onreadystatechange = getInfo3;
                requestsabor.open("GET", url, true);
                requestsabor.send();
            }
            catch (e){
                alert("Unable to connect to server");
            }

        }

        function getInfo3() {
            if (requestsabor.readyState == 4) {
                document.getElementById('saboresrestantes').innerHTML = requestsabor.responseText;
            }
        }


        function escolhasabor(id, total) {
            document.getElementById('btescolhasabor' + id).style.backgroundColor = "#d4542d";
            document.getElementById('btescolhasabor' + id).style.color = "#ffffff";
            document.getElementById('btescolhasabor' + id).onclick = "javascript: void(0)";
            inserirsabor(id);
            saboresescolhidos++;
            if (saboresescolhidos >= total){
                adicionarCarrinho(0);
                window.location.href = "perfil.jsp?id=<%= estabelecimento.getId()%>";
            }
        }

        function alterarentrega(v) {
                window.location.href = "servletAtribuiEndereco?recarregar=true&retirar=" + v + "&url=perfil.jsp?id=<%= estabelecimento.getId()%>";
        }

<%for (int j = 0; j < listCategoriaProduto.size(); j++) {%>
    <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId())) {%>
        function alterarpreco<%= listCategoriaProduto.get(j).getId()%>(u) {
        var tam = document.getElementById('produtotamanho' + u).value;
        <%if (categoriausa.borda(listCategoriaProduto.get(j).getId())) {%>
            var borda = document.getElementById('produtoborda' + u).value;
        <%}%>
        <%for (int h = 0; h < listCategoriaProduto.get(j).getTamanho().size(); h++) {%>
            if (tam == "<%= listCategoriaProduto.get(j).getTamanho().get(h).getId()%>") {
                <%if (categoriausa.borda(listCategoriaProduto.get(j).getId())) {%>
                    <%for (int b = 0; b < listCategoriaProduto.get(j).getBorda().size(); b++) {%>
                        if (borda == "<%= listCategoriaProduto.get(j).getBorda().get(b).getId()%>") {
                            document.getElementById("precoproduto" + u).innerHTML = '<%= String.format("%.2f", (double) (listCategoriaProduto.get(j).getTamanho().get(h).getPreco() + listCategoriaProduto.get(j).getBorda().get(b).getPreco()))%>';
                        }
                        <%if (b < listCategoriaProduto.get(j).getBorda().size() - 1) {%> else <%}%>

                    <%}%>
                <%} else {%>
                    document.getElementById("precoproduto" + u).innerHTML = '<%= String.format("%.2f", (double) listCategoriaProduto.get(j).getTamanho().get(h).getPreco())%>';
                <%}%>
            }<%if (h < listCategoriaProduto.get(j).getTamanho().size() - 1) {%>else<%}%>
        <%}%>
        }
    <%}%>
<%}%>
        </script>
        <title><%= estabelecimento.getNome()%></title>
    </head>
    <body style="background-color:#f9f9f9;padding-bottom: 50px">


        <!-- Fixed navbar -->
        <nav class="navbar navbar-default navbar-fixed-bottom navbarestilo">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu" style="color: #f66627">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="glyphicon glyphicon-align-justify"></span>
                    </button>
                    <button type="button" id="animation2" class="navbar-toggle" data-toggle="collapse" data-target="#carinho" style="color: #f66627;background-color: #ffffff">
                        <span class="glyphicon glyphicon-shopping-cart small"></span> <span id="quantidade">0</span>
                    </button>
                    <a  href="inicio.jsp"><img src="util/img/logorodape.png"  height="50px" width="auto"></a>
                </div>
                <ul class="collapse navbar-collapse nav navbar-nav navbar-right" id="carinho">
                    <li class="dropdown" id="dropdowncarrinho" >
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #7d7d7d"><div id="testeanimate" class="glyphicon glyphicon-shopping-cart small"></div><span id="quantidade2">0</span> - R$ <span id="valor">0</span> <span class="glyphicon glyphicon-triangle-top"></span></a>
                        <ul class="dropdown-menu scrollable-menu" role="menu">
                            <span id="cart"></span>
                        </ul>
                    </li>
                    <li><a href="javascript: void(0)" id="btfinaliza" class="finalizapedido"><span class="glyphicon glyphicon-ok-circle"></span> Finalizar Pedido</a></li>
                </ul>


                <div class="collapse navbar-collapse" id="menu">
                    <ul class="nav navbar-nav">
                        <li> <form class="navbar-form" role="search" action="procurarestabelecimento.jsp" >
                                <div class="input-group">
                                    <input type="text" class="form-control" name="procurar" placeholder="Estabelecimentos...">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit"> <span class="glyphicon glyphicon-search"></span></button>
                                    </span>
                                </div>
                            </form></li>
                    </ul>
                </div>
            </div>
        </nav>



        <div class="container col-lg-12 estabelecimento">
            <div class="col-lg-2 col-lg-offset-1 text-center"><a href="perfil.jsp?id=<%= estabelecimento.getId()%>"><img src="<%= estabelecimento.getFoto()%>" class="bordaimgestabelecimento"  alt="" ></a></div>
            <div class="col-lg-4 text-left"><h5><%if (verificaaberto.verificar(estabelecimento.getId())) {%><span class="label label-success">Aberto</span><%} else {%><span class="label label-danger">Fechado</n><%}%></h5>
                <h3><b><%= estabelecimento.getNome()%></b></h3>
                <p class="text-muted"><%= estabelecimento.getRua()%>, <%= estabelecimento.getNumero()%> - <%= estabelecimento.getBairro()%> - <%= estabelecimento.getCidade()%> - <%= estabelecimento.getUfestado()%></p>
            </div>
        </div>


        <div class="container col-lg-12 estabelecimentosidebar">
            <div class="col-lg-3 col-lg-offset-3 text-center">       
                <%if (!endereco.isRetirar()) {%>
                <div class="btn-group" role="group">      
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <%if (estabelecimento.getTaxaentrega() >= 0 && estabelecimento.isEntrega()) {%>
                        <b><img src="util/img/icons/iconrelogio.png">  <%= estabelecimento.getTempoentrega()%> min</b> <b style="margin-left: 10px"><img src="util/img/icons/iconmotoboy2.png"> R$ <%= String.format("%.2f", estabelecimento.getTaxaentrega())%></b>
                            <%} else {%>
                        <b style="margin-left: 10px;">Apenas Retiradas</b>
                        <%}%>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="max-width: 220px">
                        <%if (estabelecimento.isRetirada()) {%>
                        <li><a href="servletAtribuiEndereco?retirar=true&url=perfil.jsp?id=<%= estabelecimento.getId()%>"><b><img src="util/img/icons/iconrelogio.png">  <%= estabelecimento.getTemporetirada()%> min</b> <b style="margin-left: 10px">Retirar no local</b></a></li>
                                    <%} else {%>
                        <li class="disabled"><a href="#"><b style="margin-left: 10px;">Apenas entregas</b></a></li>
                            <%}%>
                    </ul>
                </div>

                <%} else {%>
                <div class="btn-group" role="group">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <%if (estabelecimento.isRetirada()) {%>
                        <b><img src="util/img/icons/iconrelogio.png">  <%= estabelecimento.getTemporetirada()%> min</b> <b style="margin-left: 10px">Retirar no local</b>
                            <%} else {%>
                        <b style="margin-left: 10px">Apenas Entregas</b>
                        <%}%>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="max-width: 250px">
                        <%if (estabelecimento.isEntrega()) {%>
                        <li><a href="servletAtribuiEndereco?retirar=false&url=perfil.jsp?id=<%= estabelecimento.getId()%>"><b><img src="util/img/icons/iconrelogio.png">  <%= estabelecimento.getTempoentrega()%> min</b> <b style="margin-left: 10px"><%if (endereco.getBairro() != null) {%><img src="util/img/icons/iconmotoboy2.png"> R$ <%= String.format("%.2f", estabelecimento.getTaxaentrega())%><%} else {%>Ver taxa de entrega<%}%></b></a></li>
                                    <%} else {%>
                        <li class="disabled"><a href="#"><b style="margin-left: 10px;">Apenas retiradas</b></a></li>
                            <%}%>
                    </ul>
                </div>
                <%}%>
            </div>
            <div class="col-lg-2 text-center">
                <div class="btn-group btn-block" role="group">
                    <button type="button" class="btn btn-default btn-block dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: #a6441a;border: solid 1px #d4542d;color: #d05621;">
                        Mais Informações <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" style="max-width: 240px; color: #626262">
                        <table style="margin: 7px">
                            <tr>
                                <th colspan="3">Horário de Funcionamento</th>
                            </tr>
                            <%if (horariofuncionamento.getDia().get(0).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Domingo</td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(0).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(0).getF()))%></td>
                            </tr>
                            <%}%>
                            <%if (horariofuncionamento.getDia().get(1).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Segunda</td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(1).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(1).getF()))%></td>
                            </tr>
                            <%}%>
                            <%if (horariofuncionamento.getDia().get(2).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Terça</td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(2).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(2).getF()))%></td>
                            </tr>
                            <%}%>
                            <%if (horariofuncionamento.getDia().get(3).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Quarta</td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(3).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(3).getF()))%></td>
                            </tr>
                            <%}%>
                            <%if (horariofuncionamento.getDia().get(4).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Quinta </td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(4).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(4).getF()))%></td>
                            </tr>
                            <%}%>
                            <%if (horariofuncionamento.getDia().get(5).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Sexta</td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(5).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(5).getF()))%></td>
                            </tr>
                            <%}%>
                            <%if (horariofuncionamento.getDia().get(6).isFunciona()) {%>
                            <tr>
                                <td class="text-left">Sábado</td><td class="text-center"> <%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(6).getA()))%></td><td class="text-center"><%= horarionovo.format(horarioantigo.parse(horariofuncionamento.getDia().get(6).getF()))%></td>
                            </tr>  
                            <%}%>
                        </table>
                        <hr>
                        <table style="margin: 7px">
                            <tr>
                                <th>Formas de Pagamento</th>
                            </tr>
                            <% for (int i = 0; i < listFormaPagamento.size(); i++) {%>
                            <tr>
                                <td><img src="<%= listFormaPagamento.get(i).getIcone()%>" height="20px" width="auto"> <%= listFormaPagamento.get(i).getNome()%></td>
                            </tr>

                            <%}%>

                        </table>
                    </ul></div></div>
            <div class="col-lg-3 text-left">
                <form class="form" action="perfil.jsp" method="get">
                    <input type="hidden" name="id" value="<%= estabelecimento.getId()%>"/>
                    <div class="form-group" style="display:inline;">
                        <div class="input-group"> 
                            <input type="text" class="  search-query form-control" <%if (request.getParameter("procurar") != null) {%>value="<%= request.getParameter("procurar")%>"<%}%> id="busca" name="procurar" placeholder="Procurar produtos" style="background-color: #a6441a;border-color:#a6441a; color: #ffffff;"/>
                            <span class="input-group-btn">
                                <button class="btn" type="submit"  style="background-color: #a6441a;border-color:#a6441a; color: #ffffff;">
                                    <span class=" glyphicon glyphicon-search" style="background-"></span>
                                </button>
                            </span>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <div id="demo">
            <div id="status">
                <progress max="7" value="0"></progress>
            </div>

            <%if (!disponivel) {%>

            <div class="col-lg-10 col-lg-offset-1"><div class="alert alert-warning text-center" style="margin: 10px"><%= naodisponivelmsg%></div></div>
                <%}%>
            <!-- MASONRY ITEMS START
            ================================================== -->

            <%  for (int j = 0; j < listCategoriaProduto.size(); j++) {

            %>

            <div class="container col-lg-12 ">
                <%                int quantidadesabores = 1;
                    int escolhaquantidadesabores = 1;
                    int categoriasabores = 0;
                    for (int h = 0; h < listCategoriaProduto.get(j).getTamanho().size(); h++) {
                        if (listCategoriaProduto.get(j).getTamanho().get(h).getQuantidadesabores() > quantidadesabores) {
                            quantidadesabores = listCategoriaProduto.get(j).getTamanho().get(h).getQuantidadesabores();
                        }
                    }
                    if (quantidadesabores > listCategoriaProduto.get(j).getProduto().size()) {
                        quantidadesabores = listCategoriaProduto.get(j).getProduto().size();
                    }

                    try {
                        if (Integer.parseInt(request.getParameter("quantidadesabores")) > 1 && Integer.parseInt(request.getParameter("quantidadesabores")) <= quantidadesabores) {
                            categoriasabores = Integer.parseInt(request.getParameter("categoriaid"));
                            escolhaquantidadesabores = Integer.parseInt(request.getParameter("quantidadesabores"));
                        }
                    } catch (Exception e) {

                    }

                    if (listCategoriaProduto.get(j).getProduto().size() > 0) {
                        resultados = resultados + listCategoriaProduto.get(j).getProduto().size();


                %>

                <div id="categoria<%= listCategoriaProduto.get(j).getId()%>">
                    <div class="row col-lg-12 col-lg-offset-0 bordacardapio"> 
                        <%if (escolhaquantidadesabores > 1 || request.getParameter("quantidadesabores") != null) {
                                if (categoriasabores == listCategoriaProduto.get(j).getId() && categoriausa.tamanho(listCategoriaProduto.get(j).getId())) {

                                    int tamanho = 0;
                                    int borda = 0;
                                    boolean escolhermultisabor;
                                    float preco = 0;
                                    try {
                                        tamanho = Integer.parseInt(request.getParameter("tamanho"));
                                        objtamanho = tamanhoDAO.consultarTamanho(tamanho);
                                        preco = objtamanho.getPreco();
                                        if (categoriausa.borda(listCategoriaProduto.get(j).getId())) {
                                            try {
                                                borda = Integer.parseInt(request.getParameter("borda"));
                                                if (borda > 0) {
                                                    objborda = bordaDAO.consultarBorda(borda);
                                                    preco = preco + objborda.getPreco();
                                                }
                                            } catch (Exception e) {

                                            }
                                        }
                                        escolhermultisabor = true;
                                    } catch (Exception e) {
                                        escolhermultisabor = false;
                                    }
                        %>
                        <h3><div class="titulocategoriaproduto text-left"><%= listCategoriaProduto.get(j).getNome()%>
                                <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId()) && quantidadesabores > 1) {%>
                                <div class="btn-group" role="group" style="margin-left: 5px">
                                    <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #252525;font-size: medium">
                                        <%if (escolhaquantidadesabores > 1 && categoriasabores == listCategoriaProduto.get(j).getId()) {%><%= request.getParameter("quantidadesabores")%> Sabores<%} else {%>1 Sabor<%}%>
                                        <span class="caret"></span></button> 
                                    <ul class="dropdown-menu" style="max-width: 100px">                
                                        <%if (escolhaquantidadesabores > 1 && categoriasabores == listCategoriaProduto.get(j).getId()) {%>
                                        <li><button onclick="location.href = 'perfil.jsp?id=<%= estabelecimento.getId()%>';" class="btn btn-link">1 Sabor</button></li><%}%>
                                            <%for (int h = 2; h <= quantidadesabores; h++) {%>
                                        <li><form action="perfil.jsp?id=<%= estabelecimento.getId()%>" method="post">
                                                <input type="hidden" value="<%= h%>" name="quantidadesabores">
                                                <input type="hidden" value="<%= listCategoriaProduto.get(j).getId()%>" name="categoriaid">
                                                <button class="btn btn-link" type="submit"><%= h%> Sabores</button>
                                            </form>

                                        </li>
                                        <%}%>
                                    </ul>
                                </div> 
                                <%}%>
                            </div></h3>
                            <%if (escolhermultisabor) {%>
                        <input type="hidden" id="produtotamanho0" value="<%= tamanho%>">
                        <input type="hidden" id="produtoborda0" value="<%= borda%>"> 
                        <script>esconde.push('<%= listCategoriaProduto.get(j).getId()%>');</script>
                        <h4>Escolha os Sabores <span class="label label-danger opcoesescolhasabores">Tamanho: <%= objtamanho.getNome()%> <%if (borda > 0) {%>| Borda: <%= objborda.getNome()%><%}%> <a href="perfil.jsp?id=<%=estabelecimento.getId()%>" style="color:#ffffff">x</a></span><span id="saboresrestantes"></span> </h4>
                        <div id="content<%=j%>" class="isotope" style="position: relative; overflow: hidden; height: 1440px;">
                            <%  for (int i = 0; i < listCategoriaProduto.get(j).getProduto().size(); i++) {%>
                            <div class="boxportfolio4 <%= listCategoriaProduto.get(j).getId()%> isotope-item" style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px);">
                                <div class="boxcontainer">
                                    <div id="image-container<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>"></div>
                                    <h1><a><%= listCategoriaProduto.get(j).getProduto().get(i).getNome()%></a></h1>
                                    <p><div class="text-danger h5">R$ 
                                        <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId())) {%>
                                        <span id="precoproduto<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>"><%= String.format("%.2f", (double) listCategoriaProduto.get(j).getTamanho().get(0).getPreco())%></span>
                                        <%} else {%>
                                        <%= String.format("%.2f", (double) listCategoriaProduto.get(j).getProduto().get(i).getPreco())%>
                                        <%}%>
                                    </div>
                                    </p>
                                    <p>
                                        <%= listCategoriaProduto.get(j).getProduto().get(i).getDescricao()%>                       
                                    </p>

                                    <p> 
                                        <%if (disponivel) {%>
                                        <button class="btn btn-outline" id="btescolhasabor<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>" onclick="escolhasabor(<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>,<%= escolhaquantidadesabores%>)">Escolher</button>            
                                        <%}%>
                                    </p>


                                </div>
                            </div> 

                            <%}%>
                        </div>



                        <%} else {%>
                        <%escolhamultisabor = true;%>
                        <div class="col-lg-12">
                            <form class="form-horizontal" role="form" action="perfil.jsp?id=<%=estabelecimento.getId()%>" method="post">
                                <input type="hidden" name="quantidadesabores" value="<%=escolhaquantidadesabores%>">
                                <input type="hidden" name="categoriaid" value="<%=listCategoriaProduto.get(j).getId()%>">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Tamanho:</label>
                                    <div class="col-sm-6">
                                        <div class="form-group col-lg-10 col-lg-offset-1">
                                            <select class="form-control" name="tamanho">
                                                <%for (int h = 0; h < listCategoriaProduto.get(j).getTamanho().size(); h++) {%>
                                                <%if (listCategoriaProduto.get(j).getTamanho().get(h).getQuantidadesabores() >= escolhaquantidadesabores) {%>
                                                <option value="<%= listCategoriaProduto.get(j).getTamanho().get(h).getId()%>"><%= listCategoriaProduto.get(j).getTamanho().get(h).getNome()%></option>
                                                <%}%>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>
                                </div> 
                                <%if (categoriausa.borda(listCategoriaProduto.get(j).getId()) && listCategoriaProduto.get(j).getBorda().size() > 1) {%>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Borda: </label>
                                    <div class="col-sm-6">
                                        <div class="form-group col-lg-10 col-lg-offset-1">
                                            <select class="form-control" name="borda">
                                                <%for (int h = 0; h < listCategoriaProduto.get(j).getBorda().size(); h++) {%>
                                                <option value="<%= listCategoriaProduto.get(j).getBorda().get(h).getId()%>"><%= listCategoriaProduto.get(j).getBorda().get(h).getNome()%></option>
                                                <%}%>
                                            </select>
                                        </div>

                                    </div>
                                </div>
                                <%}%>
                                <div class="col-lg-offset-2"><button type="submit" class="btn btn-warning btn-outline-rounded  green">Continuar <span class="glyphicon glyphicon-chevron-right"></span></button></div>
                            </form>
                        </div>
                        <%}
                            }%>
                        <%} else {%>
                        <%if (!escolhamultisabor) {%>
                        <h3><div class="titulocategoriaproduto text-left">
                                <button id="btnminimizar<%= listCategoriaProduto.get(j).getId()%>" class="btn btn-link" onclick="esconder('<%= listCategoriaProduto.get(j).getId()%>')" style="color: #252525"><span class="glyphicon glyphicon-plus-sign" style="color: #252525"></span></button>
                                <a id="btnminimizarlink<%= listCategoriaProduto.get(j).getId()%>"  onclick="esconder('<%= listCategoriaProduto.get(j).getId()%>')" style="color: #252525;cursor:pointer;"><%= listCategoriaProduto.get(j).getNome()%></a>
                                <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId()) && quantidadesabores > 1) {%>
                                <div class="btn-group" role="group" style="margin-left: 5px">
                                    <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #252525;font-size: medium">
                                        <%if (escolhaquantidadesabores > 1 && categoriasabores == listCategoriaProduto.get(j).getId()) {%><%= request.getParameter("quantidadesabores")%> Sabores<%} else {%>1 Sabor<%}%>
                                        <span class="caret"></span></button> 
                                    <ul class="dropdown-menu" style="max-width: 100px">                
                                        <%if (escolhaquantidadesabores > 1 && categoriasabores == listCategoriaProduto.get(j).getId()) {%>
                                        <li><a class="btn-link" href="perfil.jsp?id=<%= estabelecimento.getId()%>">1 Sabor</a></li><%}%>
                                            <%for (int h = 2; h <= quantidadesabores; h++) {%>
                                        <li>
                                            <form action="perfil.jsp?id=<%= estabelecimento.getId()%>" method="post">
                                                <input type="hidden" value="<%= h%>" name="quantidadesabores">
                                                <input type="hidden" value="<%= listCategoriaProduto.get(j).getId()%>" name="categoriaid">
                                                <button class="btn btn-link" type="submit"><%= h%> Sabores</button>
                                            </form>

                                        </li>
                                        <%}%>
                                    </ul>

                                </div> 
                                <%}%>
                            </div></h3>
                        <div id="content<%=j%>" class="isotope" style="position: relative; overflow: hidden; height: 1440px;">
                            <%  for (int i = 0; i < listCategoriaProduto.get(j).getProduto().size(); i++) {%>
                            <div class="boxportfolio4 <%= listCategoriaProduto.get(j).getId()%> isotope-item" style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px);">
                                <div class="boxcontainer">
                                    <div id="image-container<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>"></div>
                                    <h1><a><%= listCategoriaProduto.get(j).getProduto().get(i).getNome()%></a></h1>
                                    <p><div class="text-danger h5">R$ 
                                        <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId())) {%>
                                        <span id="precoproduto<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>"><%= String.format("%.2f", (double) listCategoriaProduto.get(j).getTamanho().get(0).getPreco())%></span>
                                        <%} else {%>
                                        <%= String.format("%.2f", (double) listCategoriaProduto.get(j).getProduto().get(i).getPreco())%>
                                        <%}%>
                                    </div>
                                    </p>
                                    <p>
                                        <%= listCategoriaProduto.get(j).getProduto().get(i).getDescricao()%>                       
                                    </p>
                                    <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId())) {%>
                                    <p><div class="form-group col-lg-10 col-lg-offset-1">
                                        <select class="form-control" id="produtotamanho<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>" onchange="alterarpreco<%= listCategoriaProduto.get(j).getId()%>(<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>)">
                                            <%for (int h = 0; h < listCategoriaProduto.get(j).getTamanho().size(); h++) {%>
                                            <option value="<%= listCategoriaProduto.get(j).getTamanho().get(h).getId()%>"><%= listCategoriaProduto.get(j).getTamanho().get(h).getNome()%></option>
                                            <%}%>
                                        </select>
                                    </div></p>
                                    <%}%>
                                    <%if (categoriausa.borda(listCategoriaProduto.get(j).getId()) && listCategoriaProduto.get(j).getBorda().size() > 1) {%>
                                    <p><div class="form-group col-lg-10 col-lg-offset-1">
                                        <select class="form-control" id="produtoborda<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>" onchange="alterarpreco<%= listCategoriaProduto.get(j).getId()%>(<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>)">
                                            <%for (int h = 0; h < listCategoriaProduto.get(j).getBorda().size(); h++) {%>
                                            <option value="<%= listCategoriaProduto.get(j).getBorda().get(h).getId()%>"><%= listCategoriaProduto.get(j).getBorda().get(h).getNome()%></option>
                                            <%}%>
                                        </select>
                                    </div></p>
                                    <%}%>
                                    <p>
                                        <%if (disponivel) {%>
                                        <%if (categoriausa.tamanho(listCategoriaProduto.get(j).getId())) {%>
                                        <button class="btn btn-outline" onclick="adicionarCarrinho(<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>)"><span class="glyphicon glyphicon-plus"></span> Adicionar</button>
                                        <%} else {%>
                                        <button class="btn btn-outline" onclick="adicionarCarrinho(<%= listCategoriaProduto.get(j).getProduto().get(i).getId()%>)"><span class="glyphicon glyphicon-plus"></span> Adicionar</button>
                                        <%}%>
                                        <%}%>
                                    </p>

                                </div>
                            </div> 

                            <%}%>
                        </div>
                        <%}%>
                        <%}%>

                    </div>
                </div>

            </div>
            <%}%>
            <%}%>
        </div>
        <%if (resultados <= 0) {%><div class="col-lg-10 col-lg-offset-1"><div class="alert alert-warning text-center" style="margin: 10px">Nenhum resultado encontrado</div></div><%}%>    


        <!-- MASONRY ITEMS END -->


        <div id="rodape">  

            <div class="col-lg-4 text-center" style="height: 50px;padding-top: 16px"></div>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->

        <script src="./util/js/twitter-bootstrap-hover-dropdown.js"></script>
        <script src="./util/js/common.js"></script>
        <script src="./util/js/jquery.prettyPhoto.js"></script>
        <script src="./util/js/imagesloaded.pkgd.min.js"></script>
        <script src="./util/js/imagesloaded.pkgd.js"></script>

        <jsp:include page = "./util/jsp/jqueryisotopemin.jsp"/>

        <script src="./util/js/bootstrap.min.js"></script>    
        <script src='./util/js/stopExecutionOnTimeout.js?t=1'></script>
        <script src='./util/js/imagesloaded.pkgd.js'></script>
        <script src='./util/js/css_live_reload_init.js'></script>
        <script>
                                                    (function(i, s, o, g, r, a, m){i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function(){
                                                    (i[r].q = i[r].q || []).push(arguments)}, i[r].l = 1 * new Date(); a = s.createElement(o),
                                                            m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)
                                                    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
                                                    ga('create', 'UA-66897426-1', 'auto');
                                                    ga('send', 'pageview');

        </script>

    </body></html>

<%

    } catch (Exception e) {
        response.sendRedirect("inicio.jsp");
    }
%>