package com.romecka.fakeforge.domain.limit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitCollector limitCollector;

    public Limit getUserLimit(Long userId) {
        return limitCollector.getLimit(userId);
    }

}
