package com.email.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailService
{

    public boolean sendEmail(String subject, String message, String to)
    {
        boolean f = false;
        String host = "smtp.gmail.com";

        String from = "rentalladmn2021@gmail.com";

        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session= Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("rentalladmn2021@gmail.com", "Rentall*12345");
            }
        });

        session.setDebug(true);

        MimeMessage m = new MimeMessage(session);

        try{
            m.setFrom(from);

            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            m.setSubject(subject);

            m.setText(message);

            Transport.send(m);
            System.out.println("Sent Success......");
            f = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
