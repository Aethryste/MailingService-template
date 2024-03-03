package com.mailingService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    public void sendEmail(String to, String subject, String body) {
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;

        Session session = Session.getInstance(mailSender.getJavaMailProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                env.getProperty("spring.mail.username"),
                                env.getProperty("spring.mail.password")
                        );
                    }
                });

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {
            javaMailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception ex) {
            System.err.println("Failed to send email: " + ex.getMessage());
        }
    }
}
