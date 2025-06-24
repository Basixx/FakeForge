package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Document;
import com.romecka.fakeforge.domain.person.DocumentType;

public record DocumentResponse(DocumentType type, String number) implements Document {

    public static DocumentResponse of(Document document) {
        return new DocumentResponse(document.type(), document.number());
    }

}
