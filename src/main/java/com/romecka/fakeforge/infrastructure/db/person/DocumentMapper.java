package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DocumentMapper {

    public DocumentDto mapToDocumentDto(DocumentEntity document) {
        return new DocumentDto(document.type(), document.number());
    }

}
