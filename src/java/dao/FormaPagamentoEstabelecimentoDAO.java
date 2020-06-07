/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Estabelecimento;
import beans.FormaPagamento;
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
public class FormaPagamentoEstabelecimentoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    FormaPagamento objFormaPagamento;
    Estabelecimento objEstabelecimento;
    
    public FormaPagamentoEstabelecimentoDAO() {
        con = new Conexao();
    }
    
    public void cadastrar(int idestabelecimento, int idformapagamento) {
        
         try {      
            String sql = "insert into formapagamentoestabelecimento values(?,?,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            pstm.setInt(2, idformapagamento);
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {

        }
    }
    
    public void limparformaspagamento(int idestabelecimento) {
        String sql = "delete from formapagamentoestabelecimento where idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ////////////////
    public List consultarFormasPagamentoEstabelecimento(int idestabelecimento) throws Exception {
        List<FormaPagamento> listFormaPagamento = new ArrayList<FormaPagamento>();
        FormaPagamentoDAO formapagamentoDAO = new FormaPagamentoDAO();
        String sql = "select * from formapagamentoestabelecimento where excluir = false and idestabelecimento = ?";
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objFormaPagamento = new FormaPagamento();
                objFormaPagamento = formapagamentoDAO.consultar(rs.getInt("idformapagamento"));
                listFormaPagamento.add(objFormaPagamento);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
         return listFormaPagamento;
       
    }
    
    ////////////////
    public List consultarIdFormasPagamentoEstabelecimento(int idestabelecimento) throws Exception {
        List<Integer> listFormaPagamento = new ArrayList<Integer>();
        //FormaPagamentoDAO formapagamentoDAO = new FormaPagamentoDAO();
        String sql = "select * from formapagamentoestabelecimento where excluir = false and idestabelecimento = ?";
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();

            while (rs.next()) {
               // objFormaPagamento = new FormaPagamento();
                //objFormaPagamento = rs.getInt("idformapagamento");
                listFormaPagamento.add(rs.getInt("idformapagamento"));
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }
         return listFormaPagamento;
       
    }
    
    
    
}
