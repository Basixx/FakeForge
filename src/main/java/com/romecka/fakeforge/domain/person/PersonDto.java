package com.romecka.fakeforge.domain.person;

public record PersonDto(String name,
                        String lastName,
                        String emailAddress,
                        String phoneNumber,
                        String personalId,
                        Gender gender,
                        String citizenship,
                        String bankAccountNumber,
                        DocumentDto documentDto,
                        AddressDto addressDto) {

}
