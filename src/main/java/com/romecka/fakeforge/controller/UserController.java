package com.romecka.fakeforge.controller;

import com.romecka.fakeforge.domain.dto.UserRequestDto;
import com.romecka.fakeforge.domain.dto.UserResponseDto;
import com.romecka.fakeforge.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping(value = "users/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto registerClient(@RequestBody UserRequestDto userRequestDto) {
        return userFacade.registerUser(userRequestDto);
    }

}
