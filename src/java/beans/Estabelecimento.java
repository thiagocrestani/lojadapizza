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
public class Estabelecimento {
    private int id;
    private String nome;
    private String categoria;
    private String foto;
    private String tempoentrega;
    private String temporetirada;
    private float taxaentrega;
    private String rua;
    private int numero;
    private String bairro;
    private String complemento;
    private String pontodereferencia;
    private String cep;
    private String cidade;
    private String estado;
    private String ufestado;
    private String pais;
    private String email;
    private int ddd;
    private String telefone;
    private boolean excluir;
    private boolean ativo;
    private boolean entrega;
    private boolean retirada;
    public double latitude;
    public double longitude;

    public String getTemporetirada() {
        return temporetirada;
    }

    public void setTemporetirada(String temporetirada) {
        this.temporetirada = temporetirada;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public boolean isRetirada() {
        return retirada;
    }

    public void setRetirada(boolean retirada) {
        this.retirada = retirada;
    }
    
    

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

   

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPontodereferencia() {
        return pontodereferencia;
    }

    public void setPontodereferencia(String pontodereferencia) {
        this.pontodereferencia = pontodereferencia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUfestado() {
        return ufestado;
    }

    public void setUfestado(String ufestado) {
        this.ufestado = ufestado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

   

  

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
   

    public String getTempoentrega() {
        return tempoentrega;
    }

    public void setTempoentrega(String tempoentrega) {
        this.tempoentrega = tempoentrega;
    }

    public float getTaxaentrega() {
        return taxaentrega;
    }

    public void setTaxaentrega(float taxaentrega) {
        this.taxaentrega = taxaentrega;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
}
