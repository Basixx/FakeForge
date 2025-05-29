package com.romecka.fakeforge.domain.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "documents")
public record Document(

        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(unique = true)
        @NotNull
        Long id,

        @NotNull
        DocumentType type,

        @Column(length = 10)
        @NotNull
        String number
) {
}
