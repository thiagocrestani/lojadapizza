/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import beans.UsuarioEstabelecimento;
import dao.UsuarioEstabelecimentoDAO;
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
@WebServlet(name = "sevletLoginUsuarioEstabelecimento", urlPatterns = {"/sevletLoginUsuarioEstabelecimento"})
public class sevletLoginUsuarioEstabelecimento extends HttpServlet {

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
         try{
        UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
        UsuarioEstabelecimentoDAO usuarioestabelecimentoDAO = new UsuarioEstabelecimentoDAO();
        HttpSession session = request.getSession();
        //response.sendRedirect("admin/login.jsp?erro=4");  
        usuarioestabelecimento = usuarioestabelecimentoDAO.consultarUsuario(request.getParameter("usuario"));
       
        if(usuarioestabelecimento.getSenha().equals(request.getParameter("senha"))){
            session.setAttribute("usuarioestabelecimento", usuarioestabelecimento);
            usuarioestabelecimentoDAO.zerarTentativas(usuarioestabelecimento);
            response.sendRedirect("admin/index.jsp"); 
        }else{
          response.sendRedirect("admin/login.jsp?erro=3");
          usuarioestabelecimentoDAO.aumentarTentativas(usuarioestabelecimento);
        } 
        }catch (Exception e){
          response.sendRedirect("admin/login.jsp?erro=3");   
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
