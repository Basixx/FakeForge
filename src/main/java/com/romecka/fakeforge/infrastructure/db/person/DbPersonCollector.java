package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.PersonCollector;
import com.romecka.fakeforge.domain.person.PersonDto;
import com.romecka.fakeforge.domain.person.PersonProvider;
import com.romecka.fakeforge.infrastructure.db.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbPersonCollector implements PersonCollector {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final PersonProvider personProvider;

    private final UserRepository userRepository;

    @Override
    public List<PersonDto> getPersonsOfUser(Long userId, int page, int size) {
        return personMapper.mapToPersonDtoList(
                personRepository.findByUserId(userId, page, size)
        );
    }

    @Override
    public PersonDto createPerson(Long userId) {
        Person person = (Person) personProvider.generateRandomPerson();
        person.user(userRepository.findById(userId).orElseThrow());

        return personMapper.mapToPersonDto(personRepository.save(person));
    }

}
