/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.UploadImagensEditar;


/**
 *
 * @author thiagocrestani
 */
@WebServlet(name = "servletEditarProduto", urlPatterns = {"/servletEditarProduto"})
public class servletEditarProduto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
    


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
            ServletContext context = session.getServletContext();
            String realContextPath = context.getRealPath(request.getContextPath());
            String novonome = "semfoto.jpg";
            String antigonome = "00";
            UploadImagensEditar uploadImagens = new UploadImagensEditar();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            FileItem foto = null;
            Produto produto = new Produto();
            List /*FileItem */ items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    if (item.getFieldName().equals("nome")) {
                        produto.setNome(item.getString());
                    } else if (item.getFieldName().equals("categoria")) {
                        produto.setCategoria(item.getString());
                    } else if (item.getFieldName().equals("descricao")) {
                        produto.setDescricao(item.getString());
                    } else if (item.getFieldName().equals("preco")) {
                        produto.setPreco(item.getString());
                    } else if (item.getFieldName().equals("nomefoto")) {
                        antigonome = item.getString();
                        if(antigonome.equals("semimagem")){
                            antigonome = String.valueOf(Math.round(Math.random() * 10000000));
                        }
                    } else if (item.getFieldName().equals("id")) {
                        produto.setId(item.getString());
                    }
                } else {
                    foto = item;
                }
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                novonome = uploadImagens.upload(getServletContext().getRealPath("/"), isMultipart, foto, antigonome);
            }
            
            /*String valores = "?";
            valores += "id=" + produto.getId();
            valores += "&nome=" + produto.getNome();
            valores += "&categoria=" + produto.getCategoria();
            valores += "&descricao=" + produto.getDescricao();
            valores += "&preco=" + produto.getPreco();
            valores += "&foto=" + novonome;*/
            
            response.setContentType("text/html");
            try (PrintWriter out = response.getWriter()) {
                out.println("<html lang='pt'>");
                out.println("<head>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form id='f' action='http://localhost:8080/pizza/servletEditarProduto' method='post'>");
                out.println("<input type='hidden' name='id' value='" + produto.getId()+ "'>");
                out.println("<input type='hidden' name='nome' value='" + produto.getNome() + "'>");
                out.println("<input type='hidden' name='categoria' value='" + produto.getCategoria() + "'>");
                out.println("<input type='hidden' name='descricao' value='" + produto.getDescricao() + "'>");
                out.println("<input type='hidden' name='preco' value='" + produto.getPreco() + "'>");
                out.println("<input type='hidden' name='foto' value='" + novonome + "'>");
                //out.println("<input type='submit' value='" + novonome + "'>");
                //out.println("<input type='submit' value='" + novonome + "'>");
                out.println("</form>");
                 out.println("<script>document.getElementById('f').submit();</script>");
                out.println("</body>");
                out.println("</html>");
            }
            
            //response.sendRedirect("http://localhost:8080/pizza/servletEditarProduto"+valores);
            // response.sendRedirect("admin/consultarprodutos.jsp?sucesso=1");
        } catch (Exception e) {
             response.sendRedirect("admin/cadastroproduto.jsp?erro=1");
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
