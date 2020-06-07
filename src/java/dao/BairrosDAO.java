/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao; 
import beans.Bairros;
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
public class BairrosDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Bairros objBairros;

    public BairrosDAO() {
        con = new Conexao();
    }
    
    
    public List consultarBairros(int idlocal) throws Exception {

        List<Bairros> listBairros = new ArrayList<Bairros>();
        String sql = "SELECT * from bairros where idlocais = ? and excluir = false";
         
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idlocal);           
            rs = pstm.executeQuery();
            while (rs.next()) {
                objBairros = new Bairros();
                objBairros.setId(rs.getInt("id"));
                objBairros.setNome(rs.getString("nome"));
                objBairros.setIdlocais(rs.getInt("idlocais"));
                objBairros.setExcluir(rs.getBoolean("excluir"));
                listBairros.add(objBairros);       
            }
             objConnection.close();

        } catch (SQLException ex) {          
            throw new Exception(ex);
        } 
        return listBairros;
    }
    
}
