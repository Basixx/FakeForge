package com.romecka.fakeforge.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class ApiKeyGenerator {

    private static String generateApiKey() {
        String key = UUID.randomUUID().toString().replace("-", "");
//        TODO usunąć prntln
        System.out.println("Key: " + key);
        return key;
    }

    public static String hashedApiKey(String rawKey) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawKey.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not supported", e);
        }
    }

    public static String hashedApiKey() {
        return hashedApiKey(generateApiKey());
    }

}
