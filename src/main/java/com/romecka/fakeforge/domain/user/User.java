package com.romecka.fakeforge.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "users")
public record User(

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

        @NotNull
        @Column(unique = true, length = 50)
        String apiKey) {
}
