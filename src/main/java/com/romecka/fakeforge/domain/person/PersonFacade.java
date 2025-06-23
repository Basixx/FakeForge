package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.application.api.person.PersonDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonFacade {

    private final PersonService personService;

    private final PersonMapper personMapper;

    public List<PersonDto> getPersonsByUserId(Long id,
                                              int page,
                                              int size) {
        return personMapper.mapToPersonDtoList(
                personService.getPersonsFromUser(
                        id,
                        page,
                        size
                )
        );

    }

}
