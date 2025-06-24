package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.person.DocumentType;
import com.romecka.fakeforge.domain.person.Gender;
import com.romecka.fakeforge.domain.person.PersonProvider;
import com.romecka.fakeforge.infrastructure.db.person.PersonEntity;
import com.romecka.fakeforge.infrastructure.db.user.Address;
import com.romecka.fakeforge.infrastructure.db.user.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonGenerator implements PersonProvider {

    public PersonEntity generateRandomPerson() {
        return new PersonEntity()
                .name("aaa")
                .lastName("bbb")
                .emailAddress("<EMAIL>")
                .phoneNumber("1234567890")
                .personalId("98022206177")
                .gender(Gender.FEMALE)
                .citizenship("PL")
                .bankAccountNumber("1232323232323")
                .address(new Address()
                        .street("street")
                        .buildingNumber(1)
                        .apartmentNumber(12)
                        .postalCode("12-345")
                        .city("city"))
                .document(new Document()
                        .type(DocumentType.ID_CARD)
                        .number("1234569"));
    }

}
