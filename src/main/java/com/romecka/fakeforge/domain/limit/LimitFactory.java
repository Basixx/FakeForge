package com.romecka.fakeforge.domain.limit;

import com.romecka.fakeforge.infrastructure.db.limit.Limit;
import org.springframework.stereotype.Component;

@Component
public class LimitFactory {

    public Limit createDefaultLimit() {
        return Limit.builder()
                .dailyLimit(100)
                .availableLimit(100)
                .build();
    }

}
