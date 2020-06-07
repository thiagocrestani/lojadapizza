/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiagocrestani
 */
public class CategoriaUsa {

    List<Integer> tamanho = new ArrayList<Integer>();
    List<Integer> borda = new ArrayList<Integer>();

    public CategoriaUsa() {
        tamanho.add(1);
        tamanho.add(2);
        tamanho.add(4);
        tamanho.add(6);
        borda.add(1);
        borda.add(4);
    }

    public boolean tamanho(int id) {
        for (int i = 0; i < tamanho.size(); i++) {
            if (tamanho.get(i) == id) {
                return true;
            }
        }
        return false;
    }
    
    public List getTamanho() {
        return tamanho;
    }

    public boolean borda(int id) {
        for (int i = 0; i < borda.size(); i++) {
            if (borda.get(i) == id) {
                return true;
            }
        }
        return false;
    }
    
    public List getBorda() {
        return borda;
    }

}
