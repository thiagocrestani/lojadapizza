/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bd.Conexao;
import beans.Produto;
import beans.ProdutoPedido;
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
public class ProdutoPedidoDAO {
    Conexao con = null;
    Connection objConnection = null;
    PreparedStatement pstm;
    ResultSet rs;
    ProdutoPedido objProdutoPedido;

    public ProdutoPedidoDAO() {
        con = new Conexao();
    }
    
     public void cadastrar(ProdutoPedido produtopedido) {    
         try {
            CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
            String sql = "insert into produtopedido values(?,?,?,?,?,?,?,?,default,?,?)";
            objConnection = con.conectar();
            pstm = this.objConnection.prepareStatement(sql); 
            pstm.setInt(1,produtopedido.getNumero());
            pstm.setString(2, produtopedido.getIdpedido());
            pstm.setInt(3, produtopedido.getIdproduto());
            pstm.setString(4, produtopedido.getProduto().getNome());
            pstm.setFloat(5, produtopedido.getProduto().getPreco());
            pstm.setString(6, produtopedido.getProduto().getTamanho());
            pstm.setString(7, produtopedido.getProduto().getSaborborda());
            pstm.setInt(8, produtopedido.getProduto().getSabores());
            pstm.setInt(9, produtopedido.getProduto().getIdcategoria());
             try {
                 pstm.setString(10, categoriaProdutoDAO.consultarCategoriaProdutoEstabelecimentoId(produtopedido.getProduto().getIdcategoria()).getNome());
             } catch (Exception ex) {
                 Logger.getLogger(ProdutoPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
             }
            pstm.execute();
            objConnection.close();
        } catch (SQLException ex) {
        System.out.println("Erro aqui mano!!!!"+ex);
        }
    }
     
         public List consultarProdutosPedido(String idpedido) throws Exception {

        List<Produto> listProduto = new ArrayList<Produto>();
        Produto objProduto = new Produto();
        String sql = "select produto.id as idproduto, produto.nome as nomeproduto, produto.descricao as descricaoproduto, produto.preco as precoproduto," +
"produto.categoria as categoriaproduto, produto.foto as fotoproduto, produto.ativo as ativoproduto, produto.idestabelecimento as idestabelecimentoproduto," +
"produto.excluir as excluirproduto, produtopedido.numero as numeroprodutopedido,  produtopedido.idpedido as idpedidoprodutopedido," +
" produtopedido.idproduto as  idprodutoprodutopedido,  produtopedido.nome as  nomeprodutopedido,  produtopedido.preco as  precoprodutopedido," +
"  produtopedido.tamanho as  tamanhoprodutopedido,  produtopedido.saborborda as  saborbordaprodutopedido,  produtopedido.excluir as  excluirprodutopedido," +
"   produtopedido.idcategoria as  idcategoriaprodutopedido,  produtopedido.categoria as  categoriaprodutopedido,  produtopedido.sabores as  sabores" +
" from produto,produtopedido where produto.id = produtopedido.idproduto and produtopedido.idpedido = ?";
           
        try {

            objConnection = con.conectar();
      
            pstm = this.objConnection.prepareStatement(sql);
            
            pstm.setString(1, idpedido);

            rs = pstm.executeQuery();

            while (rs.next()) {
                objProduto = new Produto();
                objProduto.setId(rs.getInt("idproduto"));
                objProduto.setNome(rs.getString("nomeprodutopedido"));
                objProduto.setIdcategoria(rs.getInt("idcategoriaprodutopedido"));
                objProduto.setTamanho(rs.getString("tamanhoprodutopedido"));
                objProduto.setCategoria(rs.getString("categoriaprodutopedido"));
                objProduto.setSaborborda(rs.getString("saborbordaprodutopedido"));
                objProduto.setDescricao(rs.getString("descricaoproduto"));
                objProduto.setPreco(rs.getFloat("precoprodutopedido"));
                objProduto.setFoto(rs.getString("fotoproduto"));
                objProduto.setSabores(rs.getInt("sabores"));
                //objProduto.setDatacadastro(rs.getString("datacadastroproduto"));
                objProduto.setAtivo(rs.getBoolean("ativoproduto"));
                listProduto.add(objProduto);
            }
            objConnection.close();

        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return listProduto;

    }
    
}
