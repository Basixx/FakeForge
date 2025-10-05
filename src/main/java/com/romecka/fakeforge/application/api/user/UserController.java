package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("users")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public UsersResponse getUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return UsersResponse.of(userService.getUsers(page, size));
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public String getUserInfo(@RequestBody UserAuthenticationRequest authenticationRequest) {
        return userService.authenticateAndGenerateToken(authenticationRequest);
    }

}
