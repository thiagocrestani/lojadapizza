/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.MasterAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagocrestani
 */
public class MasterAdminDAO {
    
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    MasterAdmin objMasterAdmin;

    public MasterAdminDAO() {
        con = new Conexao();
    }
    
    public MasterAdmin consultarUsuario(String usuario) throws Exception {
        
        
        //List<MasterAdmin> listMasterAdmin = new ArrayList<MasterAdmin>();
        String sql = "select * from masteradmin where usuario like ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, usuario);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objMasterAdmin = new MasterAdmin();
                objMasterAdmin.setId(rs.getInt("id"));
                objMasterAdmin.setUsuario(rs.getString("usuario"));
                objMasterAdmin.setSenha(rs.getString("senha"));
             //   listMasterAdmin.add(objMasterAdmin);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
        return objMasterAdmin;
    }
    
    public int consultarMaxEstabelecimento() throws Exception {
        
        int valor = 0;
        //List<MasterAdmin> listMasterAdmin = new ArrayList<MasterAdmin>();
        String sql = "select max(id) as valor from estabelecimento";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objMasterAdmin = new MasterAdmin();
                valor = rs.getInt("valor");
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
        return valor;
    }
    
    
     public String inserir(String sql) {  
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.execute();
            objConnection.close();
            return "Sucesso!";
        } catch (SQLException ex) {
            return String.valueOf(ex);

        }
    }
    
}
