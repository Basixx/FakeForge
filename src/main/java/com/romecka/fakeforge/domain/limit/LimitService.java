package com.romecka.fakeforge.domain.limit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitProvider limitProvider;

    public LimitDto getUserLimit(Long userId) {
        return limitProvider.getLimit(userId);
    }

}
