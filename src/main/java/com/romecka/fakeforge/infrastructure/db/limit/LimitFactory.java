package com.romecka.fakeforge.infrastructure.db.limit;

import com.romecka.fakeforge.domain.limit.LimitDto;
import com.romecka.fakeforge.domain.limit.LimitProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitFactory implements LimitProvider {

    private final LimitRepository limitRepository;

    public LimitDto getLimit(Long userId) {
        Limit limit = limitRepository.findByUserId(userId).orElseThrow();
        return new LimitDto(limit.getDailyLimit(), limit.getAvailableLimit());
    }

}
