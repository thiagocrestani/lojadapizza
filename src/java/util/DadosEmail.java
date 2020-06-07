/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Estabelecimento;
import beans.Pedido;
import dao.EstabelecimentoDAO;
import dao.PedidoDAO;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.*;

/**
 *
 * @author thiagocrestani
 */
public class DadosEmail {
    
    Pedido pedido;
    PedidoDAO pedidoDAO;
    CategoriaUsa categoriausa = new CategoriaUsa();
    FormataData formatadata = new FormataData();
    EstabelecimentoDAO estabelecimentoDAO;
    Estabelecimento estabelecimento;
    
    private void inicializa(String idpedido,int idestabelecimento) throws Exception{
        pedido = new Pedido();
        pedidoDAO = new PedidoDAO();
        estabelecimentoDAO = new EstabelecimentoDAO();
        estabelecimento = new Estabelecimento();
        
        pedido = pedidoDAO.consultarPedidoIdCompleto(idpedido,idestabelecimento); 
        estabelecimento = estabelecimentoDAO.consultarEstabelecimentoId(pedido.getIdestabelecimento());
    }

    private String conteudo() {
        String conteudo;
        

        conteudo = "<center><table  cellspacing='0px' style='width:650px;font-family:Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif'><tr style='background-color:#d05621;color:#FFFFFF'><td style='text-align:left;padding-left: 10px' >"
                + "<h2>Loja da pizza<br>"+estabelecimento.getNome()+"</h2>"
                + "</td><td style='text-align:center'>"
                + "<h4>Detalhes pedido #"+pedido.getId()+" ("+pedido.getDatapedidofomatada()+")</h4>"
                
                + "</td></tr><tr><td colspan='2' style='border:solid 1px #D6D6D6'><table  border='0' cellspacing='6px' cellpadding='10px'><tr><td  style=' background-color:#F4F4F4'>"
                + "<b>Cliente</b><table border='0' cellspacing='0' cellpadding='4px' style='width:650px'>"
                + "<tr><td style='text-align: left;'>Nome: "+pedido.getCliente().getNome()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Email: "+pedido.getCliente().getEmail()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Telefone: ("+pedido.getCliente().getDdd()+") "+pedido.getCliente().getTelefone()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Forma de pagamento: "+pedido.getFormapagamento()+"</td></tr>"
              
                + "</table></td></tr>";
                if(pedido.getIdendereco() > 0){
                conteudo += "<tr><td  style=' background-color:#F4F4F4'><b>Endereço para entrega</b><table  border='0' cellspacing='0' cellpadding='4px'style='width:650px'>"
                + "<tr><td style='text-align: left;'>Rua: "+pedido.getEndereco().getRua()+","+pedido.getEndereco().getNumero()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Bairro: "+pedido.getEndereco().getBairro()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Complemento: "+pedido.getEndereco().getComplemento()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Ponto de Referência: "+pedido.getEndereco().getPontodereferencia()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Cep: "+pedido.getEndereco().getCep()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Cidade: "+pedido.getEndereco().getCidade()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Estado: "+pedido.getEndereco().getEstado()+"</td></tr>"
                + "<tr><td style='text-align: left;'>País: "+pedido.getEndereco().getPais()+"</td></tr>";
                        }else{
                    conteudo += "<tr><td  style=' background-color:#F4F4F4'><b>Endereço para retirada</b><table  border='0' cellspacing='0' cellpadding='4px'style='width:650px'>"
               + "<tr><td style='text-align: left;'>Nome: "+estabelecimento.getNome()+"</td></tr>"
               + "<tr><td style='text-align: left;'>Telefone: ("+estabelecimento.getDdd()+") "+estabelecimento.getTelefone()+"</td></tr>"
               + "<tr><td style='text-align: left;'>Rua: "+estabelecimento.getRua()+","+estabelecimento.getNumero()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Bairro: "+estabelecimento.getBairro()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Complemento: "+estabelecimento.getComplemento()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Ponto de Referência: "+estabelecimento.getPontodereferencia()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Cep: "+estabelecimento.getCep()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Cidade: "+estabelecimento.getCidade()+"</td></tr>"
                + "<tr><td style='text-align: left;'>Estado: "+estabelecimento.getEstado()+"</td></tr>"
                + "<tr><td style='text-align: left;'>País: "+estabelecimento.getPais()+"</td></tr>";
                }
                conteudo += "</table></td></tr><tr><td><b>Produtos</b><table border='0' cellspacing='0' cellpadding='4px' style='width:650px; text-align:center;'>"
                + "<tr><td width='111' style='border-bottom: solid 1px #D6D6D6;background-color:#EFEFEF'><b>Categoria</b></td>"
                + "<td width='211' style='border-bottom: solid 1px #D6D6D6;background-color:#EFEFEF'><b>Nome</b></td>"
                + "<td width='93' style='border-bottom: solid 1px #D6D6D6;background-color:#EFEFEF'><b>Preço</b></td>"
                + "<td width='203' style='border-bottom: solid 1px #D6D6D6;background-color:#EFEFEF'><b>Complemento</b></td></tr>";
              for (int i = 0; i < pedido.getProdutos().size(); i++) {
                conteudo = conteudo + "<tr>"
                        + "<td style='border-bottom: solid 1px #D6D6D6;'>"+pedido.getProdutos().get(i).getCategoria()+"</td>"
                + "<td style='border-bottom: solid 1px #D6D6D6;'>"+pedido.getProdutos().get(i).getNome()+"</td>"
                + "<td style='border-bottom: solid 1px #D6D6D6;'>R$ "+String.format("%.2f", (double) pedido.getProdutos().get(i).getPreco())+"</td>"
                + "<td style='border-bottom: solid 1px #D6D6D6;'>"
                        + pedido.getProdutos().get(i).getTamanho() + " - " + pedido.getProdutos().get(i).getSaborborda();
                        if(pedido.getProdutos().get(i).getSabores() > 1){
                        conteudo = conteudo + " " + pedido.getProdutos().get(i).getSabores() + " Sabores ";
                        }
                        
                        conteudo = conteudo + "</td>"+ "</tr>";
                        }
                conteudo = conteudo + "</table></td></tr><tr style='background-color:#F4F4F4'>"
                + "<td>Taxa de entrega: ";
                if(pedido.getIdendereco() > 0){
                conteudo += "R$ "+String.format("%.2f", (double) Double.parseDouble(pedido.getTaxaentrega()));
                }else{
                   conteudo += "Retira no local"; 
                }      
                conteudo += "<br><b>Total:R$ "+String.format("%.2f", (double) pedido.getValor())+"</b><br>"
                + "Tempo de Entrega: "+pedido.getTempoentrega()+" minutos"
                + "</td></tr></table></td></tr></table></center>";

        return conteudo;

    }

    public Boolean envia(String idpedido,int idestabelecimento) {
        //username for abc@gmail.com will be "abc"  
        String username = "contatolojadapizza";
        String password = "kjflwms9932hdb121";
        
        Boolean result = false;
        try {
            inicializa(idpedido,idestabelecimento);
            String conteudo = conteudo();
            Properties props = System.getProperties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            Session emailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("contatolojadapizza@gmail.com", "kjflwms9932hdb121");
                        }
                    });
            emailSession.setDebug(true);
            Message message = new MimeMessage(emailSession);
            message.setFrom(new InternetAddress("lojadapizza@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(estabelecimento.getEmail()));
            
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(pedido.getCliente().getEmail()+",pedidoslojadapizza@gmail.com"));
            message.setSubject("Pedido Loja da Pizza - " + "("+pedido.getDatapedidofomatada()+") " + estabelecimento.getNome());
            //message.setText(conteudo);
            message.setContent(conteudo, "text/html");
            Transport transport = emailSession.getTransport("smtps");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(message, message.getAllRecipients());
            result = true;
       
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }
}
