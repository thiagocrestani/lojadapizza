/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.HorarioFuncionamento;
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
public class HorarioFuncionamentoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    HorarioFuncionamento objHorarioFuncionamento;

    public HorarioFuncionamentoDAO() {
        con = new Conexao();
    }
    
    public HorarioFuncionamento consultar(int idestabelecimento) throws Exception {

        List<HorarioFuncionamento> listHorarioFuncionamento = new ArrayList<HorarioFuncionamento>();
        HorarioFuncionamento dia = new HorarioFuncionamento();
        String sql = "select * from horariofuncionamento where idestabelecimento = ?";
           

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql); 
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objHorarioFuncionamento = new HorarioFuncionamento();
                objHorarioFuncionamento.setId(rs.getInt("id"));
                objHorarioFuncionamento.setIdestabelecimento(rs.getInt("idestabelecimento"));
                dia.setA(rs.getString("adomingo"));
                dia.setF(rs.getString("fdomingo"));
                dia.setFunciona(rs.getBoolean("domingo"));
                listHorarioFuncionamento.add(dia); 
                dia = new HorarioFuncionamento();
                dia.setA(rs.getString("asegunda"));
                dia.setF(rs.getString("fsegunda"));
                dia.setFunciona(rs.getBoolean("segunda"));
                listHorarioFuncionamento.add(dia);
                dia = new HorarioFuncionamento();
                dia.setA(rs.getString("aterca"));
                dia.setF(rs.getString("fterca"));
                dia.setFunciona(rs.getBoolean("terca"));
                listHorarioFuncionamento.add(dia);
                dia = new HorarioFuncionamento();
                dia.setA(rs.getString("aquarta"));
                dia.setF(rs.getString("fquarta"));
                dia.setFunciona(rs.getBoolean("quarta"));
                listHorarioFuncionamento.add(dia);
                dia = new HorarioFuncionamento();
                dia.setA(rs.getString("aquinta"));
                dia.setF(rs.getString("fquinta"));
                dia.setFunciona(rs.getBoolean("quinta"));
                listHorarioFuncionamento.add(dia);
                dia = new HorarioFuncionamento();
                dia.setA(rs.getString("asexta"));
                dia.setF(rs.getString("fsexta"));
                dia.setFunciona(rs.getBoolean("sexta"));
                listHorarioFuncionamento.add(dia);
                dia = new HorarioFuncionamento();
                dia.setA(rs.getString("asabado"));
                dia.setF(rs.getString("fsabado"));
                dia.setFunciona(rs.getBoolean("sabado"));
                listHorarioFuncionamento.add(dia);                  
                objHorarioFuncionamento.setDia(listHorarioFuncionamento); 
               
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro aqui manadro" + ex.getMessage());
            throw new Exception(ex);
        }
        return objHorarioFuncionamento;

    }
    
    public void editar(HorarioFuncionamento horarioFuncionamento) {
        String sql = "UPDATE horariofuncionamento SET adomingo = ?, fdomingo = ?,domingo = ?, asegunda = ?, fsegunda = ?,segunda = ?, aterca = ?, fterca = ?,terca = ?, aquarta = ?, fquarta = ?,quarta = ?, aquinta = ?, fquinta = ?,quinta = ?, asexta = ?, fsexta = ?,sexta = ?, asabado = ?, fsabado = ?,sabado = ? where idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            
            pstm.setString(1, horarioFuncionamento.getDia().get(0).getA());
            pstm.setString(2, horarioFuncionamento.getDia().get(0).getF());
            pstm.setBoolean(3, horarioFuncionamento.getDia().get(0).isFunciona());
            pstm.setString(4, horarioFuncionamento.getDia().get(1).getA());
            pstm.setString(5, horarioFuncionamento.getDia().get(1).getF());
            pstm.setBoolean(6, horarioFuncionamento.getDia().get(1).isFunciona());
            pstm.setString(7, horarioFuncionamento.getDia().get(2).getA());
            pstm.setString(8, horarioFuncionamento.getDia().get(2).getF());
            pstm.setBoolean(9, horarioFuncionamento.getDia().get(2).isFunciona());
            pstm.setString(10, horarioFuncionamento.getDia().get(3).getA());
            pstm.setString(11, horarioFuncionamento.getDia().get(3).getF());
            pstm.setBoolean(12, horarioFuncionamento.getDia().get(3).isFunciona());
            pstm.setString(13, horarioFuncionamento.getDia().get(4).getA());
            pstm.setString(14, horarioFuncionamento.getDia().get(4).getF());
            pstm.setBoolean(15, horarioFuncionamento.getDia().get(4).isFunciona());
            pstm.setString(16, horarioFuncionamento.getDia().get(5).getA());
            pstm.setString(17, horarioFuncionamento.getDia().get(5).getF());
            pstm.setBoolean(18, horarioFuncionamento.getDia().get(5).isFunciona());
            pstm.setString(19, horarioFuncionamento.getDia().get(6).getA());
            pstm.setString(20, horarioFuncionamento.getDia().get(6).getF());
            pstm.setBoolean(21, horarioFuncionamento.getDia().get(6).isFunciona());
            pstm.setInt(22, horarioFuncionamento.getIdestabelecimento());
            
           
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
