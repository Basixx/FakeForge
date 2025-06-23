package com.romecka.fakeforge.infrastructure.db.apikey;

import com.romecka.fakeforge.domain.apikey.ApiKeyDto;
import com.romecka.fakeforge.domain.apikey.ApiKeyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiKeyFactory implements ApiKeyProvider {

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyDto getApiKey(String rawApiKey) {
        ApiKey ak = apiKeyRepository.findByApiKey(rawApiKey).orElseThrow();
        return new ApiKeyDto(ak.getApiKey(), ak.getUser().getId());
    }

}
