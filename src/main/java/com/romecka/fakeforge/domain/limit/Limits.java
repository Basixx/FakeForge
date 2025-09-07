package com.romecka.fakeforge.domain.limit;

import java.util.Optional;

public interface Limits {

    Optional<Limit> getLimit(long userId);

    void useLimit(long userId);

    void resetLimits();

}
