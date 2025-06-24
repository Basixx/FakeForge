package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.User;

public record UserRequest(String name,
                          String lastName,
                          String emailAddress) implements User {

}
