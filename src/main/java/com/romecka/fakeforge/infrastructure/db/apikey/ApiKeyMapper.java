package com.romecka.fakeforge.infrastructure.db.apikey;

import com.romecka.fakeforge.domain.apikey.ApiKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ApiKeyMapper {

    public ApiKeyDto mapToApiKeyDto(ApiKeyEntity apiKey) {
        return new ApiKeyDto(apiKey.apiKey(), apiKey.user().id());
    }

}
