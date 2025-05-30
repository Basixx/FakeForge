package com.romecka.fakeforge.facade;

import com.romecka.fakeforge.domain.person.PersonDto;
import com.romecka.fakeforge.mapper.PersonMapper;
import com.romecka.fakeforge.service.PersonService;
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
