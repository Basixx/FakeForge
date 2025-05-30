package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.User;
import com.romecka.fakeforge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ApiKeyService apiKeyService;

    private final LimitService limitService;

    public User registerUser(User user) {
        user.setApiKey(apiKeyService.createApiKey());
        user.setLimit(limitService.createDefaultLimit());
        return userRepository.save(user);
    }

}
