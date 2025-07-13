package com.romecka.fakeforge.infrastructure.db.person;

import com.romecka.fakeforge.domain.person.People;
import com.romecka.fakeforge.domain.person.Person;
import com.romecka.fakeforge.domain.person.PersonProvider;
import com.romecka.fakeforge.infrastructure.db.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonStorage implements People {

    private final PersonRepository personRepository;

    private final PersonProvider personProvider;

    private final UserRepository userRepository;

    @Override
    public List<Person> getPersonsOfUser(long userId, int page, int size) {
        return personRepository.findByUserId(userId, page, size)
                .stream()
                .map(entity -> (Person) entity)
                .toList();
    }

    @Override
    public Person createPerson(long userId) {
        PersonEntity person = (PersonEntity) personProvider.generateRandomPerson();
        person.user(userRepository.findById(userId).orElseThrow());
        return personRepository.save(person);
    }

}
