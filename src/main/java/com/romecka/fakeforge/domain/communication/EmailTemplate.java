package com.romecka.fakeforge.domain.communication;

public interface EmailTemplate {

    Mail createRegistrationEmail(String to, String name);

}
