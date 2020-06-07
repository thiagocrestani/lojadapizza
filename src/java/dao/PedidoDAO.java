/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Pedido;
import beans.Produto; 
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
public class PedidoDAO {
    
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Pedido objPedido;

    public PedidoDAO() {
        con = new Conexao();
    }
    
     public boolean cadastrar(Pedido pedido) {
        
         try {      
            String sql = "insert into pedido values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, pedido.getId());
            pstm.setInt(2, pedido.getIdcliente());
            pstm.setInt(3, pedido.getIdendereco());
            pstm.setInt(4, pedido.getIdestabelecimento());
            pstm.setInt(5, pedido.getIdformapagamento());
            pstm.setFloat(6, pedido.getItens());
            pstm.setFloat(7, pedido.getValor());
            pstm.setString(8, pedido.getTempoentrega());
            pstm.setString(9, pedido.getTaxaentrega());
            pstm.setString(10, pedido.getDatapedido());
            pstm.setString(11, pedido.getDatapedidofomatada());
            pstm.setString(12, pedido.getCliente().getNome());
            pstm.setInt(13, pedido.getCliente().getDdd());
            pstm.setInt(14, pedido.getCliente().getTelefone());
            pstm.setString(15, pedido.getCliente().getEmail());
            
            pstm.execute();
            objConnection.close();
            
        } catch (SQLException ex) {
            return false;
        }
         return true;
    }
     
     public Pedido consultarPedidoId(String id, int idestabelecimento) throws Exception {
        //List<Pedido> listPedido = new ArrayList<Pedido>();
        String sql = "select * from pedido where id like ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir"));   
             //   listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objPedido;

    }
     
     public Pedido consultarPedidoCliente(String id, int idestabelecimento, int idcliente) throws Exception {
        //List<Pedido> listPedido = new ArrayList<Pedido>();
        String sql = "select * from pedido where id like ? and idcliente = ? and idestabelecimento = ?";     
        
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setInt(2, idcliente);
            pstm.setInt(3, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir"));   
             // listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objPedido;

    }
     
     public List consultarPedidos(int idestabelecimento) throws Exception {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        String sql = "select * from pedido where idestabelecimento = ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir"));   
                listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listPedido;

    }
     
     public List consultarPedidosCompleto(int idestabelecimento) throws Exception {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        String sql = "select * from pedido where idestabelecimento = ?";
        ClienteDAO cdao = new ClienteDAO();
        EnderecoDAO edao = new EnderecoDAO();

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir")); 
                objPedido.setCliente(cdao.consultarClienteId(objPedido.getIdcliente()));
                if(objPedido.getIdendereco() != 0){
                    objPedido.setEndereco(edao.consultarEnderecoCliente(objPedido.getIdendereco(), objPedido.getIdcliente()));
                }
                listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listPedido;

    }
     
     public List consultarPedidosCompleto(int idestabelecimento,int quantidade,int inicio) throws Exception {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        String sql = "select * from pedido where idestabelecimento = ? order by id desc LIMIT ? OFFSET ?";
        ClienteDAO cdao = new ClienteDAO();
        EnderecoDAO edao = new EnderecoDAO();

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            pstm.setInt(2, quantidade);
            pstm.setInt(3, inicio);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir")); 
                objPedido.setCliente(cdao.consultarClienteId(objPedido.getIdcliente()));
                if(objPedido.getIdendereco() != 0){
                objPedido.setEndereco(edao.consultarEnderecoCliente(objPedido.getIdendereco(), objPedido.getIdcliente()));
                }
                listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listPedido;

    }
     
     
     public List consultarPedidosCompleto(int idestabelecimento,int quantidade,int inicio,boolean status) throws Exception {
        List<Pedido> listPedido = new ArrayList<Pedido>();
        String sql = "select * from pedido where idestabelecimento = ? and aberto = ? order by id desc LIMIT ? OFFSET ?";
        ClienteDAO cdao = new ClienteDAO();
        EnderecoDAO edao = new EnderecoDAO();

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            pstm.setBoolean(2, status);
            pstm.setInt(3, quantidade);
            pstm.setInt(4, inicio);
            
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir")); 
                objPedido.setCliente(cdao.consultarClienteId(objPedido.getIdcliente()));
                if(objPedido.getIdendereco() != 0){
                objPedido.setEndereco(edao.consultarEnderecoCliente(objPedido.getIdendereco(), objPedido.getIdcliente()));
                }
                listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listPedido;

    }
     
    public Pedido consultarPedidoIdCompleto(String id, int idestabelecimento) throws Exception {
        ClienteDAO cdao = new ClienteDAO();
        EnderecoDAO edao = new EnderecoDAO();
        ProdutoPedidoDAO ppdao = new ProdutoPedidoDAO();
        FormaPagamentoDAO formapagamentoDAO = new FormaPagamentoDAO();
        
        String sql = "select * from pedido where id like ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objPedido = new Pedido();
                objPedido.setId(rs.getString("id"));
                objPedido.setIdcliente(rs.getInt("idcliente"));
                objPedido.setIdendereco(rs.getInt("idendereco"));
                objPedido.setIdestabelecimento(rs.getInt("idestabelecimento"));
                objPedido.setIdformapagamento(rs.getInt("idformapagamento"));
                objPedido.setFormapagamento(formapagamentoDAO.consultar(rs.getInt("idformapagamento")).getNome());    
                objPedido.setTaxaentrega(rs.getString("taxadeentrega"));
                objPedido.setItens(rs.getFloat("itens"));
                objPedido.setValor(rs.getFloat("valor"));
                objPedido.setTempoentrega(rs.getString("tempoentrega"));
                objPedido.setDatapedido(rs.getString("datapedido"));
                objPedido.setDatapedidofomatada(rs.getString("datapedidoformatada"));
                objPedido.setAberto(rs.getBoolean("aberto"));
                objPedido.setExcluir(rs.getBoolean("excluir"));
                objPedido.setCliente(cdao.consultarClienteId(objPedido.getIdcliente()));
                if(objPedido.getIdendereco() != 0){
                objPedido.setEndereco(edao.consultarEnderecoCliente(objPedido.getIdendereco(), objPedido.getIdcliente()));
                }
                objPedido.setProdutos(ppdao.consultarProdutosPedido(objPedido.getId()));         
             //   listPedido.add(objPedido);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objPedido;

    }
    
    
     public int quantidadepedidos(int idestabelecimento) throws Exception {       
        int quantidade = 0;
        String sql = "select count(*) as quantidade from pedido where idestabelecimento = ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {       
                quantidade = rs.getInt("quantidade");    
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta " + ex.getMessage());
            throw new Exception(ex);
        }
        return quantidade;

    }
     
     public int quantidadepedidos(int idestabelecimento,boolean status) throws Exception {       
        int quantidade = 0;
        String sql = "select count(*) as quantidade from pedido where idestabelecimento = ? and aberto = ?";     

        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            pstm.setBoolean(2, status);
            rs = pstm.executeQuery();
            while (rs.next()) {       
                quantidade = rs.getInt("quantidade");    
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta " + ex.getMessage());
            throw new Exception(ex);
        }
        return quantidade;

    }
    
    public void fechar(String id,int idestabelecimento) {
        String sql = "UPDATE pedido SET aberto = false where id like ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
}
