package com.romecka.fakeforge.infrastructure.db.apikey;

import com.romecka.fakeforge.domain.apikey.ApiKey;
import com.romecka.fakeforge.domain.apikey.ApiKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiKeyStorage implements ApiKeys {

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKey getApiKey(String encodedApiKey) {
        return apiKeyRepository.findByApiKey(encodedApiKey).orElseThrow();
    }

}
