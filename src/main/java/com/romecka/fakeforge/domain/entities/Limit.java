package com.romecka.fakeforge.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @Column(name = "daily_limit")
    private int dailyLimit;

    @NotNull
    @Column(name = "available_limit")
    private int availableLimit;

    @OneToOne(mappedBy = "limit")
    @JsonBackReference
    private User user;

}
