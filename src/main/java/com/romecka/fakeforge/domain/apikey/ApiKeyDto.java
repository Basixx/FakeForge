package com.romecka.fakeforge.domain.apikey;

public record ApiKeyDto(String hashedApiKey,
                        Long userId) {

}
