package com.romecka.fakeforge.domain.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super("User with ID %s not found".formatted(id));
    }

}
