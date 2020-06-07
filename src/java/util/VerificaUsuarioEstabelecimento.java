package util;

import beans.UsuarioEstabelecimento;
import dao.UsuarioEstabelecimentoDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thiagocrestani
 */
public class VerificaUsuarioEstabelecimento {

    //UsuarioEstabelecimento usuarioestabelecimentoSession;
    //

    UsuarioEstabelecimentoDAO usuarioestabelecimentoDAO = new UsuarioEstabelecimentoDAO();

    /*public VerificaUsuarioEstabelecimento(UsuarioEstabelecimento usuarioestabelecimento, UsuarioEstabelecimento usuarioestabelecimentoSession){
     this.usuarioestabelecimentoSession = usuarioestabelecimentoSession;
     this.usuarioestabelecimento = usuarioestabelecimento;
     }*/
    public boolean verificar(UsuarioEstabelecimento usuarioestabelecimentoSession) {
        try {
            
            if (usuarioestabelecimentoSession != null) {
                UsuarioEstabelecimento usuarioestabelecimento;
                usuarioestabelecimento = usuarioestabelecimentoDAO.consultarUsuario(usuarioestabelecimentoSession.getUsuario());
                if (!usuarioestabelecimento.getSenha().equals(usuarioestabelecimentoSession.getSenha())) {
                    usuarioestabelecimentoDAO.aumentarTentativas(usuarioestabelecimento);
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

}
