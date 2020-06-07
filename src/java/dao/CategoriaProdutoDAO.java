/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.CategoriaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiagocrestani
 */
public class CategoriaProdutoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    CategoriaProduto objCategoriaProduto;

    public CategoriaProdutoDAO() {
        con = new Conexao();
    }
    
   
    
     public List consultarCategoriaProduto() throws Exception {
        List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
        String sql = "SELECT * from categoriaproduto";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            rs = pstm.executeQuery();
            while (rs.next()) {
                objCategoriaProduto = new CategoriaProduto();
                objCategoriaProduto.setId(rs.getInt("id"));
                objCategoriaProduto.setNome(rs.getString("nome"));
                listCategoriaProduto.add(objCategoriaProduto);         
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        }      
        return listCategoriaProduto;
    }
     
     
     public List consultarCategoriaProdutoEstabelecimentoCompleto(int idestabelecimento) throws Exception {
        List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
        TamanhoDAO tamanhoDAO = new TamanhoDAO();
        BordaDAO bordaDAO = new BordaDAO();
        
        String sql = "SELECT distinct categoriaproduto.id as id,categoriaproduto.nome as nome  from produto,categoriaproduto where categoriaproduto.id = produto.categoria and produto.idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objCategoriaProduto = new CategoriaProduto();
                objCategoriaProduto.setId(rs.getInt("id"));
                objCategoriaProduto.setNome(rs.getString("nome"));
                //System.out.println(objCategoriaProduto.getId());
                objCategoriaProduto.setTamanho(tamanhoDAO.consultarTamanhoEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                objCategoriaProduto.setBorda(bordaDAO.consultarBordaEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                listCategoriaProduto.add(objCategoriaProduto);         
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        }      
        return listCategoriaProduto;
    }
     
     public List consultarCategoriaProdutoEstabelecimentoCompleto(int idestabelecimento, String nome) throws Exception {
        List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
        TamanhoDAO tamanhoDAO = new TamanhoDAO();
        BordaDAO bordaDAO = new BordaDAO();
        
        String sql = "SELECT distinct categoriaproduto.id as id,categoriaproduto.nome as nome  from produto,categoriaproduto where categoriaproduto.id = produto.categoria and produto.idestabelecimento = ? and upper(categoriaproduto.nome) like upper(?)";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            pstm.setString(2, "%"+nome+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                objCategoriaProduto = new CategoriaProduto();
                objCategoriaProduto.setId(rs.getInt("id"));
                objCategoriaProduto.setNome(rs.getString("nome"));
                //System.out.println(objCategoriaProduto.getId());
                objCategoriaProduto.setTamanho(tamanhoDAO.consultarTamanhoEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                objCategoriaProduto.setBorda(bordaDAO.consultarBordaEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                listCategoriaProduto.add(objCategoriaProduto);         
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        }      
        return listCategoriaProduto;
    }
     
     public CategoriaProduto consultarCategoriaProdutoEstabelecimentoCompleto(int idcategoria,int idestabelecimento) throws Exception {
       // List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
        TamanhoDAO tamanhoDAO = new TamanhoDAO();
        BordaDAO bordaDAO = new BordaDAO();
        
        String sql = "SELECT distinct categoriaproduto.id as id,categoriaproduto.nome as nome  from produto,categoriaproduto where categoriaproduto.id = produto.categoria and categoriaproduto.id = ? and produto.idestabelecimento = ?";
        try {
            //System.out.println("");
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idcategoria);
            pstm.setInt(2, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objCategoriaProduto = new CategoriaProduto();
                objCategoriaProduto.setId(rs.getInt("id"));
                objCategoriaProduto.setNome(rs.getString("nome"));
                //System.out.println(objCategoriaProduto.getId());
                objCategoriaProduto.setTamanho(tamanhoDAO.consultarTamanhoEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                objCategoriaProduto.setBorda(bordaDAO.consultarBordaEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
               
                //listCategoriaProduto.add(objCategoriaProduto);         
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        }      
        return objCategoriaProduto;
    }
     
     public CategoriaProduto consultarCategoriaProdutoEstabelecimentoBuscaCompleto(int idcategoria,int idestabelecimento) throws Exception {
       // List<CategoriaProduto> listCategoriaProduto = new ArrayList<CategoriaProduto>();
        TamanhoDAO tamanhoDAO = new TamanhoDAO();
        BordaDAO bordaDAO = new BordaDAO();
        
        String sql = "SELECT * from categoriaproduto where id = ?";
        try {
            //System.out.println("");
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idcategoria);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                objCategoriaProduto = new CategoriaProduto();
                objCategoriaProduto.setId(rs.getInt("id"));
                objCategoriaProduto.setNome(rs.getString("nome"));
                //System.out.println(objCategoriaProduto.getId());
                objCategoriaProduto.setTamanho(tamanhoDAO.consultarTamanhoEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                objCategoriaProduto.setBorda(bordaDAO.consultarBordaEstabelecimento(idestabelecimento,objCategoriaProduto.getId()));
                //listCategoriaProduto.add(objCategoriaProduto);         
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        }      
        return objCategoriaProduto;
    }
     
     
      public CategoriaProduto consultarCategoriaProdutoEstabelecimentoId(int idcategoria) throws Exception {
        String sql = "SELECT * from categoriaproduto where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idcategoria);
           
            rs = pstm.executeQuery();
            while (rs.next()) {
                objCategoriaProduto = new CategoriaProduto();
                objCategoriaProduto.setId(rs.getInt("id"));
                objCategoriaProduto.setNome(rs.getString("nome"));
                       
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        }      
        return objCategoriaProduto;
    }
    
}
