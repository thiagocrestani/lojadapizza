

<%@page import="beans.TaxaEntrega"%>
<%@page import="beans.Endereco"%>
<%@page import="util.Utilidades"%>
<%@page import="dao.TaxaEntregaDAO"%>
<%@page import="dao.CategoriaProdutoDAO"%>
<%@page import="beans.CategoriaProduto"%>
<%@page import="beans.Borda"%>
<%@page import="dao.BordaDAO"%>
<%@page import="beans.Tamanho"%>
<%@page import="dao.TamanhoDAO"%>
<%@page import="dao.EstabelecimentoDAO"%>
<%@page import="beans.Estabelecimento"%>
<%@page import="beans.Carrinho"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProdutoDAO"%>
<%@page import="beans.Produto"%>
<%
    try {
        Produto produto = new Produto();
        Carrinho carrinho = new Carrinho();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
        TamanhoDAO tamanhoDAO = new TamanhoDAO();
        BordaDAO bordaDAO = new BordaDAO();
        List<Integer> listadesabores = new ArrayList<Integer>();
        CategoriaProdutoDAO categoriaprodutoDAO = new CategoriaProdutoDAO();
        Endereco endereco = (Endereco) session.getAttribute("localusuario");

        Tamanho tamanho = new Tamanho();
        Borda borda = new Borda();

        String v = request.getParameter("id");
        String estab = request.getParameter("estab");
        String l = request.getParameter("l");
        String e = request.getParameter("eli");
        String tam = request.getParameter("tam");
        String parametroborda = request.getParameter("borda");
        String tamanhounico = "Único";

        if (v == null || v.trim().equals("")) {
            if (l != null) {
                if (!l.trim().equals("")) {
                    session.setAttribute("carrinho", new Carrinho());
                }
            } else if (e != null) {
                if (!e.trim().equals("")) {
                    carrinho = (Carrinho) session.getAttribute("carrinho");
                    carrinho.getListProduto().remove(Integer.parseInt(e));
                    int quantidade = 0;
                    double valor = 0;
                    for (int i = 0; i < carrinho.getListProduto().size(); i++) {
                        valor = valor + carrinho.getListProduto().get(i).getPreco();
                        quantidade++;
                    }

                    carrinho.setQuantidade(quantidade);
                    carrinho.setTotal(valor);
                    session.setAttribute("carrinho", carrinho);

                }
            } else {
                if (session.getAttribute("carrinho") == null) {
                    session.setAttribute("carrinho", carrinho);
                } else {
                    carrinho = (Carrinho) session.getAttribute("carrinho");

                    if (endereco.isRetirar() != carrinho.isRetirar() && carrinho.getListProduto().size() > 0 && carrinho.isRecarregar()) {
                        if (!endereco.isRetirar()) {
                            Estabelecimento estabelecimento = new Estabelecimento();
                            estabelecimento = new EstabelecimentoDAO().consultarEstabelecimentoId(carrinho.getEstabelecimento());
                            TaxaEntregaDAO taxaentregaDAO = new TaxaEntregaDAO();
                            TaxaEntrega taxaentrega = new TaxaEntrega();
                            Utilidades utilidades = new Utilidades();
                            float dist = utilidades.getDistanciaPointsMeters(endereco.getLatitude(), endereco.getLongitude(), estabelecimento.getLatitude(), estabelecimento.getLongitude());
                            taxaentrega = taxaentregaDAO.consultarTaxaEntrega(carrinho.getEstabelecimento(), dist);
                            if (taxaentrega != null) {
                                carrinho.setTaxadeentrega(taxaentrega.getPreco());
                                carrinho.setRecarregar(false);
                                carrinho.setRetirar(false);
                            }
                        } else {
                            carrinho.setRetirar(true);
                            carrinho.setRecarregar(false);
                        }
                    }

                }
            }
        } else {
            int id = Integer.parseInt(v);
            if (id == 0) {
                if (session.getAttribute("listadesabores") != null) {
                    listadesabores = (List) session.getAttribute("listadesabores");
                    produto = produtoDAO.consultarProdutoId(listadesabores.get(0));
                    produto.setSabores((int) listadesabores.size());
                    for (int s = 1; s < listadesabores.size(); s++) {
                        produto.setNome(produto.getNome() + ", " + produtoDAO.consultarProdutoId(listadesabores.get(s)).getNome());
                    }
                }
            } else {
                produto = produtoDAO.consultarProdutoId(id);
            }

            if (session.getAttribute("carrinho") == null) {

                session.setAttribute("carrinho", carrinho);

            } else {
                carrinho = (Carrinho) session.getAttribute("carrinho");

                if (carrinho.getListProduto().size() > 0 && carrinho.getEstabelecimento() != Integer.parseInt(estab)) {
                    carrinho = new Carrinho();
                    Estabelecimento estabelecimento = new Estabelecimento();
                    estabelecimento = new EstabelecimentoDAO().consultarEstabelecimentoId(Integer.parseInt(estab));
                    carrinho.setEstabelecimento(estabelecimento.getId());
                    carrinho.setEstabelecimentoentrega(estabelecimento.isEntrega());
                    carrinho.setEstabelecimentoretirada(estabelecimento.isRetirada());
                    TaxaEntregaDAO taxaentregaDAO = new TaxaEntregaDAO();
                    Utilidades utilidades = new Utilidades();

                    if (!endereco.isRetirar()) {
                        float dist = utilidades.getDistanciaPointsMeters(endereco.getLatitude(), endereco.getLongitude(), estabelecimento.getLatitude(), estabelecimento.getLongitude());
                        carrinho.setTaxadeentrega(taxaentregaDAO.consultarTaxaEntrega(carrinho.getEstabelecimento(), dist).getPreco());
                        carrinho.setRetirar(false);
                    } else {
                        carrinho.setRetirar(true);
                    }
                }

                if (carrinho.getListProduto().size() <= 0) {
                    Estabelecimento estabelecimento = new Estabelecimento();
                    estabelecimento = new EstabelecimentoDAO().consultarEstabelecimentoId(Integer.parseInt(estab));
                    carrinho.setEstabelecimento(estabelecimento.getId());
                    carrinho.setEstabelecimentoentrega(estabelecimento.isEntrega());
                    carrinho.setEstabelecimentoretirada(estabelecimento.isRetirada());
                    TaxaEntregaDAO taxaentregaDAO = new TaxaEntregaDAO();
                    Utilidades utilidades = new Utilidades();
                    if (!endereco.isRetirar()) {
                        float dist = utilidades.getDistanciaPointsMeters(endereco.getLatitude(), endereco.getLongitude(), estabelecimento.getLatitude(), estabelecimento.getLongitude());
                        carrinho.setTaxadeentrega(taxaentregaDAO.consultarTaxaEntrega(carrinho.getEstabelecimento(), dist).getPreco());
                        carrinho.setRetirar(false);

                    } else {
                        carrinho.setRetirar(true);
                    }
                }

                if (endereco.isRetirar() != carrinho.isRetirar() && carrinho.getListProduto().size() > 0) {
                    if (!endereco.isRetirar()) {
                        Estabelecimento estabelecimento = new Estabelecimento();
                        estabelecimento = new EstabelecimentoDAO().consultarEstabelecimentoId(carrinho.getEstabelecimento());
                        TaxaEntregaDAO taxaentregaDAO = new TaxaEntregaDAO();
                        Utilidades utilidades = new Utilidades();
                        float dist = utilidades.getDistanciaPointsMeters(endereco.getLatitude(), endereco.getLongitude(), estabelecimento.getLatitude(), estabelecimento.getLongitude());
                        carrinho.setTaxadeentrega(taxaentregaDAO.consultarTaxaEntrega(carrinho.getEstabelecimento(), dist).getPreco());
                        carrinho.setRetirar(false);
                    } else {
                        carrinho.setRetirar(true);
                    }
                }

                if (tam != null && !tam.trim().equals("")) {
                    tamanho = tamanhoDAO.consultarTamanho(Integer.parseInt(tam));
                    produto.setTamanho(tamanho.getNome());
                    if (parametroborda != null && !parametroborda.trim().equals("")) {
                        if (Integer.parseInt(parametroborda) != 0) {
                            borda = bordaDAO.consultarBorda(Integer.parseInt(parametroborda));
                            produto.setSaborborda("Borda de " + borda.getNome());
                            produto.setPreco(tamanho.getPreco() + borda.getPreco());
                        } else {
                            produto.setSaborborda("Sem borda");
                            produto.setPreco(tamanho.getPreco());
                        }

                    } else {
                        produto.setSaborborda("");
                        produto.setPreco(tamanho.getPreco());
                    }

                } else {
                    produto.setSaborborda("");
                    produto.setTamanho("");
                }

                produto.setCategoria(categoriaprodutoDAO.consultarCategoriaProdutoEstabelecimentoId(produto.getIdcategoria()).getNome());

                carrinho.getListProduto().add(produto);
                int quantidade = 0;
                double valor = 0;

                for (int i = 0; i < carrinho.getListProduto().size(); i++) {
                    valor = valor + carrinho.getListProduto().get(i).getPreco();
                    quantidade++;
                }

                carrinho.setQuantidade(quantidade);
                carrinho.setTotal(valor + carrinho.getTaxadeentrega());
                session.setAttribute("carrinho", carrinho);

            }

        }


%>


<%if (carrinho.getListProduto().size() <= 0) {%>
<center>Carrinho vazio!</center>
    <%} else {%>

<table class="table table-hover" style="background-color: #ffffff">
    <thead>
        <tr>
            <th>Produto</th>
            <th>Valor</th>
            <th><center>Excluir</center></th>
</tr>
</thead>
<tbody>
    <%for (int j = 0; j < carrinho.getListProduto().size(); j++) {%>
    <tr>
        <td><%= carrinho.getListProduto().get(j).getCategoria()%> <%= carrinho.getListProduto().get(j).getNome()%> <%= carrinho.getListProduto().get(j).getTamanho()%> <%= carrinho.getListProduto().get(j).getSaborborda()%> <%if (carrinho.getListProduto().get(j).getSabores() > 1) {%> <%= carrinho.getListProduto().get(j).getSabores()%> Sabores <%}%></td>
        <td>R$ <%= String.format("%.2f", (double) carrinho.getListProduto().get(j).getPreco())%></td>
        <td><center><button class="btn btn-link" onclick="excluiritem(<%= j%>);"><span class="glyphicon glyphicon-remove-circle"></span></button></center></td>
</tr>
<%}%>

<tr>

    <td>Taxa de entrega <%if (!carrinho.isRetirar() && carrinho.isEstabelecimentoretirada()) {%>(<a href="#" onclick="alterarentrega(true)">prefiro retirar no local</a>)<%} else if (carrinho.isRetirar() && carrinho.isEstabelecimentoentrega()) {%>(<a href="#" onclick="alterarentrega(false)">prefiro receber em casa</a>)<%}%></td>
    <td colspan="2" class="text-right"><b><%if (!carrinho.isRetirar()) {%>  R$ <%= String.format("%.2f", (double) carrinho.getTaxadeentrega())%><%} else {%> Retira no local<%}%></b></td>
</tr>

<tr class="warning">

    <td class="warning"><b>TOTAL</b></td>
    <td colspan="2" class="warning text-right"><b>R$ <%= String.format("%.2f", (double) carrinho.getTotal())%></b></td>

</tr>
<tr>


    <td colspan="3"><center><button class="btn btn-link" onclick="limparcarinho()"><span class="glyphicon glyphicon-remove-circle"></span> Limpar Carinho</button></center></td>

</tr>
</tbody>
</table>
    <input id="carquantidade" type="hidden" value="<%= carrinho.getListProduto().size()%>">
<input id="estabelecimentoquantidade" type="hidden" value="<%= carrinho.getEstabelecimento()%>">


<%}

    } catch (Exception e) {

    }
%>


