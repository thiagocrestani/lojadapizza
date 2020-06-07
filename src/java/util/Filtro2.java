/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.UsuarioEstabelecimento;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thiagocrestani
 */
public class Filtro2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String url = ((HttpServletRequest) request).getRequestURL().toString();
            if (url.contains("login.jsp")) {
                chain.doFilter(request, response);
            } else {
                HttpSession session = ((HttpServletRequest) request).getSession();
                UsuarioEstabelecimento usuarioestabelecimento = new UsuarioEstabelecimento();
                VerificaUsuarioEstabelecimento verificausuarioestabelecimento = new VerificaUsuarioEstabelecimento();
                usuarioestabelecimento = (UsuarioEstabelecimento) session.getAttribute("usuarioestabelecimento");
                if (verificausuarioestabelecimento.verificar(usuarioestabelecimento)) {
                    chain.doFilter(request, response);
                } else {
                    ((HttpServletResponse) response).sendRedirect("http://localhost:8080/pizza/admin/login.jsp");
                }
            }
        } catch (Exception e) {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/pizza/admin/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
