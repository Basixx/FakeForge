package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.Limit;
import com.romecka.fakeforge.repository.LimitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    public Limit getUserLimit(Long userId) {
        return limitRepository.findByUserId(userId).orElseThrow();
    }

}
