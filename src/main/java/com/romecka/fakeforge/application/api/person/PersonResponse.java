package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.AddressDto;
import com.romecka.fakeforge.domain.person.DocumentDto;
import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.PersonDto;

public record PersonResponse(String name,
                             String lastName,
                             String emailAddress,
                             String phoneNumber,
                             String personalId,
                             Gender gender,
                             String citizenship,
                             String bankAccountNumber,
                             DocumentDto documentDto,
                             AddressDto addressDto) {

    public static PersonResponse fromPerson(PersonDto person) {
        return new PersonResponse(
                person.name(),
                person.lastName(),
                person.emailAddress(),
                person.phoneNumber(),
                person.personalId(),
                person.gender(),
                person.citizenship(),
                person.bankAccountNumber(),
                person.documentDto(),
                person.addressDto()
        );
    }

}
