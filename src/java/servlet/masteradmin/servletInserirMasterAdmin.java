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
@WebServlet(name = "servletInserirMasterAdmin", urlPatterns = {"/servletInserirMasterAdmin"})
public class servletInserirMasterAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            MasterAdmin masteradmin = new MasterAdmin();
            MasterAdminDAO masteradminDAO = new MasterAdminDAO();
            MasterAdminDAO masterAdminDAO = new MasterAdminDAO();
            masteradmin = (MasterAdmin) session.getAttribute("masteradmin");
            //if (masteradmin.getSenha().equals(masteradminDAO.consultarUsuario(masteradmin.getUsuario()).getSenha())) {
                response.sendRedirect("control.jsp?operacao=" + masterAdminDAO.inserir(request.getParameter("sql")));
           // } else {
             //   response.sendRedirect("index.jsp?erro");
            //}
        } catch (Exception e) {
            response.sendRedirect("index.jsp?"+e);
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
