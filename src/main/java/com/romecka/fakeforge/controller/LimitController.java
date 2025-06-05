package com.romecka.fakeforge.controller;

import com.romecka.fakeforge.annotation.CurrentUser;
import com.romecka.fakeforge.domain.dto.LimitDto;
import com.romecka.fakeforge.domain.entities.User;
import com.romecka.fakeforge.facade.LimitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class LimitController {

    private final LimitFacade limitFacade;

    @GetMapping(value = "/users/limit")
    @ResponseStatus(OK)
    public LimitDto getUserLimit(@CurrentUser User user) {
        return limitFacade.getUserLimit(user.getId());
    }

}
