package com.romecka.fakeforge.application.api.user;

public record AddressDto(String street,
                         int buildingNumber,
                         int apartmentNumber,
                         String postalCode,
                         String city) {

}
