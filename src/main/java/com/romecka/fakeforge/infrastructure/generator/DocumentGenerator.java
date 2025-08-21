package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.person.DocumentType;
import com.romecka.fakeforge.infrastructure.db.person.DocumentEntity;

public class DocumentGenerator {

    public DocumentEntity document() {
        return new DocumentEntity()
                .type(DocumentType.ID_CARD)
                .number("1234569");
    }

    private static String documentNumber() {
        return "1234569";
    }

}
