package com.romecka.fakeforge.mapper;

import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.domain.limit.LimitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitMapper {

    public LimitDto mapToLimitDto(Limit limit) {
        return new LimitDto(limit.getDailyLimit(), limit.getAvailableLimit());
    }

}
