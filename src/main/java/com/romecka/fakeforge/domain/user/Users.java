package com.romecka.fakeforge.domain.user;

import java.util.List;

public interface Users {

    User registerUser(UserParams user);

    List<User> getUsers(int page, int size);

    User getUserByEmail(String email);

}
