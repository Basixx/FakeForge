package com.romecka.fakeforge.domain.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.romecka.fakeforge.utils.StringUtils.mask;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final EmailSender emailSender;

    private final EmailTemplate emailTemplate;

    public void sendRegistrationEmail(String emailAddress, String name) {
        log.info("Sending registration email to {}", mask(emailAddress));
        emailSender.sendEmail(emailTemplate.createRegistrationEmail(emailAddress, name));
    }

    public void sendLimitReachingEmail(String emailAddress, int limit) {
        log.info("Sending limit reaching email to {}", mask(emailAddress));
        emailSender.sendEmail(emailTemplate.createLimitReachingEmail(emailAddress, limit));
    }

}
