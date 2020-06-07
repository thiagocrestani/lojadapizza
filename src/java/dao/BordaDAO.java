/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Borda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagocrestani
 */
public class BordaDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Borda objBorda;

    public BordaDAO() {
        con = new Conexao();
    }
    
     public void cadastrar(Borda borda) {
        
         try {      
            String sql = "insert into borda values(default,?,?,?,?,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);         
            pstm.setInt(1, borda.getIdestabelecimento());
            pstm.setInt(2, borda.getIdcategoriaproduto());
            pstm.setString(3, borda.getNome());
            pstm.setFloat(4, borda.getPreco());     
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {

        }
    }
     
     
     public List consultarBordaEstabelecimento(int idestabelecimento, int idcategoria) throws Exception {
        List<Borda> listBorda = new ArrayList<Borda>();
        String sql = "SELECT * from borda where idestabelecimento = ? and idcategoriaproduto = ? and excluir = false order by id";
        objBorda = new Borda();
        objBorda.setId(0);
         
        objBorda.setIdcategoriaproduto(idcategoria);
        objBorda.setIdestabelecimento(idestabelecimento);
        objBorda.setNome("Sem borda");
        objBorda.setPreco(0);
        listBorda.add(objBorda);
        
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idestabelecimento);
            pstm.setInt(2, idcategoria);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objBorda = new Borda();
                objBorda.setId(rs.getInt("id"));
                objBorda.setNome(rs.getString("nome"));
                objBorda.setIdcategoriaproduto(rs.getInt("idcategoriaproduto"));
                objBorda.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objBorda.setPreco(rs.getFloat("preco"));
                objBorda.setExcluir(rs.getBoolean("excluir"));
                listBorda.add(objBorda);       
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return listBorda;
    }
     
     
     public Borda consultarBorda(int id) throws Exception {

        //List<Borda> listBorda = new ArrayList<Borda>();
        String sql = "SELECT * from borda where id = ? and  excluir = false";
          
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();

            while (rs.next()) {
                objBorda = new Borda();
                objBorda.setId(rs.getInt("id"));
                objBorda.setNome(rs.getString("nome"));
                objBorda.setIdcategoriaproduto(rs.getInt("idcategoriaproduto"));
                objBorda.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objBorda.setPreco(rs.getFloat("preco"));
                objBorda.setExcluir(rs.getBoolean("excluir"));
                //listBorda.add(objBorda);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objBorda;
    }
     
     
     
     public Borda consultarBorda(int id,int idestabelecimento) throws Exception {

        //List<Borda> listBorda = new ArrayList<Borda>();
        String sql = "SELECT * from borda where id = ? and idestabelecimento = ? and  excluir = false";
          
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, id);
            pstm.setInt(2, idestabelecimento);
            
            rs = pstm.executeQuery();

            while (rs.next()) {
                objBorda = new Borda();
                objBorda.setId(rs.getInt("id"));
                objBorda.setNome(rs.getString("nome"));
                objBorda.setIdcategoriaproduto(rs.getInt("idcategoriaproduto"));
                objBorda.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objBorda.setPreco(rs.getFloat("preco"));
                objBorda.setExcluir(rs.getBoolean("excluir"));
                //listBorda.add(objBorda);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objBorda;
    }
     
     public void editar(Borda borda) {
        String sql = "UPDATE borda SET nome = ?, preco = ? where id = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, borda.getNome());
            pstm.setFloat(2, borda.getPreco()); 
            pstm.setInt(3, borda.getId());
            pstm.setInt(4, borda.getIdestabelecimento());
            
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
     
     
     public void excluir(int id,int idestabelecimento) {
        String sql = "UPDATE borda SET excluir = true where id = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, idestabelecimento);
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
