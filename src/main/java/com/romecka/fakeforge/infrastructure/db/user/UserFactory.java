package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.user.UserData;
import com.romecka.fakeforge.domain.user.UserProvider;
import com.romecka.fakeforge.domain.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFactory implements UserProvider {

    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserData userData) {
        User user = User.builder()
                .name(userData.name())
                .lastName(userData.lastName())
                .emailAddress(userData.emailAddress())
                .build();
        User savedUser = userRepository.save(user);
//        TODO ADD API KEY AND LIMIT
        return new UserResponseDto(
                savedUser.getName(),
                savedUser.getLastName(),
                savedUser.getEmailAddress()
        );
    }

}
