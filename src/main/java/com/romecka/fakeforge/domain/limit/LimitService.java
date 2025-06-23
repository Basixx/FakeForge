package com.romecka.fakeforge.domain.limit;

import com.romecka.fakeforge.infrastructure.db.limit.LimitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    private final LimitMapper limitMapper;

    public LimitDto getUserLimit(Long userId) {
        return limitMapper.mapToLimitDto(limitRepository.findByUserId(userId).orElseThrow());
    }

}
