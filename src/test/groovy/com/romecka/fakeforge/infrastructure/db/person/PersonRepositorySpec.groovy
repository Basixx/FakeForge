package com.romecka.fakeforge.infrastructure.db.person

import com.romecka.fakeforge.domain.person.Gender
import com.romecka.fakeforge.infrastructure.db.user.UserEntity
import com.romecka.fakeforge.infrastructure.db.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
@Transactional
class PersonRepositorySpec extends Specification {

    @Subject
    @Autowired
    PersonRepository personRepository

    @Autowired
    UserRepository userRepository

    void 'should save person entity'() {
        given:
            PersonEntity personEntity = personEntity()
        expect:
            with(personRepository.save(personEntity)) {
                id() != null
                name() == personEntity.name()
                lastName() == personEntity.lastName()
                emailAddress() == personEntity.emailAddress()
                phoneNumber() == personEntity.phoneNumber()
                personalId() == personEntity.personalId()
                gender() == personEntity.gender()
                bankAccountNumber() == personEntity.bankAccountNumber()
                documentNumber() == personEntity.documentNumber()
                street() == personEntity.street()
                buildingNumber() == personEntity.buildingNumber()
                apartmentNumber() == personEntity.apartmentNumber()
                postalCode() == personEntity.postalCode()
                city() == personEntity.city()
            }
    }

    void 'should find person entity by ID'() {
        given:
            PersonEntity personEntity = saveEntity()
        when:
            Optional<PersonEntity> result = personRepository.findById(personEntity.id())
        then:
            with(result.get()) {
                id() == personEntity.id()
                name() == personEntity.name()
                lastName() == personEntity.lastName()
                emailAddress() == personEntity.emailAddress()
                phoneNumber() == personEntity.phoneNumber()
                personalId() == personEntity.personalId()
                gender() == personEntity.gender()
            }
    }

    void 'should return empty optional if entity not found by ID'() {
        expect:
            personRepository.findById(99L) == Optional.empty()
    }

    void 'should find person entities by user ID'() {
        given:
            UserEntity user = saveUser()
            PersonEntity firstPerson = saveEntityWithUser(user)
            PersonEntity secondPerson = saveEntityWithUser(user)
        when:
            List<PersonEntity> result = personRepository.findByUserId(user.id(), 0, 10)
        then:
            with(result) {
                size() == 2
                with(first()) {
                    id() == firstPerson.id()
                    name() == firstPerson.name()
                    lastName() == firstPerson.lastName()
                    user.id() == user.id()
                }
                with(last()) {
                    id() == secondPerson.id()
                    name() == secondPerson.name()
                    lastName() == secondPerson.lastName()
                    user.id() == user.id()
                }
            }
    }

    void 'should return empty list when no persons found for user ID'() {
        expect:
            personRepository.findByUserId(99L, 0, 10).isEmpty()
    }

    private static PersonEntity personEntity() {
        new PersonEntity(
            name: 'John',
            lastName: 'Doe',
            emailAddress: 'john.doe@example.com',
            phoneNumber: '123456789',
            personalId: '12345678901',
            gender: Gender.MALE,
            bankAccountNumber: '12345678901234567890123456',
            documentNumber: 'ABC123456',
            street: 'Main Street',
            buildingNumber: 10,
            apartmentNumber: 5,
            postalCode: '12-345',
            city: 'Warsaw'
        )
    }

    private UserEntity saveUser() {
        userRepository.save(new UserEntity(
            name: 'Test',
            lastName: 'User',
            emailAddress: 'test@example.com',
            password: 'password',
            role: 'USER'
        ))
    }

    private PersonEntity saveEntity() {
        personRepository.save(personEntity())
    }

    private PersonEntity saveEntityWithUser(UserEntity user) {
        PersonEntity person = personEntity()
        person.user(user)
        personRepository.save(person)
    }

}
