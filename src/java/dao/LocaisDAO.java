/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Locais;
import beans.Locais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thiagocrestani
 */
public class LocaisDAO {
    
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Locais objLocais;

    public LocaisDAO() {
        con = new Conexao();
    }
    
    public Locais consultarLocais(int id) throws Exception {

        //List<Locais> listLocais = new ArrayList<Locais>();
        String sql = "SELECT * from locais where id = ? and excluir = false";
          
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();

            while (rs.next()) {
                objLocais = new Locais();
                objLocais.setId(rs.getInt("id"));
                objLocais.setCep(rs.getString("cep"));
                objLocais.setCidade(rs.getString("cidade"));
                objLocais.setEstado(rs.getString("estado"));
                objLocais.setUfestado(rs.getString("ufestado"));
                objLocais.setPais(rs.getString("pais"));                
                objLocais.setExcluir(rs.getBoolean("excluir"));
                //listLocais.add(objLocais);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objLocais;
    }
    
     public Locais consultarLocaisComBairros(int id) throws Exception {

        //List<Locais> listLocais = new ArrayList<Locais>();
        String sql = "SELECT * from locais where id = ? and excluir = false";
        BairrosDAO bairrosDAO = new BairrosDAO();
          
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();

            while (rs.next()) {
                objLocais = new Locais();
                objLocais.setId(rs.getInt("id"));
                objLocais.setCep(rs.getString("cep"));
                objLocais.setCidade(rs.getString("cidade"));
                objLocais.setEstado(rs.getString("estado"));
                objLocais.setUfestado(rs.getString("ufestado"));
                objLocais.setPais(rs.getString("pais"));                
                objLocais.setExcluir(rs.getBoolean("excluir"));
                objLocais.setListBairros(bairrosDAO.consultarBairros(id));
                //listLocais.add(objLocais);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return objLocais;
    }
    
}
