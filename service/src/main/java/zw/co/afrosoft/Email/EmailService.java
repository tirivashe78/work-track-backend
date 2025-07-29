package zw.co.afrosoft.Email;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import zw.co.afrosoft.exceptions.EmailException ;
import zw.co.afrosoft.Notification;

@Service
@Resource
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

@Async
    public void sendEmail(Notification notification) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@afrosoft.co.zw");
            message.setTo(notification.getRecipient().getEmail());
            message.setSubject(notification.getSubject());
            message.setText(notification.getContent());
            mailSender.send(message);
        } catch (MailException e) {
            throw new EmailException("Failed to send email.", e);
        }
    }
}
