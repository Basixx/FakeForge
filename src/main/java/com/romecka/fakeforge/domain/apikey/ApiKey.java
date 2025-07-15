package com.romecka.fakeforge.domain.apikey;

import com.romecka.fakeforge.domain.user.User;

public interface ApiKey {

    User user();

    String apiKey();

}
