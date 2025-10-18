package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.PersonParams;
import com.romecka.fakeforge.domain.person.PersonProvider;
import com.romecka.fakeforge.infrastructure.db.person.PersonEntity;
import lombok.RequiredArgsConstructor;
import net.datafaker.providers.base.Address;
import org.springframework.stereotype.Service;

import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.address;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.cellPhoneNumber;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.emailAddress;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.firstName;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.generateBankAccountNumber;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.generateDocumentNumber;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.lastName;
import static com.romecka.fakeforge.infrastructure.generator.PersonalDataGenerator.personalId;
import static com.romecka.fakeforge.utils.RandomUtils.randomEnum;
import static com.romecka.fakeforge.utils.RandomUtils.randomInt;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
@RequiredArgsConstructor
public class PersonGenerator implements PersonProvider {

    public PersonEntity generateRandomPerson(PersonParams personParams) {
        Gender gender = defaultIfNull(personParams.gender(), randomEnum(Gender.class));
        String firstName = firstName(gender);
        String lastName = lastName(gender);
        Address address = address();
        int age = defaultIfNull(personParams.age(), randomInt(18, 100));
        return new PersonEntity()
                .name(firstName)
                .lastName(lastName)
                .emailAddress(emailAddress(firstName, lastName))
                .phoneNumber(cellPhoneNumber())
                .personalId(personalId(gender, age))
                .gender(gender)
                .bankAccountNumber(generateBankAccountNumber())
                .street(address.streetName())
                .buildingNumber(randomInt(1, 100))
                .apartmentNumber(randomInt(1, 100))
                .postalCode(address.zipCode())
                .city(address.city())
                .documentNumber(generateDocumentNumber());
    }

}
