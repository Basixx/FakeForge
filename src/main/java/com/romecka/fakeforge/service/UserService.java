package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.apikeys.ApiKey;
import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.domain.user.User;
import com.romecka.fakeforge.repository.ApiKeyRepository;
import com.romecka.fakeforge.repository.LimitRepository;
import com.romecka.fakeforge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    private final ApiKeyRepository apiKeyRepository;

    private final LimitRepository limitRepository;

    public User registerUser(User user) {
        String apiKeyA = UUID.randomUUID().toString();
        String apiSecret = bCryptPasswordEncoder.encode(apiKeyA);
        ApiKey apiKey = ApiKey.builder().apiKey(apiSecret).build();
        Limit limit = Limit.builder().dailyLimit(100).availableLimit(100).build();
        apiKey = apiKeyRepository.save(apiKey);
        limit = limitRepository.save(limit);
        user.setApiKey(apiKey);
        user.setLimit(limit);
        return userRepository.save(user);
    }

}
