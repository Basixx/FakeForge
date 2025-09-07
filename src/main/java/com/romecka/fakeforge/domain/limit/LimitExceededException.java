package com.romecka.fakeforge.domain.limit;

public class LimitExceededException extends RuntimeException {

    public LimitExceededException() {
        super("Daily limit exceeded, please try again tomorrow or contact support");
    }

}
