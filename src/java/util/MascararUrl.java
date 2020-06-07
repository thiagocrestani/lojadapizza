/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.EstabelecimentoDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

 

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

 

public class MascararUrl implements Filter {
       
 

      public void init(FilterConfig config) throws ServletException {


      }

 

      public void doFilter(ServletRequest req, ServletResponse res,
                  FilterChain chain) throws IOException, ServletException {
          
          String url = ((HttpServletRequest)req).getRequestURL().toString();
          
          if(url.contains(".jsp") || url.contains("servlet") || url.contains("admin")){
            chain.doFilter(req, res);  
          }else{
          url = url.replace("http://localhost:8080/pizza/", "");
          String divs[] = url.split("/");
          if(divs.length > 2){
             chain.doFilter(req, res); 
          }else{
              url = divs[0];        
              try {
                  EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();      
                  int id = estabelecimentoDAO.consultarNomeUrl(url);
                  //System.out.println("aperece");
                  //System.out.println(url);
                  if(id > 0){
                  ((HttpServletResponse)res).sendRedirect("perfil?id="+id);
                  }else{
                   chain.doFilter(req, res);    
                  }
              } catch (Exception ex) {
                  chain.doFilter(req, res); 
              }
          }
          }
          

      }

 

      public void destroy() {


      }

}


