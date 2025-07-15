package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.apikey.ApiKeyProvider;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKeyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyGenerator implements ApiKeyProvider {

    private final PasswordEncoder encoder;

    @Override
    public String hashedApiKey(String rawKey) {
        return encoder.encode(rawKey);
    }

    public ApiKeyEntity generateApiKey() {
        return new ApiKeyEntity()
                .apiKey(hashedApiKey(generateKey()))
                .creationDateTime(LocalDateTime.now());
    }

    private static String generateKey() {
        String key = UUID.randomUUID().toString().replace("-", "");
//        TODO usunąć prntln
        System.out.println("Key: " + key);
        return key;
    }

}
