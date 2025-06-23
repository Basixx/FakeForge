package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.apikey.ApiKeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiKeyGenerator implements ApiKeyProvider {

    private static String generateApiKey() {
        String key = UUID.randomUUID().toString().replace("-", "");
//        TODO usunąć prntln
        System.out.println("Key: " + key);
        return key;
    }

    @Override
    public String hashedApiKey(String rawKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawKey.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }

    public String hashedApiKey() {
        return hashedApiKey(generateApiKey());
    }

}
