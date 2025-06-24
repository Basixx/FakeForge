package com.romecka.fakeforge.infrastructure.db.apikey;

import com.romecka.fakeforge.domain.apikey.ApiKey;
import com.romecka.fakeforge.domain.apikey.ApiKeyCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbApiKeyCollector implements ApiKeyCollector {

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKey getApiKey(String rawApiKey) {
        return apiKeyRepository.findByApiKey(rawApiKey).orElseThrow();
    }

}
