package com.romecka.fakeforge.domain.limit;

public class LimitForUserNotFoundException extends RuntimeException {

    public LimitForUserNotFoundException(long id) {
        super("Limit for user with ID %s not found".formatted(id));
    }

}
