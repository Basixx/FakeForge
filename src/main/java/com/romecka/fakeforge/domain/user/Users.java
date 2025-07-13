package com.romecka.fakeforge.domain.user;

import java.util.List;

public interface Users {

    User registerUser(UserParams user);

    List<User> getUsers();

}
