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
public class Carrinho {

    private List<Produto> listProduto = new ArrayList<Produto>();
    private double total;
    private int quantidade;
    private int estabelecimento;
    private double taxadeentrega;
    private String complemento;
    private boolean retirar;
    private boolean recarregar = false;
    private boolean estabelecimentoretirada;
    private boolean estabelecimentoentrega;

    public boolean isEstabelecimentoretirada() {
        return estabelecimentoretirada;
    }

    public void setEstabelecimentoretirada(boolean estabelecimentoretirada) {
        this.estabelecimentoretirada = estabelecimentoretirada;
    }

    public boolean isEstabelecimentoentrega() {
        return estabelecimentoentrega;
    }

    public void setEstabelecimentoentrega(boolean estabelecimentoentrega) {
        this.estabelecimentoentrega = estabelecimentoentrega;
    }
    
    

    public boolean isRecarregar() {
        return recarregar;
    }

    public void setRecarregar(boolean recarregar) {
        this.recarregar = recarregar;
    }
    
    

    public boolean isRetirar() {
        return retirar;
    }

    public void setRetirar(boolean retirar) {
        this.retirar = retirar;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public double getTaxadeentrega() {
        return taxadeentrega;
    }

    public void setTaxadeentrega(double taxadeentrega) {
        this.taxadeentrega = taxadeentrega;
    }

    public List<Produto> getListProduto() {
        return listProduto;
    }

    public void setListProduto(List<Produto> listProduto) {
        this.listProduto = listProduto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

}
