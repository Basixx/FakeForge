package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.Person;
import com.romecka.fakeforge.infrastructure.db.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "persons", indexes = {
        @Index(name = "idx_persons_user_id", columnList = "user_id")
})
public class PersonEntity implements Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "person_id")
    Long id;

    @Column(length = 30)
    @NotNull
    String name;

    @Column(length = 30)
    @NotNull
    String lastName;

    @Column(length = 50)
    @NotNull
    String emailAddress;

    @Column(length = 12)
    @NotNull
    String phoneNumber;

    @Column(length = 11)
    @NotNull
    String personalId;

    @Enumerated(EnumType.STRING)
    @NotNull
    Gender gender;

    @Column(length = 28)
    @NotNull
    String bankAccountNumber;

    @Column(length = 9)
    @NotNull
    String documentNumber;

    @Column(length = 50)
    @NotNull
    String street;

    @NotNull
    int buildingNumber;

    @NotNull
    int apartmentNumber;

    @Column(length = 6)
    @NotNull
    String postalCode;

    @Column(length = 50)
    @NotNull
    String city;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
