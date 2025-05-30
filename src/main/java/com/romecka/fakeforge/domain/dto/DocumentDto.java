package com.romecka.fakeforge.domain.dto;

import com.romecka.fakeforge.domain.auxiliary.DocumentType;

public record DocumentDto(DocumentType type, String number) {
}
