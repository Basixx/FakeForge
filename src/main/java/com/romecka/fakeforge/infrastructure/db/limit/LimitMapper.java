package com.romecka.fakeforge.infrastructure.db.limit;

import com.romecka.fakeforge.domain.limit.LimitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LimitMapper {

    public LimitDto mapToLimitDto(Limit limit) {
        return new LimitDto(limit.dailyLimit(), limit.availableLimit());
    }

}
