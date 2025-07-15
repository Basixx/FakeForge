package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.UserAuthenticationParams;

public record UserAuthenticationRequest(String emailAddress, String apiKey) implements UserAuthenticationParams {

}
