package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.limit.LimitProvider;
import com.romecka.fakeforge.domain.user.DataEncoder;
import com.romecka.fakeforge.domain.user.User;
import com.romecka.fakeforge.domain.user.UserAlreadyExistsException;
import com.romecka.fakeforge.domain.user.UserNotFoundException;
import com.romecka.fakeforge.domain.user.UserParams;
import com.romecka.fakeforge.domain.user.Users;
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStorage implements Users {

    private final UserRepository userRepository;

    private final DataEncoder dataEncoder;

    private final LimitProvider limitProvider;

    public User registerUser(UserParams userParams) {
        if (userRepository.existsByEmailAddress(userParams.emailAddress())) {
            throw new UserAlreadyExistsException();
        } else {
            LimitEntity limit = (LimitEntity) limitProvider.generateDefaultLimit();
            UserEntity user = new UserEntity()
                    .name(userParams.name())
                    .lastName(userParams.lastName())
                    .emailAddress(userParams.emailAddress())
                    .password(dataEncoder.encode(userParams.password()))
                    .role("ROLE_USER")
                    .limit(limit);
            return userRepository.save(user);
        }
    }

    public @NotNull List<User> getUsers(int page, int size) {
        return userRepository.findAll(page, size)
                .stream()
                .map(entity -> (User) entity)
                .toList();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailAddress(email).orElseThrow(() -> new UserNotFoundException(email));
    }

}
