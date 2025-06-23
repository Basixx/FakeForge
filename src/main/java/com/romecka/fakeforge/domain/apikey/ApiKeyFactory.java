package com.romecka.fakeforge.domain.apikey;

import com.romecka.fakeforge.infrastructure.db.apikey.ApiKey;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApiKeyFactory {

    public ApiKey createApiKey() {
        String key = ApiKeyGenerator.hashedApiKey();
        return ApiKey.builder()
                .apiKey(key)
                .creationDateTime(LocalDateTime.now())
                .build();
    }

}
