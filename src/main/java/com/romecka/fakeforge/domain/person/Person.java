package com.romecka.fakeforge.domain.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "persons")
public record Person(

        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(unique = true)
        @NotNull
        Long id,

        @Column(length = 30)
        @NotNull
        String name,

        @Column(length = 30)
        @NotNull
        String lastName,

        @Column(length = 50)
        @NotNull
        String emailAddress,

        @Column(length = 12)
        @NotNull
        String phoneNumber,

        @Column(length = 11)
        @NotNull
        String personalId,

        @NotNull
        Gender gender,

        @Column(length = 2)
        @NotNull
        String citizenship,

        @Column(length = 28)
        @NotNull
        String bankAccountNumber) {
}
