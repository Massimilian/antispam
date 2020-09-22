package ru.progwards.java1.lessons;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Test {
    public static void main(String[] args) throws IOException, MessagingException {
        FileInputStream fis = new FileInputStream("src/main/resources/properties");
        Properties properties = new Properties();
        properties.load(fis);

//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("masvas84@gmail.com", "qetupoi1357098");
//            }
//        });

        Session session = Session.getInstance(properties, null);
        Message message = new MimeMessage(session);

        InternetAddress from = new InternetAddress("masvas84@gmail.com");
        message.setSubject("Subject");
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("masvas84@gmail.com"));

        message.setText("Text");

        Transport.send(message);

//        Transport transport = session.getTransport("smtp");
//        transport.connect("smtp.gmail.com", "mailFrom@gmail.com", "password");
//        transport.sendMessage(message, message.getAllRecipients());
    }

}
