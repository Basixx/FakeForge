package com.romecka.fakeforge.domain.limit;

public interface LimitCollector {

    LimitDto getLimit(Long userId);

    void useLimit(Long userId);

}
