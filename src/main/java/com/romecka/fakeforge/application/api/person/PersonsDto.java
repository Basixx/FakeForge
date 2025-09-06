package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Person;

import java.util.List;

public record PersonsDto(List<PersonDto> persons) {

    public static PersonsDto of(List<Person> persons) {
        List<PersonDto> personRespons = persons
                .stream()
                .map(PersonDto::of)
                .toList();
        return new PersonsDto(personRespons);
    }

}
