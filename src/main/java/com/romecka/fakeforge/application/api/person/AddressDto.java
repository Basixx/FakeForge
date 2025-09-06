package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Person;

public record AddressDto(String street,
                         int buildingNumber,
                         int apartmentNumber,
                         String postalCode,
                         String city) {

    public static AddressDto of(Person person) {
        return new AddressDto(
                person.street(),
                person.buildingNumber(),
                person.apartmentNumber(),
                person.postalCode(),
                person.city()
        );
    }

}
