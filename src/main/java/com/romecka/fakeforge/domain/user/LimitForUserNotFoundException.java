package com.romecka.fakeforge.domain.user;

public class LimitForUserNotFoundException extends RuntimeException {

    public LimitForUserNotFoundException(long id) {
        super("Limit for user with ID %s not found".formatted(id));
    }

}
