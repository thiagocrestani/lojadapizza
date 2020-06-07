/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Carrinho;
import beans.Endereco;
import beans.Locais;
import dao.LocaisDAO;
import java.io.IOException;
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
@WebServlet(name = "servletAtribuiEndereco", urlPatterns = {"/servletAtribuiEndereco"})
public class servletAtribuiEndereco extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            Endereco endereco = new Endereco();

            if (request.getParameter("retirar") != null) {
                if (session.getAttribute("carrinho") != null) {
                    Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
                    if (request.getParameter("recarregar") != null) {
                        carrinho.setRecarregar(true);
                    } else {
                        carrinho.setRecarregar(false);
                    }
                    session.setAttribute("carrinho", carrinho);
                }
                if (session.getAttribute("localusuario") != null) {
                    endereco = (Endereco) session.getAttribute("localusuario");
                    if (request.getParameter("retirar").equals("true")) {
                        endereco.setRetirar(true);
                        session.setAttribute("localusuario", endereco);
                    } else if (request.getParameter("retirar").equals("false")) {
                        if (endereco.getBairro() == null) {
                            response.sendRedirect("index.jsp?alterar=true&url=" + request.getParameter("url"));
                            return;
                        } else {
                            endereco.setRetirar(false);
                            session.setAttribute("localusuario", endereco);
                        }
                    } else {
                        response.sendRedirect("index.jsp");
                        return;
                    }
                    response.sendRedirect(request.getParameter("url"));
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            HttpSession session = request.getSession();
            Endereco endereco = new Endereco();
            LocaisDAO localDAO = new LocaisDAO();
            Locais local = localDAO.consultarLocais(1);

            if (request.getParameter("retirar") != null) {
                if (session.getAttribute("localusuario") != null) {
                    endereco = (Endereco) session.getAttribute("localusuario");
                }
                endereco.setRetirar(true);
            } else {
                String bairro = null;
                bairro = request.getParameter("bairro");
                if (request.getParameter("cidadeendereco").equals(local.getCidade()) && bairro != null && bairro != "") {
                    endereco.setLatitude(Double.parseDouble(request.getParameter("latitude")));
                    endereco.setLongitude(Double.parseDouble(request.getParameter("longitude")));
                    endereco.setBairro(request.getParameter("bairro"));
                    endereco.setRetirar(false);
                } else {
                    response.sendRedirect("index.jsp?erro=1");
                }
            }
            endereco.setCidade(local.getCidade());
            endereco.setCep(local.getCep());
            endereco.setEstado(local.getEstado());
            endereco.setPais(local.getPais());
            endereco.setUfestado(local.getUfestado());

            session.setAttribute("localusuario", endereco);
            if (!request.getParameter("url").equals("") && request.getParameter("url") != null && !request.getParameter("url").equals("null")) {
                response.sendRedirect(request.getParameter("url"));
            } else {
                response.sendRedirect("inicio.jsp");
            }

        } catch (Exception e) {
            response.sendRedirect("index.jsp?erro=1");

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
