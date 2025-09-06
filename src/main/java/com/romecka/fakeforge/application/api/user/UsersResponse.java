package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.User;

import java.util.List;

public record UsersResponse(List<UserDetailsDto> users) {

    public static UsersResponse of(List<User> users) {
        return new UsersResponse(
                users.stream()
                        .map(UserDetailsDto::of)
                        .toList()

        );
    }

}
