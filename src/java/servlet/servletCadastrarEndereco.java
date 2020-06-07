/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Endereco;
import dao.EnderecoDAO;
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
@WebServlet(name = "servletCadastrarEndereco", urlPatterns = {"/servletCadastrarEndereco"})
public class servletCadastrarEndereco extends HttpServlet {

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
            Endereco endereco = new Endereco();
            Endereco enderecosessao = new Endereco();
            EnderecoDAO dao = new EnderecoDAO();
            HttpSession session = request.getSession();
            
            if(session.getAttribute("localusuario") != null){
                enderecosessao = (Endereco) session.getAttribute("localusuario");      
            }else{
                response.sendRedirect("index.jsp");

            }
            /*if(request.getParameter("rua").equals("") || request.getParameter("numero").equals("")){
               response.sendRedirect("finalizarpedido2.jsp?erro=1"); 
            }*/
            endereco.setRua(request.getParameter("rua"));
            endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
            endereco.setBairro(enderecosessao.getBairro());
            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setPontodereferencia(request.getParameter("pontodereferencia"));
            endereco.setCep(enderecosessao.getCep()); 
            endereco.setCidade(enderecosessao.getCidade()); 
            endereco.setEstado(enderecosessao.getEstado());
            endereco.setIdcliente(Integer.parseInt(request.getParameter("idcliente"))); 
            //endereco.setCep(request.getParameter("email")); 
            //endereco.setSenha(request.getParameter("senha"));
            dao.cadastrar(endereco);
            response.sendRedirect("finalizarpedido3.jsp?idendereco="+dao.consultarUltimoEndereco(endereco.getIdcliente()).getId());
        } catch (Exception e) {
             
            response.sendRedirect("finalizarpedido2.jsp?erro=1");
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
