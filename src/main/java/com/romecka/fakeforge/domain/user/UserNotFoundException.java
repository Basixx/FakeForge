package com.romecka.fakeforge.domain.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super("User with ID %s not found".formatted(id));
    }

    public UserNotFoundException(String email) {
        super("User with email address %s not found".formatted(email));
    }

}
