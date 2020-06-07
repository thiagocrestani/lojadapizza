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
public class HorarioFuncionamento {
    private String f;
    private String a;
    private boolean funciona;
    private int idestabelecimento;
    private int id;
    private List<HorarioFuncionamento> dia = new ArrayList<HorarioFuncionamento>();

    public boolean isFunciona() {
        return funciona;
    }

    public void setFunciona(boolean funciona) {
        this.funciona = funciona;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(int idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public List<HorarioFuncionamento> getDia() {
        return dia;
    }

    public void setDia(List<HorarioFuncionamento> dia) {
        this.dia = dia;
    }
    
    
    
    
}
