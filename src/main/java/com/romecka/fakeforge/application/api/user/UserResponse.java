package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.UserResponseDto;

public record UserResponse(String name,
                           String lastName,
                           String emailAddress) {

    public static UserResponse fromUser(UserResponseDto userResponseDto) {
        return new UserResponse(
                userResponseDto.name(),
                userResponseDto.lastName(),
                userResponseDto.emailAddress()
        );
    }

}
