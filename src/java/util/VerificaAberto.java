/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.HorarioFuncionamento;
import dao.HorarioFuncionamentoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagocrestani
 */
public class VerificaAberto {
    public boolean verificar(int idestabelecimento){
        HorarioFuncionamento horariofuncionamento = new HorarioFuncionamento();
        HorarioFuncionamentoDAO horariofuncionamentoDAO = new HorarioFuncionamentoDAO();
        SimpleDateFormat ft = new SimpleDateFormat("HHmm");
        Double horaatual = Double.parseDouble(ft.format(new Date()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
        GregorianCalendar gc = new GregorianCalendar(); 
        Date hoje = new Date();
        try {
            hoje = sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
        }
        gc.setTime(hoje);  
        int dia = gc.get(GregorianCalendar.DAY_OF_WEEK); 
        dia --;
               
        try {
            horariofuncionamento = horariofuncionamentoDAO.consultar(idestabelecimento); 
            //System.out.println("aqui " + horariofuncionamento.getDia().get(dia).getA());
            if(horaatual > Double.parseDouble(horariofuncionamento.getDia().get(dia).getA()) && horaatual < Double.parseDouble(horariofuncionamento.getDia().get(dia).getF()) && horariofuncionamento.getDia().get(dia).isFunciona()){
                //System.out.println(horariofuncionamento.getDia().get(dia).getF());    
                return true;
            }else{
                return false;
            }   
            //return true;
        } catch (Exception ex) {
            return false;
        }     

    }
    
}
