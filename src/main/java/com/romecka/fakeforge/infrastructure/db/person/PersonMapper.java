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

    public PersonDto mapToPersonDto(PersonEntity personEntity) {
        return new PersonDto(
                personEntity.name(),
                personEntity.lastName(),
                personEntity.emailAddress(),
                personEntity.phoneNumber(),
                personEntity.personalId(),
                personEntity.gender(),
                personEntity.citizenship(),
                personEntity.bankAccountNumber(),
                documentMapper.mapToDocumentDto(personEntity.document()),
                addressMapper.mapToAddressDto(personEntity.address())
        );
    }

    public List<PersonDto> mapToPersonDtoList(List<PersonEntity> personEntityEntities) {
        return personEntityEntities.stream()
                .map(this::mapToPersonDto)
                .toList();
    }

}
