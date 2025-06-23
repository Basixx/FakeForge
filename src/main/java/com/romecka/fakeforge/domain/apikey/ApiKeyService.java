package com.romecka.fakeforge.domain.apikey;

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

    public Optional<ApiKey> findByRawApiKey(String rawApiKey) {
        String apiKey = ApiKeyGenerator.hashedApiKey(rawApiKey);
        return apiKeyRepository.findByApiKey(apiKey);
    }

}
