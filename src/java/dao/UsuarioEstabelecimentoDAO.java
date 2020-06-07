/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.UsuarioEstabelecimento;
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
public class UsuarioEstabelecimentoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    UsuarioEstabelecimento objUsuarioEstabelecimento;

    public UsuarioEstabelecimentoDAO() {
        con = new Conexao();
    }
    
    public UsuarioEstabelecimento consultarUsuario(String usuario) throws Exception {
        //List<UsuarioEstabelecimento> listUsuarioEstabelecimento = new ArrayList<UsuarioEstabelecimento>();
        String sql = "select * from usuarioestabelecimento where usuario like ? and tentativas < 30";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, usuario);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objUsuarioEstabelecimento = new UsuarioEstabelecimento();
                objUsuarioEstabelecimento.setId(rs.getInt("id"));
                objUsuarioEstabelecimento.setUsuario(rs.getString("usuario"));
                objUsuarioEstabelecimento.setSenha(rs.getString("senha"));
                objUsuarioEstabelecimento.setEstabelecimento(rs.getInt("idestabelecimento"));
                objUsuarioEstabelecimento.setTentativas(rs.getInt("tentativas"));
             //   listUsuarioEstabelecimento.add(objUsuarioEstabelecimento);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
        return objUsuarioEstabelecimento;

    }
    
    public void aumentarTentativas(UsuarioEstabelecimento usuarioestabelecimento) {
        String sql = "UPDATE usuarioestabelecimento  SET tentativas = ((select tentativas from usuarioestabelecimento where id = ?)+1) where id = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, usuarioestabelecimento.getId());
            pstm.setInt(2, usuarioestabelecimento.getId());
            pstm.setInt(3, usuarioestabelecimento.getEstabelecimento());
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public void zerarTentativas(UsuarioEstabelecimento usuarioestabelecimento) {
        String sql = "UPDATE usuarioestabelecimento  SET tentativas = 0 where id = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, usuarioestabelecimento.getId());
            pstm.setInt(2, usuarioestabelecimento.getEstabelecimento());
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
    
}
