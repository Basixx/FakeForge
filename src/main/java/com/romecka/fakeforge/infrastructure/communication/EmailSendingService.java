package com.romecka.fakeforge.infrastructure.communication;

import com.romecka.fakeforge.domain.communication.EmailSender;
import com.romecka.fakeforge.domain.communication.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setTo(mail.mailTo());
            helper.setSubject(mail.subject());
            helper.setText(mail.message(), true);
            javaMailSender.send(message);
        } catch (MailException | MessagingException e) {
            log.error("Failed to process email sending: {}", e.getMessage());
        }
    }

}
