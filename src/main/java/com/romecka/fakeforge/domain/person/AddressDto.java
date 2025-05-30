package com.romecka.fakeforge.domain.person;

public record AddressDto(String street,
                         int buildingNumber,
                         int apartmentNumber,
                         String postalCode,
                         String city) {
}
