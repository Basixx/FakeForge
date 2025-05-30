package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.ApiKey;
import com.romecka.fakeforge.repository.ApiKeyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ApiKeyRepository apiKeyRepository;

    public ApiKey createApiKey() {
        String key = UUID.randomUUID().toString();
//        TODO usunac prtl
        System.out.println("Key: " + key);
        ApiKey apiKey = ApiKey.builder()
                .apiKey(bCryptPasswordEncoder.encode(key))
                .build();
        return apiKeyRepository.save(apiKey);
    }

}
