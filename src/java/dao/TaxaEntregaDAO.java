/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.TaxaEntrega;
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
public class TaxaEntregaDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    TaxaEntrega objTaxaEntrega;

    public TaxaEntregaDAO() {
        con = new Conexao();
    }
    
     public void cadastrar(TaxaEntrega taxaentrega) {
        
         try {      
            String sql = "insert into taxaentrega values(?,?,?,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);         
            pstm.setInt(1, taxaentrega.getIdestabelecimento());
            pstm.setFloat(2, taxaentrega.getDistancia());
            pstm.setFloat(3, taxaentrega.getPreco());     
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {

        }
    }
     
     
     public List consultarTaxaEntregaEstabelecimento(int idestabelecimento) throws Exception {
        List<TaxaEntrega> listTaxaEntrega = new ArrayList<TaxaEntrega>();
        String sql = "SELECT * from taxaentrega where idestabelecimento = ?  and excluir = false order by distancia";
        listTaxaEntrega.add(objTaxaEntrega);        
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objTaxaEntrega = new TaxaEntrega();
                objTaxaEntrega.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objTaxaEntrega.setPreco(rs.getFloat("preco"));
                objTaxaEntrega.setDistancia(rs.getFloat("distancia"));
                objTaxaEntrega.setExcluir(rs.getBoolean("excluir"));
                listTaxaEntrega.add(objTaxaEntrega);       
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return listTaxaEntrega;
    }
     
     
     public TaxaEntrega consultarTaxaEntrega(int idestabelecimento,float distancia) throws Exception {
        //List<TaxaEntrega> listTaxaEntrega = new ArrayList<TaxaEntrega>();
        String sql = "SELECT * from taxaentrega where idestabelecimento = ?  and distancia = (select min(distancia) as distancia from taxaentrega where distancia > ? and idestabelecimento = ?)";      
        objTaxaEntrega = null;
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idestabelecimento); 
            pstm.setFloat(2, distancia);
            pstm.setInt(3, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objTaxaEntrega = new TaxaEntrega();
                objTaxaEntrega.setDistancia(rs.getFloat("distancia"));
                objTaxaEntrega.setPreco(rs.getFloat("preco"));
                objTaxaEntrega.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objTaxaEntrega.setExcluir(rs.getBoolean("excluir"));
                //listTaxaEntrega.add(objTaxaEntrega);       
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objTaxaEntrega;
    }
     
     
     public TaxaEntrega consultarTaxaEntregaExata(int idestabelecimento,float distancia) throws Exception {
        //List<TaxaEntrega> listTaxaEntrega = new ArrayList<TaxaEntrega>();
        String sql = "SELECT * from taxaentrega where idestabelecimento = ?  and distancia = ?";      
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idestabelecimento); 
            pstm.setFloat(2, distancia);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objTaxaEntrega = new TaxaEntrega();
                objTaxaEntrega.setDistancia(rs.getFloat("distancia"));
                objTaxaEntrega.setPreco(rs.getFloat("preco"));
                objTaxaEntrega.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objTaxaEntrega.setExcluir(rs.getBoolean("excluir"));
                //listTaxaEntrega.add(objTaxaEntrega);       
            }
             objConnection.close();
        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objTaxaEntrega;
    }
     
     
     public void editar(TaxaEntrega taxaentrega) {
        String sql = "UPDATE taxaentrega SET distancia = ?, preco = ? where distancia = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setFloat(1, taxaentrega.getNovadistancia());
            pstm.setFloat(2, taxaentrega.getPreco()); 
            pstm.setFloat(3, taxaentrega.getDistancia());
            pstm.setInt(4, taxaentrega.getIdestabelecimento());
            
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
     
     
     public void excluir(int idestabelecimento, float distancia) {
        String sql = "delete from taxaentrega where distancia = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setFloat(1, distancia);
            pstm.setInt(2, idestabelecimento);   
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
