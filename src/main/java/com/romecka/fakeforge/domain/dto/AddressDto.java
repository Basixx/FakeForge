package com.romecka.fakeforge.domain.dto;

public record AddressDto(String street,
                         int buildingNumber,
                         int apartmentNumber,
                         String postalCode,
                         String city) {
}
