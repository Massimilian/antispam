package ru.progwards.java1.lessons;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) {
        Test2 t = new Test2();
        t.execute();
    }
    public static void execute() {
        final String password = "qetupoi1357098";
        final String to = "vasalekmas@yandex.ru";
        final String from = "vasalekmas@yandex.ru";
        String host = "smtp.yandex.ru";
        String port = "465"; // TLS: 25, 587;  SSL: 465
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        ((Properties) properties).setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Отчёт о сформированном jar с метаинформацией по нему");
            // прикрепляем текст сообщения
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Метаинформация по jar\n");
            multipart.addBodyPart(messageBodyPart);
            // прикрепляем файл
//            messageBodyPart = new MimeBodyPart();
//            String jarName = findJarPath();
//            DataSource source = new FileDataSource(jarName);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(jarName);
//            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
