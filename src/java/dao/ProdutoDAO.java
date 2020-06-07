/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Estabelecimento;
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
public class ProdutoDAO {

    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    Produto objProduto;
    String caminhoimagens = "http://localhost:8080/pizzaimagens/imguploaded/";

    public ProdutoDAO() {
        con = new Conexao();
    }

    public void cadastrar(Produto produto) {

        try {
            String sql = "insert into produto values(default,?,?,?,?,?,?,default,?,default)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setFloat(3, produto.getPreco());
            pstm.setInt(4, produto.getIdcategoria());
            pstm.setString(5, produto.getDatacadastro());
            pstm.setString(6, caminhoimagens + produto.getFoto());
               
            
            pstm.setInt(7, produto.getIdestablecimento());

            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {

        }
    }

    public List consultarProdutos(int idestabelecimento) throws Exception {

        List<Produto> listProduto = new ArrayList<Produto>();
        String sql = "select * from produto where idestabelecimento = ? and excluir = false order by id";

        try {

            objConnection = con.conectar();

            pstm = this.objConnection.prepareStatement(sql);

            pstm.setInt(1, idestabelecimento);

            rs = pstm.executeQuery();

            while (rs.next()) {
                objProduto = new Produto();
                objProduto.setId(rs.getInt("id"));
                objProduto.setNome(rs.getString("nome"));
                objProduto.setIdcategoria(rs.getInt("categoria"));
                objProduto.setDescricao(rs.getString("descricao"));
                objProduto.setPreco(rs.getFloat("preco"));
                objProduto.setFoto(rs.getString("foto"));
                objProduto.setDatacadastro(rs.getString("datacadastro"));
                objProduto.setAtivo(rs.getBoolean("ativo"));
                listProduto.add(objProduto);
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return listProduto;

    }

    public Produto consultarProdutoId(int idproduto) throws Exception {

        String sql = "select * from produto where id = ? and excluir = false order by id";

        try {

            objConnection = con.conectar();

            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idproduto);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objProduto = new Produto();
                objProduto.setId(rs.getInt("id"));
                objProduto.setNome(rs.getString("nome"));
                objProduto.setIdcategoria(rs.getInt("categoria"));
                objProduto.setDescricao(rs.getString("descricao"));
                objProduto.setPreco(rs.getFloat("preco"));
                objProduto.setFoto(rs.getString("foto"));
                objProduto.setDatacadastro(rs.getString("datacadastro"));
                objProduto.setAtivo(rs.getBoolean("ativo"));
            }
            objConnection.close();

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return objProduto;

    }
    
    
    public int consultarUltimoProduto(int idestabelecimento) throws Exception {
        int id = 0;
        String sql = "select * from produto where id = (select max(id) from produto) and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {     
                id = rs.getInt("id");         
            }
            objConnection.close();
        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        return id;
    }

    public List consultarProdutoCategoria(int idestabelecimento, int categoria) throws Exception {
        List<Produto> listProduto = new ArrayList<Produto>();
        String sql = "select * from produto where categoria = ? and idestabelecimento = ? and excluir = false order by id";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, categoria);
            pstm.setInt(2, idestabelecimento);
            rs = pstm.executeQuery();
            while (rs.next()) {
                objProduto = new Produto();
                objProduto.setId(rs.getInt("id"));
                objProduto.setNome(rs.getString("nome"));
                objProduto.setIdcategoria(rs.getInt("categoria"));
                objProduto.setDescricao(rs.getString("descricao"));
                objProduto.setPreco(rs.getFloat("preco"));
                objProduto.setFoto(rs.getString("foto"));
                objProduto.setDatacadastro(rs.getString("datacadastro"));
                objProduto.setAtivo(rs.getBoolean("ativo"));
                listProduto.add(objProduto);

            }

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        objConnection.close();
        return listProduto;

    }

    public List consultarProdutoCategoria(int idestabelecimento, int categoria, String nome) throws Exception {
        List<Produto> listProduto = new ArrayList<Produto>();
        String sql = "select * from produto where categoria = ? and idestabelecimento = ? and upper(nome) like upper(?) and excluir = false order by id";
        try {

            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, categoria);
            pstm.setInt(2, idestabelecimento);
            pstm.setString(3, "%" + nome + "%");
            rs = pstm.executeQuery();

            while (rs.next()) {
                objProduto = new Produto();
                objProduto.setId(rs.getInt("id"));
                objProduto.setNome(rs.getString("nome"));
                objProduto.setIdcategoria(rs.getInt("categoria"));
                objProduto.setDescricao(rs.getString("descricao"));
                objProduto.setPreco(rs.getFloat("preco"));
                objProduto.setFoto(rs.getString("foto"));
                objProduto.setDatacadastro(rs.getString("datacadastro"));
                objProduto.setAtivo(rs.getBoolean("ativo"));
                listProduto.add(objProduto);

            }

        } catch (SQLException ex) {
            System.out.println("Erro na consulta de points" + ex.getMessage());
            throw new Exception(ex);
        }
        objConnection.close();
        return listProduto;

    }

    public void editar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, descricao= ?, preco = ?, foto = ? where id = ? and idestabelecimento = ? ";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setFloat(3, produto.getPreco()); 
            pstm.setString(4, caminhoimagens + produto.getFoto());
            pstm.setInt(5, produto.getId());
            pstm.setInt(6, produto.getIdestablecimento());
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    
   

    public void excluir(int id, int idestabelecimento) {
        String sql = "UPDATE produto SET excluir = true where id = ? and idestabelecimento = ?";
        try {
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.setInt(2, idestabelecimento);
            pstm.execute();
            objConnection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void close() {
        try {
            objConnection.close();
        } catch (SQLException ex) {

        }
    }

}
