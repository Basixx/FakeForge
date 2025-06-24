package com.romecka.fakeforge.domain.limit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final Limits limits;

    public Limit getUserLimit(long userId) {
        return limits.getLimit(userId);
    }

}
