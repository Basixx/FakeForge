package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.domain.user.AddressDto;
import com.romecka.fakeforge.domain.user.DocumentDto;
import com.romecka.fakeforge.domain.user.Gender;

public record PersonDto(String name,
                        String lastName,
                        String emailAddress,
                        String phoneNumber,
                        String personalId,
                        Gender gender,
                        String citizenship,
                        String bankAccountNumber,
                        DocumentDto documentDto,
                        AddressDto addressDto) implements Person {

}
