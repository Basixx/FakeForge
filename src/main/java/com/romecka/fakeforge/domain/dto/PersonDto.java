package com.romecka.fakeforge.domain.dto;

import com.romecka.fakeforge.domain.auxiliary.Gender;

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
