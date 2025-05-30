package com.romecka.fakeforge.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(String name,
                              String lastName,
                              String emailAddress,
                              @JsonProperty("limit") LimitDto limitDto) {
}
