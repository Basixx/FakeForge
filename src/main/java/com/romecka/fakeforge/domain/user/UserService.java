package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.apikey.ApiKeyFactory;
import com.romecka.fakeforge.domain.limit.LimitFactory;
import com.romecka.fakeforge.infrastructure.db.user.User;
import com.romecka.fakeforge.infrastructure.db.user.UserRepository;
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
