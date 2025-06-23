package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonMapper {

    private final DocumentMapper documentMapper;

    private final AddressMapper addressMapper;

    public PersonDto mapToPersonDto(Person person) {
        return new PersonDto(
                person.getName(),
                person.getLastName(),
                person.getEmailAddress(),
                person.getPhoneNumber(),
                person.getPersonalId(),
                person.getGender(),
                person.getCitizenship(),
                person.getBankAccountNumber(),
                documentMapper.mapToDocumentDto(person.getDocument()),
                addressMapper.mapToAddressDto(person.getAddress())
        );
    }

    public List<PersonDto> mapToPersonDtoList(List<Person> personEntities) {
        return personEntities.stream()
                .map(this::mapToPersonDto)
                .toList();
    }

}
