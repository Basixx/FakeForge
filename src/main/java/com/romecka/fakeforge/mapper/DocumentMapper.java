package com.romecka.fakeforge.mapper;

import com.romecka.fakeforge.domain.entities.Document;
import com.romecka.fakeforge.domain.dto.DocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentMapper {

    public DocumentDto mapToDocumentDto(Document document) {
        return new DocumentDto(document.getType(), document.getNumber());
    }
}
