package com.romecka.fakeforge.domain.communication;

public record Mail(String mailTo,
                   String subject,
                   String message) {

}
