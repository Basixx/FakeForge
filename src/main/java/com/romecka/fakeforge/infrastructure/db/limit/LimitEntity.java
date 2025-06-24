package com.romecka.fakeforge.infrastructure.db.limit;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.infrastructure.db.user.UserEntity;
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
public class LimitEntity implements Limit {

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
    private UserEntity userEntity;

}
