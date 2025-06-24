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
        LimitEntity limitEntity = limitRepository.findByUserEntityId(userId).orElseThrow();
        return limitMapper.mapToLimitDto(limitEntity);
    }

    @Override
    public void useLimit(Long userId) {
        LimitEntity limitEntity = limitRepository.findByUserEntityId(userId).orElseThrow();
        limitEntity.availableLimit(limitEntity.availableLimit() - 1);
        limitRepository.save(limitEntity);
    }

}
