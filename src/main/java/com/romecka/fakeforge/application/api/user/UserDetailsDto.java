package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.User;

public record UserDetailsDto(String name,
                             String lastName,
                             String emailAddress,
                             String role) {

    public static UserDetailsDto of(User user) {
        return new UserDetailsDto(
                user.name(),
                user.lastName(),
                user.emailAddress(),
                user.role()
        );
    }

}
