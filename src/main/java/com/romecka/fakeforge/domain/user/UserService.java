package com.romecka.fakeforge.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserProvider userProvider;

    public UserResponseDto registerUser(UserData userData) {
        return userProvider.saveUser(userData);
    }

}
