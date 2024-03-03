package com.mailingService.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Test
    public void testSendEmail() {
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
        message.setTo(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        message.setSubject("UnitTest: EmailServiceTest");
        message.setText("This is a test email from your Spring Boot application.");

        try {
            javaMailSender.send(message);
            // If no exception is thrown, the email was sent successfully
            System.out.println("Test email sent successfully.");
            assertTrue(true);
        }
        catch (Exception e) {
            // Log any exceptions that occur during the email sending process
            System.err.println("Failed to send test email: " + e.getMessage());
            fail();
        }
    }
}
