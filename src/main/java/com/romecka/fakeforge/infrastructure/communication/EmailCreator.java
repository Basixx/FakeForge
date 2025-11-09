package com.romecka.fakeforge.infrastructure.communication;

import com.romecka.fakeforge.domain.communication.EmailTemplate;
import com.romecka.fakeforge.domain.communication.Mail;
import org.springframework.stereotype.Service;

@Service
public class EmailCreator implements EmailTemplate {

    public Mail createRegistrationEmail(String emailAddress, String name) {
        return new Mail(
                emailAddress,
                "FakeForge Registration",
                "Hello, %s! Welcome to FakeForge!".formatted(name)
        );
    }

}
