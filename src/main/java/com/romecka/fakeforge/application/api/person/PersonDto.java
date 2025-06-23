package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.application.api.user.AddressDto;
import com.romecka.fakeforge.application.api.user.DocumentDto;
import com.romecka.fakeforge.infrastructure.db.user.Gender;

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
