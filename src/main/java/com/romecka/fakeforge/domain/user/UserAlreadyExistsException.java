package com.romecka.fakeforge.domain.user;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User with email address already exists");
    }

}
