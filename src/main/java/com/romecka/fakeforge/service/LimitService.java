package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.Limit;
import com.romecka.fakeforge.domain.entities.User;
import com.romecka.fakeforge.repository.LimitRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    public void createDefaultLimit(User user) {
        limitRepository.save(Limit.builder()
                .user(user)
                .dailyLimit(100)
                .availableLimit(100)
                .build()
        );
    }

    public Limit getUserLimit(Long userId) {
        return limitRepository.findByUserId(userId).orElseThrow();
    }

}
