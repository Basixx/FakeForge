package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.infrastructure.db.person.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public List<PersonDto> getPersonsFromUser(Long userId,
                                              int page,
                                              int size) {
        return personMapper.mapToPersonDtoList(
                personRepository.findByUserId(
                        userId,
                        page,
                        size
                )
        );
    }

}
