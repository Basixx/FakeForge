package com.romecka.fakeforge.domain.apikey;

public interface ApiKeyProvider {

    String hashedApiKey(String rawApiKey);

    ApiKey generateApiKey();

}
