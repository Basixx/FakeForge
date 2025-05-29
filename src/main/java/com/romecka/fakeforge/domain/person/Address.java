package com.romecka.fakeforge.domain.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true)
    @NotNull
    Long id;

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

    @Column(length = 20)
    @NotNull
    String city;

}
