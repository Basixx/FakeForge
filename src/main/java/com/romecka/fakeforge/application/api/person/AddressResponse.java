package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Address;

public record AddressResponse(String street,
                              int buildingNumber,
                              int apartmentNumber,
                              String postalCode,
                              String city) implements Address {

    public static AddressResponse of(Address address) {
        return new AddressResponse(
                address.street(),
                address.buildingNumber(),
                address.apartmentNumber(),
                address.postalCode(),
                address.city()
        );
    }

}
