package com.romecka.fakeforge.application.api.user;

import com.romecka.fakeforge.domain.user.DocumentType;

public record DocumentDto(DocumentType type, String number) {

}
