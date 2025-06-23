package com.romecka.fakeforge.domain.limit;

import com.romecka.fakeforge.infrastructure.db.limit.Limit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitMapper {

    public LimitDto mapToLimitDto(Limit limit) {
        return new LimitDto(limit.getDailyLimit(), limit.getAvailableLimit());
    }

}
