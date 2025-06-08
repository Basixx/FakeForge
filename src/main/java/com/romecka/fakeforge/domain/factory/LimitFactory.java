package com.romecka.fakeforge.domain.factory;

import com.romecka.fakeforge.domain.entities.Limit;
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
