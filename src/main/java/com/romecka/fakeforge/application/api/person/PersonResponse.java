package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.Person;

public record PersonResponse(String name,
                             String lastName,
                             String emailAddress,
                             String phoneNumber,
                             String personalId,
                             Gender gender,
                             String citizenship,
                             String bankAccountNumber,
                             DocumentResponse document,
                             AddressResponse address) {

    public static PersonResponse of(Person person) {
        return new PersonResponse(
                person.name(),
                person.lastName(),
                person.emailAddress(),
                person.phoneNumber(),
                person.personalId(),
                person.gender(),
                person.citizenship(),
                person.bankAccountNumber(),
                DocumentResponse.of(person.document()),
                AddressResponse.of(person.address())
        );
    }

}
