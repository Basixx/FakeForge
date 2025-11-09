package com.romecka.fakeforge.infrastructure.communication;

import com.romecka.fakeforge.domain.communication.EmailSender;
import com.romecka.fakeforge.domain.communication.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import static com.romecka.fakeforge.utils.StringUtils.mask;

@Slf4j
@Service
@ConditionalOnProperty(
        name = "app.mail.sending.enabled",
        havingValue = "false"
)
public class EmailSendingLoggingService implements EmailSender {

    @Override
    public void sendEmail(Mail mail) {
        log.info("Email sending disabled, skipping email to {}", mask(mail.mailTo()));
    }

}
