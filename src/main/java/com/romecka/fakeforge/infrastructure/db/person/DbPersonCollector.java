package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.PersonCollector;
import com.romecka.fakeforge.domain.person.PersonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbPersonCollector implements PersonCollector {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Override
    public List<PersonDto> getPersonsOfUser(Long userId, int page, int size) {
        return personMapper.mapToPersonDtoList(
                personRepository.findByUserId(userId, page, size)
        );
    }

}
