package com.romecka.fakeforge.domain.user;

public class EmailVerificationFailedException extends RuntimeException {

    public EmailVerificationFailedException() {
        super("Email verification failed, try again later or contact support");
    }

}
