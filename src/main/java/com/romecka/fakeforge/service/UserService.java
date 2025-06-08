package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.User;
import com.romecka.fakeforge.domain.factory.ApiKeyFactory;
import com.romecka.fakeforge.domain.factory.LimitFactory;
import com.romecka.fakeforge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ApiKeyFactory apiKeyFactory;

    private final LimitFactory limitFactory;

    public User registerUser(User user) {
        user.setApiKey(apiKeyFactory.createApiKey());
        user.setLimit(limitFactory.createDefaultLimit());
        return userRepository.save(user);
    }

}
