package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.User;

public record UserResponse(String name,
                           String lastName,
                           String emailAddress) {

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.name(),
                user.lastName(),
                user.emailAddress()
        );
    }

}
