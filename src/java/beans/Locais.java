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
public class Locais {

    private int id;
    List<Bairros> listBairros = new ArrayList<Bairros>();
    private String cep;
    private String cidade;
    private String estado;
    private String ufestado;
    private String pais;
    private Boolean excluir;

    public List<Bairros> getListBairros() {
        return listBairros;
    }

    public void setListBairros(List<Bairros> listBairros) {
        this.listBairros = listBairros;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getExcluir() {
        return excluir;
    }

    public void setExcluir(Boolean excluir) {
        this.excluir = excluir;
    }
    
    
}
