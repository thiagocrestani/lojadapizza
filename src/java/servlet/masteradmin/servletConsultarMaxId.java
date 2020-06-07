/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.masteradmin;

import beans.MasterAdmin;
import dao.MasterAdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thiagocrestani
 */
@WebServlet(name = "servletConsultarMaxId", urlPatterns = {"/servletConsultarMaxId"})
public class servletConsultarMaxId extends HttpServlet {

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
            MasterAdmin masteradmin = new MasterAdmin();
            MasterAdminDAO masteradminDAO = new MasterAdminDAO();
            MasterAdminDAO masterAdminDAO = new MasterAdminDAO();
            masteradmin = (MasterAdmin) session.getAttribute("masteradmin");
            if (masteradmin.getSenha().equals(masteradminDAO.consultarUsuario(masteradmin.getUsuario()).getSenha())) {
                response.sendRedirect("admin/masteradmin/control.jsp?operacao=" + masterAdminDAO.consultarMaxEstabelecimento());
            } else {
                response.sendRedirect("index.jsp");
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
