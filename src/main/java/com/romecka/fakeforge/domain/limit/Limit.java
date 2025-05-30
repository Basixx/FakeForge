package com.romecka.fakeforge.domain.limit;

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
@Table(name = "limits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Limit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "limit_id")
    private Long id;

    @NotNull
    private int dailyLimit;

    @NotNull
    private int availableLimit;

}
