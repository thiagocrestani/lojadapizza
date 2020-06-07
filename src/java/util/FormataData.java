/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thiagocrestani
 */
public class FormataData {

    private String segundo = "";
    private String minuto = "";
    private String hora = "";
    private String dia = "";
    private String mes = "";
    private String ano = "";
    private String segundoatual = "";
    private String minutoatual = "";
    private String horaatual = "";
    private String diaatual = "";
    private String mesatual = "";
    private String anoatual = "";

    private void inicializar(String data) {
        ano = data.substring(0, 4);
        mes = data.substring(4, 6);
        dia = data.substring(6, 8);
        hora = data.substring(8, 10);
        minuto = data.substring(10, 12);
        segundo = data.substring(12, 14);
    }

    private void inicializaratual(String data) {
        anoatual = data.substring(0, 4);
        mesatual = data.substring(4, 6);
        diaatual = data.substring(6, 8);
        horaatual = data.substring(8, 10);
        minutoatual = data.substring(10, 12);
        segundoatual = data.substring(12, 14);
    }

    public String datatipo1(String data) {
        inicializar(data);
        return dia + "/" + mes + "/" + ano + " - " + hora + ":" + minuto;
    }

    public String tempoDecorrido(String data) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        inicializar(data);
        String tempo = "";
        String dataatual = ft.format(new Date());
        inicializaratual(dataatual);
        //int isegundo = Integer.parseInt(segundoatual) - Integer.parseInt(segundo);
        int iminuto = Integer.parseInt(minutoatual) - Integer.parseInt(minuto);
        int ihora = Integer.parseInt(horaatual) - Integer.parseInt(hora);
        int idia = Integer.parseInt(diaatual) - Integer.parseInt(dia);
        int imes = Integer.parseInt(mesatual) - Integer.parseInt(mes);
        int iano = Integer.parseInt(anoatual) - Integer.parseInt(ano);    
        if (iano > 0) {
            if (iano < 2) {
                tempo = "ano";
            } else {
                tempo = "anos";
            }
        } else
        if (imes > 0) {
            if (imes < 2) {
                tempo = "mês";
            } else {
                tempo = "meses";
            }
        } else
        if (idia > 0) {
            if (idia < 2) {
                tempo = "dia";
            } else {
                tempo = "dias";
            }
        } else if (ihora > 0) {
            if (ihora < 2) {
                tempo = "hora";
            } else {
                tempo = "horas";
            }
        } else if (iminuto > 0) {
            if (iminuto < 2) {
                tempo = "minuto";
            } else {
               tempo = "minutos";
            }       
        } else {
            tempo = "segundos";
        }
        return tempo;
    }

    public int segundoDecorrido(String data) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        inicializar(data);
        String dataatual = ft.format(new Date());
        inicializaratual(dataatual);
        if(Integer.parseInt(segundoatual) > Integer.parseInt(segundo)){
          return Integer.parseInt(segundoatual) - Integer.parseInt(segundo);
        }else{
          return (Integer.parseInt(segundoatual)+60) - Integer.parseInt(segundo);  
        }
    }
    
    public int minutoDecorrido(String data) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        inicializar(data);
        String dataatual = ft.format(new Date());
        inicializaratual(dataatual);
        if(Integer.parseInt(minutoatual) > Integer.parseInt(minuto)){
          return Integer.parseInt(minutoatual) - Integer.parseInt(minuto);
        }else{
          return (Integer.parseInt(minutoatual)+60) - Integer.parseInt(minuto);  
        }
        
    }
    
    public int horaDecorrida(String data) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        inicializar(data);
        String dataatual = ft.format(new Date());
        inicializaratual(dataatual);
        if(Integer.parseInt(horaatual) > Integer.parseInt(hora)){
          return Integer.parseInt(horaatual) - Integer.parseInt(hora);
        }else{
          return (Integer.parseInt(horaatual)+60) - Integer.parseInt(hora);  
        }     
    }

}
