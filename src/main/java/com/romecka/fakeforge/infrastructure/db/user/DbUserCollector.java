package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.apikey.ApiKeyProvider;
import com.romecka.fakeforge.domain.user.UserCollector;
import com.romecka.fakeforge.domain.user.UserData;
import com.romecka.fakeforge.domain.user.UserResponseDto;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKey;
import com.romecka.fakeforge.infrastructure.db.limit.Limit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DbUserCollector implements UserCollector {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ApiKeyProvider apiKeyProvider;

    public UserResponseDto registerUser(UserData userData) {
        ApiKey apiKey = ApiKey.builder()
                .apiKey(apiKeyProvider.hashedApiKey())
                .creationDateTime(LocalDateTime.now())
                .build();
        Limit limit = Limit.builder().build();
        User user = User.builder()
                .name(userData.name())
                .lastName(userData.lastName())
                .emailAddress(userData.emailAddress())
                .apiKey(apiKey)
                .limit(limit)
                .build();
        return userMapper.mapToUserResponseDto(userRepository.save(user));
    }

}
