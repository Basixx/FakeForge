package com.romecka.fakeforge.infrastructure.api.email;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmailVerificationDto(@JsonProperty("smtpCheck") boolean emailExists) {

}
