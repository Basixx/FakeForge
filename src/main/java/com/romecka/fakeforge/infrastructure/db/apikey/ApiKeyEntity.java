package com.romecka.fakeforge.infrastructure.db.apikey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romecka.fakeforge.domain.apikey.ApiKey;
import com.romecka.fakeforge.infrastructure.db.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "api_keys")
public class ApiKeyEntity implements ApiKey {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "api_key_id")
    private Long id;

    @Column(name = "api_key", unique = true, length = 150)
    @NotNull
    private String apiKey;

    @Column(name = "creation_date_time")
    @NotNull
    private LocalDateTime creationDateTime;

    @OneToOne(mappedBy = "apiKey")
    @JsonIgnore
    private UserEntity userEntity;

}
