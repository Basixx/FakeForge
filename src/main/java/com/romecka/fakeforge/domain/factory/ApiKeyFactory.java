package com.romecka.fakeforge.domain.factory;

import com.romecka.fakeforge.domain.entities.ApiKey;
import com.romecka.fakeforge.utils.ApiKeyGenerator;
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
