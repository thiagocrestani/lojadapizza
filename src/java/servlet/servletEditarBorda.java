/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Borda;
import beans.UsuarioEstabelecimento;
import dao.BordaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.VerificaUsuarioEstabelecimento;

/**
 *
 * @author thiagocrestani
 */
@WebServlet(name = "servletEditarBorda", urlPatterns = {"/servletEditarBorda"})
public class servletEditarBorda extends HttpServlet {

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
            UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
            VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
            usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");

            if (verificausuarioestabelecimento.verificar(usuarioestabelecimento)) {
                try {
                    Borda borda = new Borda();
                    BordaDAO dao = new BordaDAO();

                    usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");

                    borda.setNome(request.getParameter("nome"));
                    borda.setPreco(Float.parseFloat(request.getParameter("preco")));
                    borda.setId(Integer.parseInt(request.getParameter("id")));
                    borda.setIdestabelecimento(usuarioestabelecimento.getEstabelecimento());
                    dao.editar(borda);

                    response.sendRedirect("admin/editarprodutoscategoria.jsp?sucesso=1");
                } catch (Exception e) {
                    response.sendRedirect("admin/editarborda.jsp?erro=1&id=" + request.getParameter("id"));

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