/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Carrinho;
import beans.Cliente;
import beans.Estabelecimento;
import beans.Pedido;
import beans.PedidoFinalizado;
import beans.Produto;
import beans.ProdutoPedido;
import dao.ClienteDAO;
import dao.EnderecoDAO;
import dao.EstabelecimentoDAO;
import dao.FormaPagamentoDAO;
import dao.PedidoDAO;
import dao.ProdutoDAO;
import dao.ProdutoPedidoDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DadosEmail;
import util.VerificaAberto;

/**
 *
 * @author thiagocrestani
 */
@WebServlet(name = "servletFinalizarPedido", urlPatterns = {"/servletFinalizarPedido"})
public class servletFinalizarPedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            Pedido pedido = new Pedido();
            Carrinho carrinho = new Carrinho();
            PedidoFinalizado pedidofinalizado = new PedidoFinalizado();
            ProdutoPedido produtopedido = new ProdutoPedido();
            PedidoDAO pedidoDAO = new PedidoDAO();
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Estabelecimento estabelecimento = new Estabelecimento();
            EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
            ProdutoPedidoDAO produtopedidoDAO = new ProdutoPedidoDAO();
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            VerificaAberto verificaaberto = new VerificaAberto();

            SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat ft2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String datapedido = ft.format(new Date());
            String datapedidoformatada = ft2.format(new Date());
            carrinho = (Carrinho) session.getAttribute("carrinho");
            FormaPagamentoDAO formapagamentoDAO = new FormaPagamentoDAO();
            DadosEmail dadosemail = new DadosEmail();
            if (verificaaberto.verificar(carrinho.getEstabelecimento())) {

                pedido.setDatapedido(String.valueOf(datapedido));
                pedido.setDatapedidofomatada(String.valueOf(datapedidoformatada));
                pedido.setItens(carrinho.getQuantidade());
                pedido.setValor((float) carrinho.getTotal());

                pedido.setIdestabelecimento(carrinho.getEstabelecimento());
                estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(pedido.getIdestabelecimento());
                pedido.setIdcliente(clienteDAO.consultarClienteId(Integer.parseInt(request.getParameter("idcliente"))).getId());
                cliente = clienteDAO.consultarClienteId(pedido.getIdcliente());
                pedido.setCliente(cliente);
                if (carrinho.isRetirar()) {
                    if (estabelecimento.isRetirada()) {
                        pedido.setIdendereco(0);
                        pedido.setTempoentrega(estabelecimento.getTemporetirada());
                        pedido.setTaxaentrega(String.valueOf(0));
                    } else {
                        response.sendRedirect("index.jsp");
                        return;
                    }
                } else {
                    if (estabelecimento.isEntrega()) {
                        //System.out.println("okokokokok");
                        pedido.setTaxaentrega(String.valueOf(carrinho.getTaxadeentrega()));
                        pedido.setTempoentrega(estabelecimento.getTempoentrega());
                        pedido.setIdendereco(enderecoDAO.consultarEnderecoCliente(Integer.parseInt(request.getParameter("idendereco")), Integer.parseInt(request.getParameter("idcliente"))).getId());
                    }else{
                        response.sendRedirect("index.jsp");
                        return;  
                    }
                }
                

                pedido.setIdformapagamento(Integer.parseInt(request.getParameter("formapagamento")));
                pedido.setId(String.valueOf(datapedido) + pedido.getIdcliente());

                if (!pedidoDAO.cadastrar(pedido)) {
                    response.sendRedirect("index.jsp");
                    return;
                }


                for (int i = 0; i < carrinho.getListProduto().size(); i++) {
                    produtopedido = new ProdutoPedido();
                    produtopedido.setIdpedido(pedido.getId());
                    produtopedido.setNumero(i);
                    produtopedido.setIdproduto(carrinho.getListProduto().get(i).getId());
                    produto = produtoDAO.consultarProdutoId(produtopedido.getIdproduto());
                    produto.setNome(carrinho.getListProduto().get(i).getNome());
                    produto.setSabores(carrinho.getListProduto().get(i).getSabores());
                    produto.setPreco(carrinho.getListProduto().get(i).getPreco());
                    produto.setTamanho(carrinho.getListProduto().get(i).getTamanho());
                    produto.setSaborborda(carrinho.getListProduto().get(i).getSaborborda());
                    produtopedido.setProduto(produto);
                    produtopedidoDAO.cadastrar(produtopedido);

                }
                //dadosemail.envia(pedido.getId(),pedido.getIdestabelecimento());

                pedidofinalizado.setEstabelecimento(estabelecimento);
                pedidofinalizado.setPedido(pedido);
                session.setAttribute("pedidofinalizado", pedidofinalizado);
                session.setAttribute("carrinho", null);
                response.sendRedirect("confirmacao.jsp");
            } else {
                response.sendRedirect("perfil.jsp?id=" + carrinho.getEstabelecimento());
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
