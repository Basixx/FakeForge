package com.romecka.fakeforge.domain.user;

public class EmailAddressInvalidException extends RuntimeException {

    public EmailAddressInvalidException(String emailAddress) {
        super("Email address %s is invalid".formatted(emailAddress));
    }

}
