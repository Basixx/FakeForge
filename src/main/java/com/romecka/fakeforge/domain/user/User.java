package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.apikeys.ApiKey;
import com.romecka.fakeforge.domain.limit.Limit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true)
    @NotNull
    private Long id;

    @Column(length = 30)
    @NotNull
    private String name;

    @Column(length = 30)
    @NotNull
    private String lastName;

    @Column(unique = true, length = 50)
    @NotNull
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    @OneToOne
    @JoinColumn(name = "limit_id")
    private Limit limit;

}
