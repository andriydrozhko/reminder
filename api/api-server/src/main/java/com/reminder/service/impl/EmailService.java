package com.reminder.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private static final String host = "smtp.gmail.com";
    private static final String port = "465";

    //TODO Update me
    private static final String USER_NAME = "";
    private static final String PASSWORD = "";
    private static final String NO_REPLY_EMAIL = "no-reply@reminder.com";
    private static Session session;

    public EmailService() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER_NAME, PASSWORD);
                }
            });
    }

    public static void sendMessage(String to, String subject, String messageText) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(NO_REPLY_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
        } catch (MessagingException e) {
            log.error("Internal server error: ", e);
        }

    }
}
