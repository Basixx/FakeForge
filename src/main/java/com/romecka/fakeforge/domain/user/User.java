package com.romecka.fakeforge.domain.user;

import com.romecka.fakeforge.domain.apikeys.ApiKey;
import com.romecka.fakeforge.domain.limit.Limit;
import com.romecka.fakeforge.domain.person.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "user_id")
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

    @OneToMany(
            cascade = REMOVE,
            orphanRemoval = true,
            targetEntity = Person.class,
            mappedBy = "user"
    )
    private List<Person> persons;

}
