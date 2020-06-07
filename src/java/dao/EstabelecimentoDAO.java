/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Estabelecimento;
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
public class EstabelecimentoDAO {

    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Estabelecimento objEstabelecimento;

    public EstabelecimentoDAO() {
        con = new Conexao();
    }

    public List consultarEstabelecimento() throws Exception {

        List<Estabelecimento> listCategoria = new ArrayList<Estabelecimento>();
        String sql = "select * from estabelecimento where ativo=true order by id";

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                objEstabelecimento.setId(rs.getInt("id"));
                objEstabelecimento.setTempoentrega(rs.getString("tempoentrega"));
                objEstabelecimento.setTaxaentrega(rs.getFloat("taxadeentrega"));
                objEstabelecimento.setFoto(rs.getString("foto"));
                objEstabelecimento.setNome(rs.getString("nome"));
                objEstabelecimento.setEmail(rs.getString("email"));
                objEstabelecimento.setDdd(rs.getInt("ddd"));
                objEstabelecimento.setTelefone(rs.getString("telefone"));
                objEstabelecimento.setRua(rs.getString("rua"));
                objEstabelecimento.setNumero(rs.getInt("numero"));
                objEstabelecimento.setBairro(rs.getString("bairro"));
                objEstabelecimento.setComplemento(rs.getString("complemento"));
                objEstabelecimento.setPontodereferencia(rs.getString("pontodereferencia"));
                objEstabelecimento.setCep(rs.getString("cep"));
                objEstabelecimento.setCidade(rs.getString("cidade"));
                objEstabelecimento.setEstado(rs.getString("estado"));
                objEstabelecimento.setUfestado(rs.getString("ufestado"));
                objEstabelecimento.setPais(rs.getString("pais"));
                objEstabelecimento.setLatitude(Double.parseDouble(rs.getString("latitude")));
                objEstabelecimento.setLongitude(Double.parseDouble(rs.getString("longitude")));
                objEstabelecimento.setExcluir(rs.getBoolean("excluir"));
                objEstabelecimento.setAtivo(rs.getBoolean("ativo"));
                objEstabelecimento.setRetirada(rs.getBoolean("retirada"));
                objEstabelecimento.setEntrega(rs.getBoolean("entrega"));
                objEstabelecimento.setTemporetirada(rs.getString("temporetirada"));
                listCategoria.add(objEstabelecimento);

            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }

