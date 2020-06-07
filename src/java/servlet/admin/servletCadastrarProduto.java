/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import beans.Produto;
import beans.UsuarioEstabelecimento;
import dao.ProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@WebServlet(name = "servletCadastrarProduto", urlPatterns = {"/servletCadastrarProduto"})
@MultipartConfig
public class servletCadastrarProduto extends HttpServlet {


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
                   Produto produto = new Produto();
                   ProdutoDAO dao = new ProdutoDAO();
                   
                   produto.setNome(request.getParameter("nome"));
                   produto.setIdcategoria(Integer.parseInt(request.getParameter("categoria")));
                   produto.setDescricao(request.getParameter("descricao"));
                   produto.setPreco(Float.parseFloat(request.getParameter("preco")));
                   produto.setFoto(request.getParameter("foto"));
                   produto.setIdestablecimento(usuarioestabelecimento.getEstabelecimento());
                   SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
                   produto.setDatacadastro(String.valueOf(ft.format(new Date())));
                   dao.cadastrar(produto);
                   /* 
                    UploadImagens uploadImagens = new UploadImagens();
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    FileItem foto = null;
                    Produto produto = new Produto();
                    ProdutoDAO dao = new ProdutoDAO();
                    String novonome = "semfoto.jpg";
                    List  items = upload.parseRequest(request);
                    Iterator iter = items.iterator();
                    while (iter.hasNext()) {
                        FileItem item = (FileItem) iter.next();
                        if (item.isFormField()) {
                            if (item.getFieldName().equals("nome")) {
                                produto.setNome(item.getString());
                            } else if (item.getFieldName().equals("categoria")) {
                                produto.setIdcategoria(Integer.parseInt(item.getString()));
                            } else if (item.getFieldName().equals("descricao")) {
                                produto.setDescricao(item.getString());
                            } else if (item.getFieldName().equals("preco")) {
                                produto.setPreco(Float.parseFloat(item.getString()));
                            }
                        } else {
                            foto = item;
                        }
                    }

                 
                    produto.setFoto("imguploaded/semfoto.jpg");
                    produto.setIdestablecimento(usuarioestabelecimento.getEstabelecimento());
                    SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
                    produto.setDatacadastro(String.valueOf(ft.format(new Date())));
                    dao.cadastrar(produto); 
                    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                    int idproduto = dao.consultarUltimoProduto(usuarioestabelecimento.getEstabelecimento());
                    if (isMultipart) {
                        novonome = uploadImagens.upload(getServletContext().getRealPath("/"), isMultipart, foto, String.valueOf(idproduto));
                    }
                    dao.editarFoto(novonome, idproduto, usuarioestabelecimento.getEstabelecimento());*/
                    response.sendRedirect("admin/consultarprodutos.jsp?sucesso=1");
                } catch (Exception e) {
                    response.sendRedirect("admin/cadastroproduto.jsp?erro=1");
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
