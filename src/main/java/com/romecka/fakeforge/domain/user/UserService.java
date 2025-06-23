package com.romecka.fakeforge.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserCollector userCollector;

    public UserResponseDto registerUser(UserData userData) {
        return userCollector.registerUser(userData);
    }

}
