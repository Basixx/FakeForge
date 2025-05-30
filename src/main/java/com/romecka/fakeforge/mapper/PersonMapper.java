package com.romecka.fakeforge.mapper;

import com.romecka.fakeforge.domain.entities.Person;
import com.romecka.fakeforge.domain.dto.PersonDto;
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

    public List<PersonDto> mapToPersonDtoList(List<Person> persons) {
        return persons.stream()
                .map(this::mapToPersonDto)
                .toList();
    }

}
