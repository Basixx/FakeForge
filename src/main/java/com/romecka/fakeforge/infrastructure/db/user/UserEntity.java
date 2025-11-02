package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.user.User;
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity;
import com.romecka.fakeforge.infrastructure.db.person.PersonEntity;
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

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements User {

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

    @Column(unique = true, length = 50)
    @NotNull
    private String password;

    @Column(length = 20, name = "user_role")
    @NotNull
    private String role;

    @OneToOne(cascade = ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "limit_id")
    private LimitEntity limit;

    @OneToMany(
            cascade = REMOVE,
            orphanRemoval = true,
            targetEntity = PersonEntity.class,
            mappedBy = "user"
    )
    private List<PersonEntity> personEntities;

}
