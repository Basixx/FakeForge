package com.romecka.fakeforge.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final Users users;

    public User registerUser(UserParams user) {
        return users.registerUser(user);
    }

    public List<User> getUsers() {
        return users.getUsers();
    }

}
