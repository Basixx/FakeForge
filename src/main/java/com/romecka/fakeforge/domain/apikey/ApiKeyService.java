package com.romecka.fakeforge.domain.apikey;

import com.romecka.fakeforge.application.service.exception.UnauthorizedException;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKey;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKeyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyDto findByRawApiKey(String rawApiKey) {
        String apiKey = ApiKeyGenerator.hashedApiKey(rawApiKey);
        Optional<ApiKey> ak = apiKeyRepository.findByApiKey(apiKey);
        if (ak.isEmpty()) {
            throw new UnauthorizedException("Invalid API key");
        }
        return new ApiKeyDto(ak.get().getApiKey(), ak.get().getUser().getId());
    }

}
