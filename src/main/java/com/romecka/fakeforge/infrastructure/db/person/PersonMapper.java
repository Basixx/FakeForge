package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class PersonMapper {

    private final DocumentMapper documentMapper;

    private final AddressMapper addressMapper;

    public PersonDto mapToPersonDto(PersonEntity person) {
        return new PersonDto(
                person.name(),
                person.lastName(),
                person.emailAddress(),
                person.phoneNumber(),
                person.personalId(),
                person.gender(),
                person.citizenship(),
                person.bankAccountNumber(),
                documentMapper.mapToDocumentDto(person.document()),
                addressMapper.mapToAddressDto(person.address())
        );
    }

    public List<PersonDto> mapToPersonDtoList(List<PersonEntity> personEntities) {
        return personEntities.stream()
                .map(this::mapToPersonDto)
                .toList();
    }

}
