/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import beans.Produto;
import beans.UsuarioEstabelecimento;
import dao.ProdutoDAO;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.CategoriaUsa;
import util.VerificaUsuarioEstabelecimento;

/**
 *
 * @author thiagocrestani
 */
@WebServlet(name = "servletEditarProduto", urlPatterns = {"/servletEditarProduto"})
@MultipartConfig
public class servletEditarProduto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
            VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
            usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
            CategoriaUsa categoriausa = new CategoriaUsa();

            if (verificausuarioestabelecimento.verificar(usuarioestabelecimento)) {
                try {
                    Produto produto = new Produto();
                    ProdutoDAO dao = new ProdutoDAO();
                

                     produto.setId(Integer.parseInt(request.getParameter("id")));
                     produto.setNome(request.getParameter("nome"));
                     produto.setIdestablecimento(usuarioestabelecimento.getEstabelecimento());
                     produto.setDescricao(request.getParameter("descricao"));
                     //produto.setDescricao(request.getParameter("descricao"));
                     if (!categoriausa.tamanho(Integer.parseInt(request.getParameter("categoria")))) {
                     produto.setPreco(Float.parseFloat(request.getParameter("preco")));
                    
                     } else {
                     produto.setPreco((float) 0);
                     }
                     
                     produto.setFoto(request.getParameter("foto"));
                     dao.editar(produto);
                 
                     response.sendRedirect("admin/consultarprodutos.jsp?sucesso=1");

                 /////////////////   
                } catch (Exception e) {
                    response.sendRedirect("admin/editarproduto.jsp?erro=1&id=" + request.getParameter("id"));
                }
            } else {
                response.sendRedirect("admin/login.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("admin/login.jsp");
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
