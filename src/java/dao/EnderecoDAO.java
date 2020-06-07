/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Endereco;
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
public class EnderecoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Endereco objEndereco;

    public EnderecoDAO() {
        con = new Conexao();
    }
    
    public void cadastrar(Endereco endereco) {
        
         try {      
            String sql = "insert into endereco values(default,?,?,?,?,?,?,?,?,default,?,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
           
            pstm.setString(1, endereco.getRua());
            pstm.setInt(2, endereco.getNumero());
            pstm.setString(3, endereco.getBairro());
            pstm.setString(4, endereco.getComplemento());
            pstm.setString(5, endereco.getPontodereferencia());
            pstm.setString(6, endereco.getCep());
            pstm.setString(7, endereco.getCidade());
            pstm.setString(8, endereco.getEstado());
            pstm.setInt(9, endereco.getIdcliente());
        
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
    
     public List consultarEnderecos(int id) throws Exception {

        List<Endereco> listEndereco = new ArrayList<Endereco>();
        String sql = "select * from endereco where idcliente = ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEndereco = new Endereco();
                objEndereco.setId(rs.getInt("id"));
                objEndereco.setRua(rs.getString("rua"));
                objEndereco.setNumero(rs.getInt("numero"));
                objEndereco.setBairro(rs.getString("bairro"));
                objEndereco.setComplemento(rs.getString("complemento"));
                objEndereco.setPontodereferencia(rs.getString("pontodereferencia"));
                objEndereco.setCep(rs.getString("cep"));
                objEndereco.setCidade(rs.getString("cidade"));
                objEndereco.setEstado(rs.getString("estado"));
                objEndereco.setPais(rs.getString("pais"));
                objEndereco.setIdcliente(rs.getInt("idcliente"));
                listEndereco.add(objEndereco);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listEndereco;

    }
     
     public List consultarEnderecosAtivo(int id) throws Exception {

        List<Endereco> listEndereco = new ArrayList<Endereco>();
        String sql = "select * from endereco where idcliente = ? and excluir = false";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEndereco = new Endereco();
                objEndereco.setId(rs.getInt("id"));
                objEndereco.setRua(rs.getString("rua"));
                objEndereco.setNumero(rs.getInt("numero"));
                objEndereco.setBairro(rs.getString("bairro"));
                objEndereco.setComplemento(rs.getString("complemento"));
                objEndereco.setPontodereferencia(rs.getString("pontodereferencia"));
                objEndereco.setCep(rs.getString("cep"));
                objEndereco.setCidade(rs.getString("cidade"));
                objEndereco.setEstado(rs.getString("estado"));
                objEndereco.setPais(rs.getString("pais"));
                objEndereco.setIdcliente(rs.getInt("idcliente"));
                listEndereco.add(objEndereco);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listEndereco;

    }
     
     
     public List consultarEnderecosAtivo(int id, String bairro, String cidade) throws Exception {

        List<Endereco> listEndereco = new ArrayList<Endereco>();
        String sql = "select * from endereco where idcliente = ? and excluir = false and upper(bairro) like upper(?) and upper(cidade) like upper(?)";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setString(2, bairro);
            pstm.setString(3, cidade);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEndereco = new Endereco();
                objEndereco.setId(rs.getInt("id"));
                objEndereco.setRua(rs.getString("rua"));
                objEndereco.setNumero(rs.getInt("numero"));
                objEndereco.setBairro(rs.getString("bairro"));
                objEndereco.setComplemento(rs.getString("complemento"));
                objEndereco.setPontodereferencia(rs.getString("pontodereferencia"));
                objEndereco.setCep(rs.getString("cep"));
                objEndereco.setCidade(rs.getString("cidade"));
                objEndereco.setEstado(rs.getString("estado"));
                objEndereco.setPais(rs.getString("pais"));
                objEndereco.setIdcliente(rs.getInt("idcliente"));
                listEndereco.add(objEndereco);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listEndereco;

    }
     
     
     public Endereco consultarEnderecoCliente(int idendereco,int idcliente) throws Exception {
        String sql = "select * from endereco where id = ? and idcliente = ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idendereco);
            pstm.setInt(2, idcliente);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEndereco = new Endereco();
                objEndereco.setId(rs.getInt("id"));
                objEndereco.setRua(rs.getString("rua"));
                objEndereco.setNumero(rs.getInt("numero"));
                objEndereco.setBairro(rs.getString("bairro"));
                objEndereco.setComplemento(rs.getString("complemento"));
                objEndereco.setPontodereferencia(rs.getString("pontodereferencia"));
                objEndereco.setCep(rs.getString("cep"));
                objEndereco.setCidade(rs.getString("cidade"));
                objEndereco.setEstado(rs.getString("estado"));
                objEndereco.setPais(rs.getString("pais"));
                objEndereco.setIdcliente(rs.getInt("idcliente"));
                //listEndereco.add(objEndereco);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objEndereco;

    }
     
     
     
     
      public Endereco consultarUltimoEndereco(int idcliente) throws Exception {
        String sql = "select * from endereco where id = (select max(id) from endereco where idcliente  = ?)";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idcliente);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEndereco = new Endereco();
                objEndereco.setId(rs.getInt("id"));
                objEndereco.setRua(rs.getString("rua"));
                objEndereco.setNumero(rs.getInt("numero"));
                objEndereco.setBairro(rs.getString("bairro"));
                objEndereco.setComplemento(rs.getString("complemento"));
                objEndereco.setPontodereferencia(rs.getString("pontodereferencia"));
                objEndereco.setCep(rs.getString("cep"));
                objEndereco.setCidade(rs.getString("cidade"));
                objEndereco.setEstado(rs.getString("estado"));
                objEndereco.setPais(rs.getString("pais"));
                objEndereco.setIdcliente(rs.getInt("idcliente"));
                //listEndereco.add(objEndereco);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objEndereco;

    }
     
       public void editar(Endereco endereco) {
        String sql = "UPDATE endereco SET rua = ?, numero = ?, bairro = ?, complemento = ?, pontodereferencia = ?, cep = ?, cidade = ?, estado = ? where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            
            pstm.setString(1, endereco.getRua());
            pstm.setInt(2, endereco.getNumero());
            pstm.setString(3, endereco.getBairro());
            pstm.setString(4, endereco.getComplemento());
            pstm.setString(5, endereco.getPontodereferencia());
            pstm.setString(6, endereco.getCep());
            pstm.setString(7, endereco.getCidade());
            pstm.setString(8, endereco.getEstado());
            pstm.setInt(9, endereco.getId());
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            

        }
    }
       
        public void inativar(int id) {
        String sql = "UPDATE endereco SET excluir = true where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
       }
       
       public void excluir(int id) {
        String sql = "delete from endereco where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
       }
    
}
