/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import beans.FormaPagamento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiagocrestani
 */
public class FormaPagamentoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    FormaPagamento objFormaPagamento;

    public FormaPagamentoDAO() {
        con = new Conexao();
    }
    
    
      public FormaPagamento consultar(int id) throws Exception {
        //List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
        String sql = "select * from formapagamento where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objFormaPagamento = new FormaPagamento();
                objFormaPagamento.setId(rs.getInt("id"));
                objFormaPagamento.setNome(rs.getString("nome"));
                objFormaPagamento.setIcone(rs.getString("icone"));
                objFormaPagamento.setExcluir(rs.getBoolean("excluir"));
                //listFormaPagamento.add(objFormaPagamento);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
        return objFormaPagamento;
    }
      
      public FormaPagamento consultar(String nome) throws Exception {
        //List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
        String sql = "select * from formapagamento where nome like ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objFormaPagamento = new FormaPagamento();
                objFormaPagamento.setId(rs.getInt("id"));
                objFormaPagamento.setNome(rs.getString("nome"));
                objFormaPagamento.setIcone(rs.getString("icone"));
                objFormaPagamento.setExcluir(rs.getBoolean("excluir"));
                //listFormaPagamento.add(objFormaPagamento);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
        return objFormaPagamento;
    }
      
      
      public List consultarTodas() throws Exception {
        List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
        String sql = "select * from formapagamento where excluir = false";
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
          
            //pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objFormaPagamento = new FormaPagamento();
                objFormaPagamento.setId(rs.getInt("id"));
                objFormaPagamento.setNome(rs.getString("nome"));
                objFormaPagamento.setIcone(rs.getString("icone"));
                objFormaPagamento.setExcluir(rs.getBoolean("excluir"));
                listFormaPagamento.add(objFormaPagamento);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
        return listFormaPagamento;
    }
}
