package fontys.sem3.school.business.interfaces;

public interface IEmailService {
    void sendBookingConfirmation(String to, String subject, String body);
}
