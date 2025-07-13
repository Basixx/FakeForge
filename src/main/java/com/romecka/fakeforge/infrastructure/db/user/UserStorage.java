package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.apikey.ApiKeyProvider;
import com.romecka.fakeforge.domain.limit.LimitProvider;
import com.romecka.fakeforge.domain.user.User;
import com.romecka.fakeforge.domain.user.UserParams;
import com.romecka.fakeforge.domain.user.Users;
import com.romecka.fakeforge.infrastructure.db.apikey.ApiKeyEntity;
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStorage implements Users {

    private final UserRepository userRepository;

    private final ApiKeyProvider apiKeyProvider;

    private final LimitProvider limitProvider;

    public User registerUser(UserParams userParams) {
        ApiKeyEntity apiKey = (ApiKeyEntity) apiKeyProvider.generateApiKey();
        LimitEntity limit = (LimitEntity) limitProvider.generateDefaultLimit();
        UserEntity user = new UserEntity()
                .name(userParams.name())
                .lastName(userParams.lastName())
                .emailAddress(userParams.emailAddress())
                .apiKey(apiKey)
                .limit(limit);
        return userRepository.save(user);
    }

    public @NotNull List<User> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(entity -> (User) entity)
                .toList();
    }

}
