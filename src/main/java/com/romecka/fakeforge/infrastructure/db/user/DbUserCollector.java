package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.apikey.ApiKeyProvider;
import com.romecka.fakeforge.domain.limit.LimitProvider;
import com.romecka.fakeforge.domain.user.UserCollector;
import com.romecka.fakeforge.domain.user.UserData;
import com.romecka.fakeforge.domain.user.UserResponseDto;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKeyEntity;
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbUserCollector implements UserCollector {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ApiKeyProvider apiKeyProvider;

    private final LimitProvider limitProvider;

    public UserResponseDto registerUser(UserData userData) {
        ApiKeyEntity apiKey = (ApiKeyEntity) apiKeyProvider.generateApiKey();
        LimitEntity limit = (LimitEntity) limitProvider.generateDefaultLimit();
        UserEntity user = new UserEntity()
                .name(userData.name())
                .lastName(userData.lastName())
                .emailAddress(userData.emailAddress())
                .apiKey(apiKey)
                .limit(limit);
        return userMapper.mapToUserResponseDto(userRepository.save(user));
    }

}
