package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.user.Gender;
import com.romecka.fakeforge.infrastructure.db.user.Address;
import com.romecka.fakeforge.infrastructure.db.user.Document;
import com.romecka.fakeforge.infrastructure.db.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "persons", indexes = {
        @Index(name = "idx_persons_user_id", columnList = "user_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonEntity {

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

    @NotNull
    Gender gender;

    @Column(length = 2)
    @NotNull
    String citizenship;

    @Column(length = 28)
    @NotNull
    String bankAccountNumber;

    @OneToOne
    @JoinColumn(name = "document_id")
    Document document;

    @OneToOne
    @JoinColumn(name = "address_id")
    Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
