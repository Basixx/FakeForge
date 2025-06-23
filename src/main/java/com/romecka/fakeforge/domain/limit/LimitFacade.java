package com.romecka.fakeforge.domain.limit;

import com.romecka.fakeforge.application.api.limit.LimitDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LimitFacade {

    private final LimitService limitService;

    private final LimitMapper limitMapper;

    public LimitDto getUserLimit(Long userId) {
        return limitMapper.mapToLimitDto(limitService.getUserLimit(userId));
    }

}
