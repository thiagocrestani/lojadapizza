/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Tamanho;
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
public class TamanhoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Tamanho objTamanho;

    public TamanhoDAO() {
        con = new Conexao();
    }
    
     public void cadastrar(Tamanho tamanho) {
        
         try {      
            String sql = "insert into tamanho values(default,?,?,?,?,?,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);         
            pstm.setInt(1, tamanho.getIdestabelecimento());
            pstm.setInt(2, tamanho.getIdcategoriaproduto());
            pstm.setString(3, tamanho.getNome());
            pstm.setFloat(4, tamanho.getPreco()); 
            pstm.setInt(5, tamanho.getQuantidadesabores());  
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {

        }
    }
     
     public List consultarTamanhoEstabelecimento(int idestabelecimento, int idcategoria) throws Exception {

        List<Tamanho> listTamanho = new ArrayList<Tamanho>();
        String sql = "SELECT * from tamanho where idestabelecimento = ? and idcategoriaproduto = ? and excluir = false order by id";
           

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idestabelecimento);
            pstm.setInt(2, idcategoria);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objTamanho = new Tamanho();
                objTamanho.setId(rs.getInt("id"));
                objTamanho.setNome(rs.getString("nome"));
                objTamanho.setIdcategoriaproduto(rs.getInt("idcategoriaproduto"));
                objTamanho.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objTamanho.setPreco(rs.getFloat("preco"));
                objTamanho.setExcluir(rs.getBoolean("excluir"));
                objTamanho.setQuantidadesabores(rs.getInt("quantidadesabores"));
                listTamanho.add(objTamanho);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return listTamanho;
    }
     
     
     public Tamanho consultarTamanho(int id) throws Exception {
        //List<Tamanho> listTamanho = new ArrayList<Tamanho>();
        String sql = "SELECT * from tamanho where id = ? and excluir = false";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objTamanho = new Tamanho();
                objTamanho.setId(rs.getInt("id"));
                objTamanho.setNome(rs.getString("nome"));
                objTamanho.setIdcategoriaproduto(rs.getInt("idcategoriaproduto"));
                objTamanho.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objTamanho.setPreco(rs.getFloat("preco"));
                objTamanho.setExcluir(rs.getBoolean("excluir"));
                objTamanho.setQuantidadesabores(rs.getInt("quantidadesabores"));
                     //listTamanho.add(objTamanho);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objTamanho;
    }
     
     
     
     public Tamanho consultarTamanho(int id, int idestabelecimento) throws Exception {
        //List<Tamanho> listTamanho = new ArrayList<Tamanho>();
        String sql = "SELECT * from tamanho where id = ? and idestabelecimento = ? and excluir = false";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, id);
            pstm.setInt(2, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objTamanho = new Tamanho();
                objTamanho.setId(rs.getInt("id"));
                objTamanho.setNome(rs.getString("nome"));
                objTamanho.setIdcategoriaproduto(rs.getInt("idcategoriaproduto"));
                objTamanho.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objTamanho.setPreco(rs.getFloat("preco"));
                objTamanho.setExcluir(rs.getBoolean("excluir"));
                objTamanho.setQuantidadesabores(rs.getInt("quantidadesabores"));
                     //listTamanho.add(objTamanho);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objTamanho;
    }
     
     public void excluir(int id, int idestabelecimento) {
        String sql = "UPDATE tamanho SET excluir = true where id = ? and idestabelecimento = ?";
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
     
     
     public void editar(Tamanho tamanho) {
        String sql = "UPDATE tamanho SET nome = ?, preco = ?, quantidadesabores = ? where id = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, tamanho.getNome());
            pstm.setFloat(2, tamanho.getPreco()); 
            pstm.setInt(3, tamanho.getQuantidadesabores()); 
            pstm.setInt(4, tamanho.getId());
            pstm.setInt(5, tamanho.getIdestabelecimento());
            
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
