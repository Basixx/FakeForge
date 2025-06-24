package com.romecka.fakeforge.infrastructure.db.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "address_id")
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
