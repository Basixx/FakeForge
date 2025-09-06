package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.PersonProvider;
import com.romecka.fakeforge.infrastructure.db.person.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.romecka.fakeforge.infrastructure.generator.AddressGenerator.address;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.bankAccountNumber;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.cellPhoneNumber;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.documentNumber;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.emailAddress;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.firstName;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.lastName;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.personalId;
import static com.romecka.fakeforge.utils.RandomUtils.randomEnum;

@Service
@RequiredArgsConstructor
public class PersonGenerator implements PersonProvider {

    public PersonEntity generateRandomPerson() {
        Gender gender = randomEnum(Gender.class);
        String firstName = firstName(gender);
        String lastName = lastName(gender);

        return new PersonEntity()
                .name(firstName)
                .lastName(lastName)
                .emailAddress(emailAddress(firstName, lastName))
                .phoneNumber(cellPhoneNumber())
                .personalId(personalId(gender))
                .gender(gender)
                .citizenship("PL")
                .bankAccountNumber(bankAccountNumber())
                .address(address())
                .documentNumber(documentNumber());
    }

}
