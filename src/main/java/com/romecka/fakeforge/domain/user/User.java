package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.apikey.ApiKey;

public interface User {

    Long id();

    String name();

    String lastName();

    String emailAddress();

    ApiKey apiKey();

    String role();

}
