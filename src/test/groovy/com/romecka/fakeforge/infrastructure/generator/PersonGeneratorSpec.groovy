package com.romecka.fakeforge.infrastructure.generator

import com.romecka.fakeforge.domain.person.PersonParams
import com.romecka.fakeforge.infrastructure.db.person.PersonEntity
import spock.lang.Specification
import spock.lang.Subject

import static com.romecka.fakeforge.domain.person.Gender.FEMALE
import static com.romecka.fakeforge.domain.person.Gender.MALE

class PersonGeneratorSpec extends Specification {

    @Subject
    PersonGenerator personGenerator = new PersonGenerator()

    void 'should generate new FEMALE'() {
        given:
            PersonParams personParams = Stub {
                gender() >> FEMALE
                age() >> 35
            }
        when:
            PersonEntity result = personGenerator.generateRandomPerson(personParams)
        then:
            with(result) {
                id() == null
                name() != null
                lastName() != null
                emailAddress() != null
                phoneNumber() != null
                personalId() != null
                gender() == FEMALE
                bankAccountNumber() != null
                street() != null
                buildingNumber() > 0
                apartmentNumber() > 0
                postalCode() != null
                city() != null
                documentNumber() != null
            }
    }

    void 'should generate new MALE'() {
        given:
            PersonParams personParams = Stub {
                gender() >> MALE
                age() >> 45
            }
        when:
            PersonEntity result = personGenerator.generateRandomPerson(personParams)
        then:
            with(result) {
                id() == null
                name() != null
                lastName() != null
                emailAddress() != null
                phoneNumber() != null
                personalId() != null
                gender() == MALE
                bankAccountNumber() != null
                street() != null
                buildingNumber() > 0
                apartmentNumber() > 0
                postalCode() != null
                city() != null
                documentNumber() != null
            }
    }

}
