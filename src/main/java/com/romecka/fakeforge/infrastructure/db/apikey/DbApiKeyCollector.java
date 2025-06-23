package com.romecka.fakeforge.infrastructure.db.apikey;

import com.romecka.fakeforge.domain.apikey.ApiKeyCollector;
import com.romecka.fakeforge.domain.apikey.ApiKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbApiKeyCollector implements ApiKeyCollector {

    private final ApiKeyRepository apiKeyRepository;

    private final ApiKeyMapper apiKeyMapper;

    @Override
    public ApiKeyDto getApiKey(String rawApiKey) {
        ApiKey apiKey = apiKeyRepository.findByApiKey(rawApiKey).orElseThrow();
        return apiKeyMapper.mapToApiKeyDto(apiKey);
    }

}
