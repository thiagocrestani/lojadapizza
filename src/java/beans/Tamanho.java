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
public class Tamanho {
    private int id;
    private int idestabelecimento;
    private int idcategoriaproduto;
    private String nome;
    private float preco;
    private boolean excluir;
    private int quantidadesabores;

    public int getQuantidadesabores() {
        return quantidadesabores;
    }

    public void setQuantidadesabores(int quantidadesabores) {
        this.quantidadesabores = quantidadesabores;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(int idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public int getIdcategoriaproduto() {
        return idcategoriaproduto;
    }

    public void setIdcategoriaproduto(int idcategoriaproduto) {
        this.idcategoriaproduto = idcategoriaproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

}
