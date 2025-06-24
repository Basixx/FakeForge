package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.DocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "document_id")
    Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    DocumentType type;

    @Column(length = 10)
    @NotNull
    String number;

}
