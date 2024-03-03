package com.mailingService.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import javax.mail.Session;
import java.util.Properties;

@Configuration
public class MailSenderConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = new Properties();
        props.put("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.smtp.ssl.enable", env.getProperty("spring.mail.properties.mail.smtp.ssl.enable"));

        mailSender.setJavaMailProperties(props);

        // Log the JavaMail Session properties
        Session session = mailSender.getSession();
        Properties sessionProperties = session.getProperties();
        sessionProperties.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

        return mailSender;
    }
}
