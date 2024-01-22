package fontys.sem3.school.business.interfaces.User;

import fontys.sem3.school.business.interfaces.IEmailService;
import fontys.sem3.school.business.servicesInterfaces.IEventService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {
    private final JavaMailSender javaMailSender;
    @Override
    public void sendBookingConfirmation(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try{
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
         javaMailSender.send(message);
     }catch (MessagingException e){
           throw new IllegalArgumentException("Something went wrong");
      }
   }
}
