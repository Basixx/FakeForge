package com.romecka.fakeforge.infrastructure.communication;

import com.romecka.fakeforge.domain.communication.EmailTemplate;
import com.romecka.fakeforge.domain.communication.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailCreator implements EmailTemplate {

    private final SpringTemplateEngine templateEngine;

    public Mail createRegistrationEmail(String emailAddress, String name) {
        Context context = new Context();
        context.setVariable("name", name);
        String body = templateEngine.process("registration.html", context);
        return new Mail(
                emailAddress,
                "FakeForge Registration",
                body
        );
    }

    public Mail createLimitReachingEmail(String emailAddress, int limit) {
        Context context = new Context();
        context.setVariable("limit", limit);
        String body = templateEngine.process("limitReaching.html", context);
        return new Mail(
                emailAddress,
                "You're close to reaching your daily limit",
                body
        );
    }

}
