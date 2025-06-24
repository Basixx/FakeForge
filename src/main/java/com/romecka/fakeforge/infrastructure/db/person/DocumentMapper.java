package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.DocumentDto;
import com.romecka.fakeforge.infrastructure.db.user.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DocumentMapper {

    public DocumentDto mapToDocumentDto(Document document) {
        return new DocumentDto(document.type(), document.number());
    }

}
