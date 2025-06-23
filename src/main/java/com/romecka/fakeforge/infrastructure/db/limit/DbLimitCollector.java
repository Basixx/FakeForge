package com.romecka.fakeforge.infrastructure.db.limit;

import com.romecka.fakeforge.domain.limit.LimitCollector;
import com.romecka.fakeforge.domain.limit.LimitDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbLimitCollector implements LimitCollector {

    private final LimitRepository limitRepository;

    private final LimitMapper limitMapper;

    @Override
    public LimitDto getLimit(Long userId) {
        Limit limit = limitRepository.findByUserId(userId).orElseThrow();
        return limitMapper.mapToLimitDto(limit);
    }

    @Override
    public void useLimit(Long userId) {
        Limit limit = limitRepository.findByUserId(userId).orElseThrow();
        limit.setAvailableLimit(limit.getAvailableLimit() - 1);
        limitRepository.save(limit);
    }

}
