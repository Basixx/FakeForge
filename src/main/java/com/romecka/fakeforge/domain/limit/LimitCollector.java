package com.romecka.fakeforge.domain.limit;

public interface LimitCollector {

    Limit getLimit(Long userId);

    void useLimit(Long userId);

}
