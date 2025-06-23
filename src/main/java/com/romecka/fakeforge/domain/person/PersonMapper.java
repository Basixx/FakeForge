package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.infrastructure.db.person.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonMapper {

    private final DocumentMapper documentMapper;

    private final AddressMapper addressMapper;

    public PersonDto mapToPersonDto(PersonEntity personEntity) {
        return new PersonDto(
                personEntity.getName(),
                personEntity.getLastName(),
                personEntity.getEmailAddress(),
                personEntity.getPhoneNumber(),
                personEntity.getPersonalId(),
                personEntity.getGender(),
                personEntity.getCitizenship(),
                personEntity.getBankAccountNumber(),
                documentMapper.mapToDocumentDto(personEntity.getDocument()),
                addressMapper.mapToAddressDto(personEntity.getAddress())
        );
    }

    public List<PersonDto> mapToPersonDtoList(List<PersonEntity> personEntities) {
        return personEntities.stream()
                .map(this::mapToPersonDto)
                .toList();
    }

}
