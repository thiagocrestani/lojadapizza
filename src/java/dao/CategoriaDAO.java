/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Categoria;
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
public class CategoriaDAO {
    
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Categoria objCategoria;

    public CategoriaDAO() {
        con = new Conexao();
    }
    
    public List consultarCategoriaEstabelecimento(int idestabelecimento) throws Exception {

        List<Categoria> listCategoria = new ArrayList<Categoria>();
        String sql = "SELECT DISTINCT categoria from produto where idestabelecimento = ?";
           

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);        
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objCategoria = new Categoria();
                
                objCategoria.setNome(rs.getString("categoria"));
                
                listCategoria.add(objCategoria);
                
            }
             objConnection.close();

        } catch (SQLException ex) {
           
            throw new Exception(ex);
        }
       
        return listCategoria;

    }
    
}
