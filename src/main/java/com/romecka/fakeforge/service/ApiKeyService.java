package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.ApiKey;
import com.romecka.fakeforge.repository.ApiKeyRepository;
import com.romecka.fakeforge.utils.ApiKeyGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public Optional<ApiKey> findByRawApiKey(String rawApiKey) {
        String apiKey = ApiKeyGenerator.hashedApiKey(rawApiKey);
        return apiKeyRepository.findByApiKey(apiKey);
    }

}
