package com.romecka.fakeforge.infrastructure.api.email;

import com.romecka.fakeforge.domain.user.EmailVerification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(
        name = "app.mail.verification.enabled",
        havingValue = "false"
)
public class EmailVerificationLoggingService implements EmailVerification {

    @Override
    public void verify(String emailAddress) {
        log.info("Email verification disabled, no validation provided for address {}", emailAddress);
    }

}
