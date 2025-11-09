package com.romecka.fakeforge.infrastructure.communication;

import com.romecka.fakeforge.domain.communication.EmailSender;
import com.romecka.fakeforge.domain.communication.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "app.mail.sending.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class EmailSendingService implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.mailTo());
            message.setSubject(mail.subject());
            message.setText(mail.message());
            javaMailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to process email sending: {}", e.getMessage());
        }
    }

}
