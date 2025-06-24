package com.romecka.fakeforge.application.api.limit;

import com.romecka.fakeforge.application.config.CurrentUser;
import com.romecka.fakeforge.domain.limit.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class LimitController {

    private final LimitService limitService;

    @GetMapping(value = "/users/limit")
    @ResponseStatus(OK)
    public LimitResponse getUserLimit(@CurrentUser Long userId) {
        return LimitResponse.of(limitService.getUserLimit(userId));
    }

}
