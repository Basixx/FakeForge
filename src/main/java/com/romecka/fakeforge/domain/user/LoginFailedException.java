package com.romecka.fakeforge.domain.user;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException() {
        super("Wrong email address or password");
    }

}
