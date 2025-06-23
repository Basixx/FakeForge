package com.romecka.fakeforge.domain.apikey;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyCollector apiKeyCollector;

    private final ApiKeyProvider apiKeyProvider;

    public ApiKeyDto findByRawApiKey(String rawApiKey) {
        String apiKey = apiKeyProvider.hashedApiKey(rawApiKey);
        return apiKeyCollector.getApiKey(apiKey);
    }

}
