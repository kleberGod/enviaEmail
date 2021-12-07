
package com.mkyong;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class enviarEmail {
    public static void main(String[] args) {

        final String username = "emailemissor@gmail.com";//Email Emissor (Gmail)
        final String password = "Passw0rd";//Senha do Email emissor

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            //message.setFrom(new InternetAddress("kleber"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("emailreceptor@gmail.com")//email Receptor(Gmail)
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("teste envio email,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
