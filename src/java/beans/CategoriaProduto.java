/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiagocrestani
 */
public class CategoriaProduto {
    private int id;
    private String Nome;
    List<Tamanho> tamanho = new ArrayList<Tamanho>();
    List<Borda> borda = new ArrayList<Borda>();
    List<Produto> produto = new ArrayList<Produto>();

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }
    

    public List<Borda> getBorda() {
        return borda;
    }

    public void setBorda(List<Borda> borda) {
        this.borda = borda;
    }
    
    

    public List<Tamanho> getTamanho() {
        return tamanho;
    }

    public void setTamanho(List<Tamanho> tamanho) {
        this.tamanho = tamanho;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    
    
}
