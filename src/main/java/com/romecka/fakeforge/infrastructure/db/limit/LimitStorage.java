package com.romecka.fakeforge.infrastructure.db.limit;

import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.domain.limit.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitStorage implements Limits {

    private final LimitRepository limitRepository;

    @Override
    public Limit getLimit(long userId) {
        return limitRepository.findByUserId(userId).orElseThrow();
    }

    @Override
    public void useLimit(long userId) {
        LimitEntity limit = limitRepository.findByUserId(userId).orElseThrow();
        limit.availableLimit(limit.availableLimit() - 1);
        limitRepository.save(limit);
    }

}
