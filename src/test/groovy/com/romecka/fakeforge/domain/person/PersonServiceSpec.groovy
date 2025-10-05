package com.romecka.fakeforge.domain.person

import com.romecka.fakeforge.domain.limit.Limits
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

    @Subject
    PersonService personService = new PersonService(people, limits)

    void 'should invoke get persons from user'() {
        when:
            List<Person> result = personService.getPersonsFromUser(1, 0, 10)
        then:
            1 * people.getPersonsOfUser(1, 0, 10) >> [person, secondPerson]
        and:
            with(result) {
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

    void 'should invoke create person'() {
        given:
            PersonParams personParams = Stub()
        when:
            Person result = personService.createPerson(1L, personParams)
        then:
            1 * limits.useLimit(1L)
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
}
