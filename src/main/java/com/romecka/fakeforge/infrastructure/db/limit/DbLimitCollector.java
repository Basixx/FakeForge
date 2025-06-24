package com.romecka.fakeforge.infrastructure.db.limit;

import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.domain.limit.LimitCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbLimitCollector implements LimitCollector {

    private final LimitRepository limitRepository;

    @Override
    public Limit getLimit(Long userId) {
        return limitRepository.findByUserId(userId).orElseThrow();
    }

    @Override
    public void useLimit(Long userId) {
        LimitEntity limit = limitRepository.findByUserId(userId).orElseThrow();
        limit.availableLimit(limit.availableLimit() - 1);
        limitRepository.save(limit);
    }

}
