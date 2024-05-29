package fontys.sem3.school.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Set mail server host
        mailSender.setHost("smtp.gmail.com");

        // Set port
        mailSender.setPort(587);

        // Set username and password for authentication
        mailSender.setUsername("jazzclassicspurchase@gmail.com");
        mailSender.setPassword("yhvqiimxkeqkivuk");

        // Enable STARTTLS
        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");

        // Enable authentication
        mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");

        // Set trust for SSL
        mailSender.getJavaMailProperties().put("mail.smtp.ssl.trust", "*");

        return mailSender;
    }
}

