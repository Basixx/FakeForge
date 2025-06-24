package com.romecka.fakeforge.infrastructure.db.limit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.romecka.fakeforge.domain.limit.LimitEntity;
import com.romecka.fakeforge.infrastructure.db.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "limits")
public class Limit implements LimitEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "limit_id")
    private Long id;

    @NotNull
    @Column(name = "daily_limit")
    private int dailyLimit = 100;

    @NotNull
    @Column(name = "available_limit")
    private int availableLimit = 100;

    @OneToOne(mappedBy = "limit")
    @JsonBackReference
    private User user;

}