        return listCategoria;

    }

    public Estabelecimento consultarEstabelecimentoId(int id) throws Exception {

        String sql = "select * from estabelecimento where id = ? and ativo=true order by id";

        try {

            objConnection = con.conectar();

            pstm = this.objConnection.prepareStatement(sql);

            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                objEstabelecimento.setId(rs.getInt("id"));
                objEstabelecimento.setTempoentrega(rs.getString("tempoentrega"));
                objEstabelecimento.setTaxaentrega(rs.getFloat("taxadeentrega"));
                objEstabelecimento.setFoto(rs.getString("foto"));
                objEstabelecimento.setNome(rs.getString("nome"));
                objEstabelecimento.setEmail(rs.getString("email"));
                objEstabelecimento.setDdd(rs.getInt("ddd"));
                objEstabelecimento.setTelefone(rs.getString("telefone"));
                objEstabelecimento.setRua(rs.getString("rua"));
                objEstabelecimento.setNumero(rs.getInt("numero"));
                objEstabelecimento.setBairro(rs.getString("bairro"));
                objEstabelecimento.setComplemento(rs.getString("complemento"));
                objEstabelecimento.setPontodereferencia(rs.getString("pontodereferencia"));
                objEstabelecimento.setCep(rs.getString("cep"));
                objEstabelecimento.setCidade(rs.getString("cidade"));
                objEstabelecimento.setEstado(rs.getString("estado"));
                objEstabelecimento.setUfestado(rs.getString("ufestado"));
                objEstabelecimento.setPais(rs.getString("pais"));
                objEstabelecimento.setExcluir(rs.getBoolean("excluir"));
                objEstabelecimento.setAtivo(rs.getBoolean("ativo"));
                objEstabelecimento.setRetirada(rs.getBoolean("retirada"));
                objEstabelecimento.setEntrega(rs.getBoolean("entrega"));
                objEstabelecimento.setTemporetirada(rs.getString("temporetirada"));
                objEstabelecimento.setLatitude(Double.parseDouble(rs.getString("latitude")));
                objEstabelecimento.setLongitude(Double.parseDouble(rs.getString("longitude")));

            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objEstabelecimento;

    }
    
     public List consultarEstabelecimentoNome(String nome) throws Exception {

        List<Estabelecimento> listEstabelecimento = new ArrayList<Estabelecimento>();
        String sql = "select * from estabelecimento where upper(nome) like upper(?) and ativo=true order by id";
 
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1,"%"+nome+"%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                objEstabelecimento.setId(rs.getInt("id"));
                objEstabelecimento.setTempoentrega(rs.getString("tempoentrega"));
                objEstabelecimento.setTaxaentrega(rs.getFloat("taxadeentrega"));
                objEstabelecimento.setFoto(rs.getString("foto"));
                objEstabelecimento.setNome(rs.getString("nome"));
                objEstabelecimento.setEmail(rs.getString("email"));
                objEstabelecimento.setDdd(rs.getInt("ddd"));
                objEstabelecimento.setTelefone(rs.getString("telefone"));
                objEstabelecimento.setRua(rs.getString("rua"));
                objEstabelecimento.setNumero(rs.getInt("numero"));
                objEstabelecimento.setBairro(rs.getString("bairro"));
                objEstabelecimento.setComplemento(rs.getString("complemento"));
                objEstabelecimento.setPontodereferencia(rs.getString("pontodereferencia"));
                objEstabelecimento.setCep(rs.getString("cep"));
                objEstabelecimento.setCidade(rs.getString("cidade"));
                objEstabelecimento.setEstado(rs.getString("estado"));
                objEstabelecimento.setUfestado(rs.getString("ufestado"));
                objEstabelecimento.setPais(rs.getString("pais"));
                objEstabelecimento.setExcluir(rs.getBoolean("excluir"));
                objEstabelecimento.setAtivo(rs.getBoolean("ativo"));
                objEstabelecimento.setRetirada(rs.getBoolean("retirada"));
                objEstabelecimento.setEntrega(rs.getBoolean("entrega"));
                objEstabelecimento.setTemporetirada(rs.getString("temporetirada"));
                objEstabelecimento.setLatitude(Double.parseDouble(rs.getString("latitude")));
                objEstabelecimento.setLongitude(Double.parseDouble(rs.getString("longitude")));
                listEstabelecimento.add(objEstabelecimento);

            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }

        return listEstabelecimento;

    }
     
    
     
     
     public List consultarEstabelecimentoCategoria(String categoria) throws Exception {

        List<Estabelecimento> listEstabelecimento = new ArrayList<Estabelecimento>();
        String sql = "select * from estabelecimento where upper(categoria) like upper(?) and ativo=true order by id";

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1,"%"+categoria+"%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                objEstabelecimento.setId(rs.getInt("id"));
                objEstabelecimento.setTempoentrega(rs.getString("tempoentrega"));
                objEstabelecimento.setTaxaentrega(rs.getFloat("taxadeentrega"));
                objEstabelecimento.setFoto(rs.getString("foto"));
                objEstabelecimento.setNome(rs.getString("nome"));
                objEstabelecimento.setEmail(rs.getString("email"));
                objEstabelecimento.setDdd(rs.getInt("ddd"));
                objEstabelecimento.setTelefone(rs.getString("telefone"));
                objEstabelecimento.setRua(rs.getString("rua"));
                objEstabelecimento.setNumero(rs.getInt("numero"));
                objEstabelecimento.setBairro(rs.getString("bairro"));
                objEstabelecimento.setComplemento(rs.getString("complemento"));
                objEstabelecimento.setPontodereferencia(rs.getString("pontodereferencia"));
                objEstabelecimento.setCep(rs.getString("cep"));
                objEstabelecimento.setCidade(rs.getString("cidade"));
                objEstabelecimento.setEstado(rs.getString("estado"));
                objEstabelecimento.setUfestado(rs.getString("ufestado"));
                objEstabelecimento.setPais(rs.getString("pais"));
                objEstabelecimento.setExcluir(rs.getBoolean("excluir"));
                objEstabelecimento.setAtivo(rs.getBoolean("ativo"));
                objEstabelecimento.setRetirada(rs.getBoolean("retirada"));
                objEstabelecimento.setEntrega(rs.getBoolean("entrega"));
                objEstabelecimento.setTemporetirada(rs.getString("temporetirada"));
                objEstabelecimento.setLatitude(Double.parseDouble(rs.getString("latitude")));
                objEstabelecimento.setLongitude(Double.parseDouble(rs.getString("longitude")));
                listEstabelecimento.add(objEstabelecimento);

            }
            objConnection.close();

        } catch (SQLException ex) {
         
            throw new Exception(ex);
        }

        return listEstabelecimento;

    }
     
     
     
     
     
     
       public List consultarTodasCategoriasEstabelecimento() throws Exception {

        List<Estabelecimento> listCategoria = new ArrayList<Estabelecimento>();
        String sql = "select DISTINCT categoria from estabelecimento";

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                objEstabelecimento.setCategoria(rs.getString("categoria"));
                listCategoria.add(objEstabelecimento);

            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }

        return listCategoria;

    }
       
       
       public int consultarNomeUrl(String url) throws Exception {
        int id = 0;
        String sql = "select id from estabelecimento where nomeurl = ?";
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1,url);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                id = rs.getInt("id");
               

            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        }

        return id;

    }
       
        public void tempoentrega(int id, int minutos, int minutos2) {
        String sql = "UPDATE estabelecimento SET tempoentrega = ?,temporetirada = ? where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);          
            pstm.setInt(1, minutos);
            pstm.setInt(2, minutos2);
            pstm.setInt(3,id);
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
        
        public void taxaentrega(int id, float taxa) {
        String sql = "UPDATE estabelecimento SET taxadeentrega = ? where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);          
            pstm.setFloat(1, taxa);
            pstm.setInt(2,id);
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
        
        
        public void editar(Estabelecimento estabelecimento) {
        String sql = "UPDATE estabelecimento SET email = ?, ddd = ?, telefone = ?, rua = ?, numero = ?, bairro = ?, complemento = ?, pontodereferencia = ? where id = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, estabelecimento.getEmail());
            pstm.setInt(2, estabelecimento.getDdd());
            pstm.setString(3, estabelecimento.getTelefone());
            pstm.setString(4, estabelecimento.getRua());
            pstm.setInt(5, estabelecimento.getNumero());
            pstm.setString(6, estabelecimento.getBairro());
            pstm.setString(7, estabelecimento.getComplemento());
            pstm.setString(8, estabelecimento.getPontodereferencia());
            pstm.setInt(9, estabelecimento.getId());
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
        
    }
        
   public List consultarEstabelecimentoMaisPedidos(int quantidade) throws Exception {

        List<Estabelecimento> listCategoria = new ArrayList<Estabelecimento>();
        String sql = "select estabelecimento.id as id,estabelecimento.nome as nome, estabelecimento.id as tempoentrega,estabelecimento.id as taxadeentrega," +
"estabelecimento.foto as foto,estabelecimento.id as idestabelecimento,count(pedido.idestabelecimento) as qtdpedidos from estabelecimento, pedido where estabelecimento.id = pedido.idestabelecimento and ativo=true group by estabelecimento.id,pedido.idestabelecimento order by count(pedido.idestabelecimento) desc limit ?;";

        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1,quantidade);
            rs = pstm.executeQuery();

            while (rs.next()) {
                objEstabelecimento = new Estabelecimento();
                objEstabelecimento.setId(rs.getInt("id"));
                objEstabelecimento.setTempoentrega(rs.getString("tempoentrega"));
                objEstabelecimento.setTaxaentrega(rs.getFloat("taxadeentrega"));
                objEstabelecimento.setFoto(rs.getString("foto"));
                objEstabelecimento.setNome(rs.getString("nome")); 
                listCategoria.add(objEstabelecimento);

            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }

        return listCategoria;

    }     
         

}
