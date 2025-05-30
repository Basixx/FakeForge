package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.limit.LimitDto;

public record UserDto(String name,
                      String lastName,
                      String emailAddress,
                      LimitDto limitDto) {
}
