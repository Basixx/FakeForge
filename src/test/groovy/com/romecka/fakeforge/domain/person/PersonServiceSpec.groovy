package com.romecka.fakeforge.domain.person

import com.romecka.fakeforge.domain.communication.CommunicationService
import com.romecka.fakeforge.domain.limit.Limit
import com.romecka.fakeforge.domain.limit.Limits
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import spock.lang.Specification
import spock.lang.Subject

import static com.romecka.fakeforge.domain.person.Gender.FEMALE
import static com.romecka.fakeforge.domain.person.Gender.MALE

class PersonServiceSpec extends Specification {

    Person person = Stub {
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

    Person secondPerson = Stub {
        name() >> 'name2'
        lastName() >> 'lastName2'
        emailAddress() >> 'email2'
        phoneNumber() >> '666666666'
        personalId() >> '44444444444'
        gender() >> MALE
        bankAccountNumber() >> '55555555555'
        documentNumber() >> 'document2'
        street() >> 'street2'
        buildingNumber() >> 3
        apartmentNumber() >> 4
        postalCode() >> 'code2'
        city() >> 'city2'
    }

    People people = Mock()

    Limits limits = Mock()

    CommunicationService communicationService = Mock()

    @Subject
    PersonService personService = new PersonService(people,
        limits,
        communicationService)

    void 'should invoke get persons from user'() {
        given:
            Slice<Person> slice = new SliceImpl<>([person, secondPerson], PageRequest.of(0, 10), false)
        when:
            Slice<Person> result = personService.getPersonsFromUser(1, 0, 10)
        then:
            1 * people.getPersonsOfUser(1, 0, 10) >> slice
        and:
            with(result) {
                !hasNext()
                with(content) {
                    size() == 2
                    with(first) {
                        name() == person.name()
                        lastName() == person.lastName()
                        emailAddress() == person.emailAddress()
                        phoneNumber() == person.phoneNumber()
                        personalId() == person.personalId()
                        gender() == person.gender()
                        bankAccountNumber() == person.bankAccountNumber()
                        documentNumber() == person.documentNumber()
                        street() == person.street()
                        buildingNumber() == person.buildingNumber()
                        apartmentNumber() == person.apartmentNumber()
                        postalCode() == person.postalCode()
                        city() == person.city()
                    }
                    with(last) {
                        name() == secondPerson.name()
                        lastName() == secondPerson.lastName()
                        emailAddress() == secondPerson.emailAddress()
                        phoneNumber() == secondPerson.phoneNumber()
                        personalId() == secondPerson.personalId()
                        gender() == secondPerson.gender()
                        bankAccountNumber() == secondPerson.bankAccountNumber()
                        documentNumber() == secondPerson.documentNumber()
                        street() == secondPerson.street()
                        buildingNumber() == secondPerson.buildingNumber()
                        apartmentNumber() == secondPerson.apartmentNumber()
                        postalCode() == secondPerson.postalCode()
                        city() == secondPerson.city()
                    }
                }
            }
    }

    void 'should invoke create person with email'() {
        given:
            PersonParams personParams = Stub()
            Limit limit = Stub {
                availableLimit() >> 10
            }
            String email = 'user@email.com'
        when:
            Person result = personService.createPerson(1L, email, personParams)
        then:
            1 * limits.useLimit(1L)
            1 * limits.getLimit(1L) >> limit
            1 * communicationService.sendLimitReachingEmail(email, 10)
            1 * people.createPerson(1L, personParams) >> person
        and:
            with(result) {
                name() == person.name()
                lastName() == person.lastName()
                emailAddress() == person.emailAddress()
                phoneNumber() == person.phoneNumber()
                personalId() == person.personalId()
                gender() == person.gender()
                bankAccountNumber() == person.bankAccountNumber()
                documentNumber() == person.documentNumber()
                street() == person.street()
                buildingNumber() == person.buildingNumber()
                apartmentNumber() == person.apartmentNumber()
                postalCode() == person.postalCode()
                city() == person.city()
            }
    }

    void 'should invoke create person without email'() {
        given:
            PersonParams personParams = Stub()
            Limit limit = Stub {
                availableLimit() >> availableLimit
            }
            String email = 'user@email.com'
        when:
            Person result = personService.createPerson(1L, email, personParams)
        then:
            1 * limits.useLimit(1L)
            1 * limits.getLimit(1L) >> limit
            0 * communicationService.sendLimitReachingEmail(email, 10)
            1 * people.createPerson(1L, personParams) >> person
        and:
            with(result) {
                name() == person.name()
                lastName() == person.lastName()
                emailAddress() == person.emailAddress()
                phoneNumber() == person.phoneNumber()
                personalId() == person.personalId()
                gender() == person.gender()
                bankAccountNumber() == person.bankAccountNumber()
                documentNumber() == person.documentNumber()
                street() == person.street()
                buildingNumber() == person.buildingNumber()
                apartmentNumber() == person.apartmentNumber()
                postalCode() == person.postalCode()
                city() == person.city()
            }
        where:
            availableLimit << [1, 9, 11, 50]
    }

}
