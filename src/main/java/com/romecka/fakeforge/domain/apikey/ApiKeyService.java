package com.romecka.fakeforge.domain.apikey;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyProvider apiKeyProvider;

    public ApiKeyDto findByRawApiKey(String rawApiKey) {
        String apiKey = ApiKeyGenerator.hashedApiKey(rawApiKey);
        return apiKeyProvider.getApiKey(apiKey);
    }

}
