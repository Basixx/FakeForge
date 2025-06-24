package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.infrastructure.db.apikey.ApiKey;
import com.romecka.fakeforge.infrastructure.db.limit.Limit;
import com.romecka.fakeforge.infrastructure.db.person.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "user_id")
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

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "api_key_id")
    private ApiKey apiKey;

    @OneToOne(cascade = PERSIST)
    @JoinColumn(name = "limit_id")
    private Limit limit;

    @OneToMany(
            cascade = REMOVE,
            orphanRemoval = true,
            targetEntity = Person.class,
            mappedBy = "user"
    )
    private List<Person> personEntities;

}
