package com.romecka.fakeforge.domain.limit;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final Limits limits;

    public Limit getUserLimit(long userId) {
        return limits.getLimit(userId).orElseThrow(LimitNotFoundException::new);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetLimits() {
        limits.resetLimits();
    }

    public void updateLimit(long userId, int dailyLimit) {
        limits.updateLimit(userId, dailyLimit);
    }

}
