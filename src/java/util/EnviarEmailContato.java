/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author thiagocrestani
 */
public class EnviarEmailContato {
    
    public Boolean enviar(String nome,String email, String telefone, String mensagem) {
        //username for abc@gmail.com will be "abc"  
        String username = "contatolojadapizza";
        String password = "kjflwms9932hdb121";
        
        String conteudo = "nome:"+ nome + "<br>email:" + email + "<br>telefone:"+ telefone + "<br>mensagem:"+ mensagem;
        
        Boolean result = false;
        try {
           
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
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("thiagocrestani@gmail.com"));
            
           
            message.setSubject("Contato Loja da Pizza - " + nome);
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
