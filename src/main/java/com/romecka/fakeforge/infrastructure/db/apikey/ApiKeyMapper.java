package com.romecka.fakeforge.infrastructure.db.apikey;

import com.romecka.fakeforge.domain.apikey.ApiKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ApiKeyMapper {

    public ApiKeyDto mapToApiKeyDto(ApiKeyEntity apiKeyEntity) {
        return new ApiKeyDto(apiKeyEntity.apiKey(), apiKeyEntity.userEntity().id());
    }

}
