/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Carrinho;
import beans.Cliente;
import beans.Endereco;
import dao.ClienteDAO;
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
@WebServlet(name = "servletCadastrarCliente", urlPatterns = {"/servletCadastrarCliente"})
public class servletCadastrarCliente extends HttpServlet {

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
            Cliente cliente = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            boolean novo = true;
            HttpSession session = request.getSession();
            Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
            try {
                
                 
                if (dao.consultarClienteEmail(request.getParameter("email")).getEmail().equals(request.getParameter("email"))) {
                    cliente = dao.consultarClienteEmail(request.getParameter("email"));
                    cliente.setNome(request.getParameter("nome"));
                    cliente.setDdd(Integer.parseInt(request.getParameter("ddd")));
                    cliente.setTelefone(Integer.parseInt(request.getParameter("telefone")));
                    dao.editar(cliente);
                } else {
                    cliente.setNome(request.getParameter("nome"));
                    cliente.setDdd(Integer.parseInt(request.getParameter("ddd")));
                    cliente.setTelefone(Integer.parseInt(request.getParameter("telefone")));
                    cliente.setEmail(request.getParameter("email"));
                    //cliente.setSenha(request.getParameter("senha"));
                    dao.cadastrar(cliente);
                }
            } catch (Exception e) {
                cliente.setNome(request.getParameter("nome"));
                cliente.setDdd(Integer.parseInt(request.getParameter("ddd")));
                cliente.setTelefone(Integer.parseInt(request.getParameter("telefone")));
                cliente.setEmail(request.getParameter("email"));
                //cliente.setSenha(request.getParameter("senha"));
                dao.cadastrar(cliente);
                cliente = dao.consultarClienteEmail(request.getParameter("email"));
            }
           
            session.setAttribute("cliente", cliente);    
            if(carrinho.isRetirar()){
               response.sendRedirect("finalizarpedido3.jsp"); 
            }else{
               response.sendRedirect("finalizarpedido2.jsp?op=8");
            }
        } catch (Exception e) {
            response.sendRedirect("finalizarpedido1.jsp?erro=1");
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
