package com.romecka.fakeforge.infrastructure.db.limit;

import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.domain.limit.LimitExceededException;
import com.romecka.fakeforge.domain.limit.LimitForUserNotFoundException;
import com.romecka.fakeforge.domain.limit.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LimitStorage implements Limits {

    private final LimitRepository limitRepository;

    @Override
    public Limit getLimit(long userId) {
        return findLimit(userId);
    }

    @Override
    public void useLimit(long userId) {
        LimitEntity limit = findLimit(userId);
        decrementLimit(limit);
    }

    @Override
    @Transactional
    public void resetLimits() {
        limitRepository.resetAllLimits();
    }

    @Override
    @Transactional
    public void updateLimit(long userId, int dailyLimit) {
        LimitEntity limit = findLimit(userId);
        limit.dailyLimit(dailyLimit);
        limitRepository.save(limit);
    }

    private void decrementLimit(LimitEntity limit) {
        if (limit.availableLimit() <= 0) {
            throw new LimitExceededException();
        }
        limit.availableLimit(limit.availableLimit() - 1);
        limitRepository.save(limit);
    }

    private LimitEntity findLimit(long userId) {
        return limitRepository.findByUserId(userId).orElseThrow(() -> new LimitForUserNotFoundException(userId));
    }

}
