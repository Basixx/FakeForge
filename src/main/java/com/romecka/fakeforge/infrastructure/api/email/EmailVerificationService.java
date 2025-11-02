package com.romecka.fakeforge.infrastructure.api.email;

import com.romecka.fakeforge.domain.user.EmailAddressInvalidException;
import com.romecka.fakeforge.domain.user.EmailVerification;
import com.romecka.fakeforge.domain.user.EmailVerificationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.mail.verification.enabled", havingValue = "true", matchIfMissing = true)
public class EmailVerificationService implements EmailVerification {

    private final RestTemplate restTemplate;

    @Value("${whoisxml.api.endpoint}")
    private String emailCheckEndpoint;

    @Value("${whoisxml.api.key}")
    private String emailCheckKey;

    @Override
    public void verify(String email) throws EmailVerificationFailedException, EmailAddressInvalidException {
        EmailVerificationDto emailCheckResponse;
        try {
            emailCheckResponse = restTemplate.getForObject(emailCheckURL(email), EmailVerificationDto.class);
        } catch (RuntimeException ex) {
            throw new EmailVerificationFailedException();
        }
        EmailVerificationDto response = Optional.ofNullable(emailCheckResponse).orElseThrow(EmailVerificationFailedException::new);
        if (!response.emailExists()) {
            throw new EmailAddressInvalidException(email);
        }
    }

    private URI emailCheckURL(String email) {
        return emailCheckUri().queryParam("emailAddress", email).build().encode().toUri();
    }

    private UriComponentsBuilder emailCheckUri() {
        return UriComponentsBuilder.fromUriString(emailCheckEndpoint).queryParam("apiKey", emailCheckKey);
    }

}
