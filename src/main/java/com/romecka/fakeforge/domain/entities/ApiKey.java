package com.romecka.fakeforge.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "api_keys")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApiKey {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "api_key_id")
    private Long id;

    @Column(unique = true, length = 150)
    @NotNull
    private String apiKey;

}
