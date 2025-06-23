package com.romecka.fakeforge.domain.apikey;

public interface ApiKeyProvider {

    ApiKeyDto getApiKey(String rawApiKey);

}
