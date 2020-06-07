/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Cliente;
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
public class ClienteDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Cliente objCliente;

    public ClienteDAO() {
        con = new Conexao();
    }
    
     public void cadastrar(Cliente cliente) {
        
         try {      
            String sql = "insert into cliente values(default,?,?,?,?,?)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
         
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getDdd());
            pstm.setInt(3, cliente.getTelefone());
            pstm.setString(4, cliente.getEmail());
            pstm.setString(5, cliente.getSenha());
      
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {

        }
    }
     
     public Cliente consultarClienteEmail(String email) throws Exception {

        //List<Cliente> listCliente = new ArrayList<Cliente>();
        String sql = "select * from cliente where email like ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objCliente = new Cliente();
                objCliente.setId(rs.getInt("id"));
                objCliente.setNome(rs.getString("nome"));
                objCliente.setDdd(rs.getInt("ddd"));
                objCliente.setTelefone(rs.getInt("telefone"));
                objCliente.setEmail(rs.getString("email"));
                objCliente.setSenha(rs.getString("senha"));
              
             //   listCliente.add(objCliente);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objCliente;

    }
     
      public Cliente consultarClienteId(int id) throws Exception {

        //List<Cliente> listCliente = new ArrayList<Cliente>();
        String sql = "select * from cliente where id = ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objCliente = new Cliente();
                objCliente.setId(rs.getInt("id"));
                objCliente.setNome(rs.getString("nome"));
                objCliente.setDdd(rs.getInt("ddd"));
                objCliente.setTelefone(rs.getInt("telefone"));
                objCliente.setEmail(rs.getString("email"));
                objCliente.setSenha(rs.getString("senha"));
              
             //   listCliente.add(objCliente);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objCliente;

    }
     
     public void editar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, ddd = ?, telefone= ?, email = ?, senha = ? where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            
            pstm.setString(1, cliente.getNome());
            pstm.setInt(2, cliente.getDdd());
            pstm.setInt(3, cliente.getTelefone());
            pstm.setString(4, cliente.getEmail());
            pstm.setString(5, cliente.getSenha());
            pstm.setInt(6, cliente.getId());
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            

        }
    }
    
}
