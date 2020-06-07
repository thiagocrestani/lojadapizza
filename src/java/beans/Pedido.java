/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

/**
 *
 * @author thiagocrestani
 */
public class Pedido {

    private String id;
    private int idcliente;
    private int idendereco;
    private int idestabelecimento;
    private int idformapagamento;
    private float itens;
    private float valor;
    private String tempoentrega;
    private String taxaentrega;
    private String datapedido; 
    private String datapedidofomatada; 
    private String formapagamento; 
    private Cliente cliente;
    private Endereco endereco;
    private List<Produto> produtos;
    private boolean aberto;
    private boolean excluir;

    public String getDatapedidofomatada() {
        return datapedidofomatada;
    }

    public void setDatapedidofomatada(String datapedidofomatada) {
        this.datapedidofomatada = datapedidofomatada;
    }
    
    

    public String getFormapagamento() {
        return formapagamento;
    }

    public void setFormapagamento(String formapagamento) {
        this.formapagamento = formapagamento;
    }
    
    

    public int getIdformapagamento() {
        return idformapagamento;
    }

    public void setIdformapagamento(int idformapagamento) {
        this.idformapagamento = idformapagamento;
    }
     
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    

    public String getTaxaentrega() {
        return taxaentrega;
    }

    public void setTaxaentrega(String taxaentrega) {
        this.taxaentrega = taxaentrega;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  
   

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(int idendereco) {
        this.idendereco = idendereco;
    }

    public int getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(int idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public float getItens() {
        return itens;
    }

    public void setItens(float itens) {
        this.itens = itens;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTempoentrega() {
        return tempoentrega;
    }

    public void setTempoentrega(String tempoentrega) {
        this.tempoentrega = tempoentrega;
    }

    public String getDatapedido() {
        return datapedido;
    }

    public void setDatapedido(String datapedido) {
        this.datapedido = datapedido;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

}
