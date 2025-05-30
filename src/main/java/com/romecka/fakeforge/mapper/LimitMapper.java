package com.romecka.fakeforge.mapper;

import com.romecka.fakeforge.domain.entities.Limit;
import com.romecka.fakeforge.domain.dto.LimitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitMapper {

    public LimitDto mapToLimitDto(Limit limit) {
        return new LimitDto(limit.getDailyLimit(), limit.getAvailableLimit());
    }

}
