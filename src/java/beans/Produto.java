/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author thiagocrestani
 */
public class Produto {
    
    private int id;
    private String nome;
    private String descricao;
    private Float preco;
    private int idcategoria;
    private int idtamanhoproduto;
    private String datacadastro;
    private boolean ativo;
    private String foto;
    private String saborborda;
    private String tamanho;
    private int idestablecimento;
    private String complemento;
    private String categoria;
    private int sabores = 1;

    public int getSabores() {
        return sabores;
    }

    public void setSabores(int sabores) {
        this.sabores = sabores;
    }
    
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
  
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    
    

    public String getSaborborda() {
        return saborborda;
    }

    public void setSaborborda(String saborborda) {
        this.saborborda = saborborda;
    }
   

    public int getIdestablecimento() {
        return idestablecimento;
    }

    public void setIdestablecimento(int idestablecimento) {
        this.idestablecimento = idestablecimento;
    }
    
    

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public int getIdtamanhoproduto() {
        return idtamanhoproduto;
    }

    public void setIdtamanhoproduto(int idtamanhoproduto) {
        this.idtamanhoproduto = idtamanhoproduto;
    }


    public String getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(String datacadastro) {
        this.datacadastro = datacadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
        
    
    
    
}
