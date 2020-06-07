/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import beans.HorarioFuncionamento;
import beans.UsuarioEstabelecimento;
import dao.EstabelecimentoDAO;
import dao.HorarioFuncionamentoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "servletEditarDadosEstabelecimento", urlPatterns = {"/servletEditarDadosEstabelecimento"})
public class servletEditarDadosEstabelecimento extends HttpServlet {

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
                    //EstabelecimentoDAO dao = new EstabelecimentoDAO();
                    HorarioFuncionamento dia = new HorarioFuncionamento();
                    HorarioFuncionamento objhorarioFuncionamento = new HorarioFuncionamento();
                    HorarioFuncionamentoDAO horarioFuncionamentoDAO = new HorarioFuncionamentoDAO();
                    List<HorarioFuncionamento> listHorarioFuncionamento = new ArrayList<HorarioFuncionamento>();
                    SimpleDateFormat horarioantigo = new SimpleDateFormat("HH:mm");
                    SimpleDateFormat horarionovo = new SimpleDateFormat("HHmm");

                    objhorarioFuncionamento.setIdestabelecimento(usuarioestabelecimento.getEstabelecimento());
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("adomingo"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fdomingo"))));
                    if (request.getParameter("domingo") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    dia = new HorarioFuncionamento();
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("asegunda"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fsegunda"))));
                    if (request.getParameter("segunda") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    dia = new HorarioFuncionamento();
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("aterca"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fterca"))));
                    if (request.getParameter("terca") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    dia = new HorarioFuncionamento();
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("aquarta"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fquarta"))));
                    if (request.getParameter("quarta") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    dia = new HorarioFuncionamento();
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("aquinta"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fquinta"))));
                    if (request.getParameter("quinta") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    dia = new HorarioFuncionamento();
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("asexta"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fsexta"))));
                    if (request.getParameter("sexta") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    dia = new HorarioFuncionamento();
                    dia.setA(horarionovo.format(horarioantigo.parse(request.getParameter("asabado"))));
                    dia.setF(horarionovo.format(horarioantigo.parse(request.getParameter("fsabado"))));
                    if (request.getParameter("sabado") != null) {
                        dia.setFunciona(true);
                    } else {
                        dia.setFunciona(false);
                    }
                    listHorarioFuncionamento.add(dia);
                    objhorarioFuncionamento.setDia(listHorarioFuncionamento);
                    horarioFuncionamentoDAO.editar(objhorarioFuncionamento);
                    response.sendRedirect("admin/dados.jsp?sucesso=1");
                } catch (Exception e) {
                    response.sendRedirect("admin/dados.jsp?erro=1" + e);
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
