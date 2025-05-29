package com.romecka.fakeforge.domain.limit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "limits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Limit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true)
    @NotNull
    Long id;

    @Column(unique = true, length = 50)
    @NotNull
    String apiKey;

}
