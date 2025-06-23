package com.romecka.fakeforge.domain.user;

public record AddressDto(String street,
                         int buildingNumber,
                         int apartmentNumber,
                         String postalCode,
                         String city) {

}
