package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerClient(@RequestBody UserRequest userRequest) {
        return UserResponse.of(userService.registerUser(userRequest));
    }

}
