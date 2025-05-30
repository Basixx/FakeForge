package com.romecka.fakeforge.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.romecka.fakeforge.domain.limit.LimitDto;

public record UserResponseDto(String name,
                              String lastName,
                              String emailAddress,
                              @JsonProperty("limit") LimitDto limitDto) {
}
