/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import beans.TaxaEntrega;
import beans.UsuarioEstabelecimento;
import dao.TaxaEntregaDAO;
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
@WebServlet(name = "servletEditarTaxaEntrega", urlPatterns = {"/servletEditarTaxaEntrega"})
public class servletEditarTaxaEntrega extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

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
                    //Tamanho tamanho = new Endereco();
                    TaxaEntregaDAO dao = new TaxaEntregaDAO();
                    TaxaEntrega taxaentrega = new TaxaEntrega();
                    taxaentrega.setIdestabelecimento(usuarioestabelecimento.getId());
                    taxaentrega.setDistancia(Float.parseFloat(request.getParameter("distancia")));
                    taxaentrega.setNovadistancia(Float.parseFloat(request.getParameter("novadistancia")));
                    taxaentrega.setPreco(Float.parseFloat(request.getParameter("preco")));
                    
                    dao.editar(taxaentrega);
                    response.sendRedirect("admin/taxadeentrega.jsp?sucesso=1");
                } catch (Exception e) {
                    response.sendRedirect("admin/editartaxaentrega.jsp?erro=1");
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
