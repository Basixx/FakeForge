package com.romecka.fakeforge.domain.limit;

public interface LimitProvider {

    LimitDto getLimit(Long userId);

}
