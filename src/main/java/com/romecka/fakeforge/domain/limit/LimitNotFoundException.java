package com.romecka.fakeforge.domain.limit;

public class LimitNotFoundException extends RuntimeException {

    public LimitNotFoundException() {
        super("Limit not found");
    }

}
