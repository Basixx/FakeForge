package com.romecka.fakeforge.infrastructure.db.person

import com.romecka.fakeforge.domain.person.Person
import com.romecka.fakeforge.domain.person.PersonParams
import com.romecka.fakeforge.domain.person.PersonProvider
import com.romecka.fakeforge.domain.user.UserNotFoundException
import com.romecka.fakeforge.infrastructure.db.user.UserEntity
import com.romecka.fakeforge.infrastructure.db.user.UserRepository
import spock.lang.Specification
import spock.lang.Subject

import static com.romecka.fakeforge.domain.person.Gender.FEMALE

class PersonStorageSpec extends Specification {

    private static long userId = 1

    private PersonEntity personEntity = Stub {
        name() >> 'name'
        lastName() >> 'lastName'
        emailAddress() >> 'email'
        phoneNumber() >> '111111111'
        personalId() >> '22222222222'
        gender() >> FEMALE
        bankAccountNumber() >> '33333333333'
        documentNumber() >> 'document'
        street() >> 'street'
        buildingNumber() >> 1
        apartmentNumber() >> 2
        postalCode() >> 'code'
        city() >> 'city'
    }

    PersonRepository personRepository = Mock()

    PersonProvider personProvider = Mock()

    UserRepository userRepository = Mock()

    @Subject
    PersonStorage personStorage = new PersonStorage(
        personRepository,
        personProvider,
        userRepository
    )

    void 'should invoke find by id'() {
        when:
            List<Person> result = personStorage.getPersonsOfUser(userId, 0, 1)
        then:
            1 * personRepository.findByUserId(userId, 0, 1) >> [personEntity]
        and:
            with(result) {
                size() == 1
                with(first) {
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
    }

    void 'should invoke create person'() {
        given:
            PersonParams personParams = Stub {
                gender() >> FEMALE
            }
            UserEntity user = Stub()
        when:
            Person result = personStorage.createPerson(userId, personParams)
        then:
            1 * personProvider.generateRandomPerson(personParams) >> personEntity
            1 * userRepository.findById(userId) >> Optional.of(user)
            1 * personRepository.save(personEntity) >> personEntity
        and:
            result.gender() == personParams.gender()
    }

    void 'should throw an exception when user not found'() {
        given:
            PersonParams personParams = Stub()
        when:
            personStorage.createPerson(userId, personParams)
        then:
            1 * personProvider.generateRandomPerson(personParams) >> personEntity
            1 * userRepository.findById(userId) >> Optional.empty()
            0 * personRepository.save(personEntity)
        and:
            thrown(UserNotFoundException)
    }

}
