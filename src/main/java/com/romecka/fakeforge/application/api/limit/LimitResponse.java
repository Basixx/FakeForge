package com.romecka.fakeforge.application.api.limit;

import com.romecka.fakeforge.domain.limit.Limit;

public record LimitResponse(int dailyLimit, int availableLimit) {

    public static LimitResponse fromLimit(Limit limit) {
        return new LimitResponse(limit.dailyLimit(), limit.availableLimit());
    }

}
