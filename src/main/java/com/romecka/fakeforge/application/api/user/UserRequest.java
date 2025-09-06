package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.UserParams;

public record UserRequest(String name,
                          String lastName,
                          String emailAddress,
                          String password) implements UserParams {

}
