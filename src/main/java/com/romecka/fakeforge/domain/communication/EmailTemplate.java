package com.romecka.fakeforge.domain.communication;

public interface EmailTemplate {

    Mail createRegistrationEmail(String to, String name);

    Mail createLimitReachingEmail(String to, int limit);

}
