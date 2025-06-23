package com.romecka.fakeforge.application.api.limit;

import com.romecka.fakeforge.domain.limit.LimitDto;

public record LimitResponse(int dailyLimit, int availableLimit) {

    public static LimitResponse fromLimit(LimitDto limitDto) {
        return new LimitResponse(limitDto.dailyLimit(), limitDto.availableLimit());
    }

}
