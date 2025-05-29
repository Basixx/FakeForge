package com.romecka.fakeforge.domain.apikeys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "api_keys")
public record ApiKey(

        @Id
        @GeneratedValue(strategy = IDENTITY)
        @Column(unique = true)
        @NotNull
        Long id,

        @Column(unique = true, length = 50)
        @NotNull
        String apiKey
) {
}
