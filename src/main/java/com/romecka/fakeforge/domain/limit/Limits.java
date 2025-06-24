package com.romecka.fakeforge.domain.limit;

public interface Limits {

    Limit getLimit(long userId);

    void useLimit(long userId);

}
