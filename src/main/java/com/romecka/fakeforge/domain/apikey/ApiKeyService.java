package com.romecka.fakeforge.domain.apikey;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeys apiKeys;

    private final ApiKeyProvider apiKeyProvider;

    public ApiKey findByRawApiKey(String rawApiKey) {
        String apiKey = apiKeyProvider.hashedApiKey(rawApiKey);
        return apiKeys.getApiKey(apiKey);
    }

}
