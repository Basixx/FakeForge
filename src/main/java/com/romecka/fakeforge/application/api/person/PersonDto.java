package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.Person;

public record PersonDto(String name,
                        String lastName,
                        String emailAddress,
                        String phoneNumber,
                        String personalId,
                        Gender gender,
                        String bankAccountNumber,
                        String documentNumber,
                        AddressDto address) {

    public static PersonDto of(Person person) {
        return new PersonDto(
                person.name(),
                person.lastName(),
                person.emailAddress(),
                person.phoneNumber(),
                person.personalId(),
                person.gender(),
                person.bankAccountNumber(),
                person.documentNumber(),
                AddressDto.of(person)
        );
    }

}
