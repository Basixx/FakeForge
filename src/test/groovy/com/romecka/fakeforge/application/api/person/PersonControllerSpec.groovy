package com.romecka.fakeforge.application.api.person

import com.romecka.fakeforge.application.api.ControllerIntegrationSpec
import com.romecka.fakeforge.application.api.generic.ErrorResponse
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity
import com.romecka.fakeforge.infrastructure.db.person.PersonEntity
import com.romecka.fakeforge.infrastructure.db.user.UserEntity
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse

import static com.romecka.fakeforge.domain.person.Gender.MALE
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS

class PersonControllerSpec extends ControllerIntegrationSpec {

    void 'should return list of persons for authenticated user'() {
        given:
            UserEntity user = saveUser()
            PersonEntity personEntity = new PersonEntity(name: 'Olaf',
                lastName: 'Kozak',
                emailAddress: 'email',
                phoneNumber: '111222999',
                personalId: '11111111111',
                gender: MALE,
                bankAccountNumber: '75835100031902636471223070',
                documentNumber: 'CHI930854',
                street: 'Mazowiecka',
                buildingNumber: 2,
                apartmentNumber: 12,
                postalCode: '02-123',
                city: 'Warszawa',
                user: user)
            personRepository.save(personEntity)
            PersonEntity secondPersonEntity = new PersonEntity(
                name: 'Mariola',
                lastName: 'Nowak',
                emailAddress: 'email2',
                phoneNumber: '121222999',
                personalId: '11211111111',
                gender: MALE,
                bankAccountNumber: '91871710227076559352087306',
                documentNumber: 'POU154571',
                street: 'Wojewódzka',
                buildingNumber: 1,
                apartmentNumber: 22,
                postalCode: '02-321',
                city: 'Kraków',
                user: user)
            personRepository.save(secondPersonEntity)
        when:
            PersonsDto result = performGetPersonsRequest(USER_TOKEN)
                .statusCode(OK.value())
                .extract()
                .as(PersonsDto)
        then:
            with(result.persons()) {
                size() == 2
                with(first) {
                    name() == personEntity.name()
                    lastName() == personEntity.lastName()
                    emailAddress() == personEntity.emailAddress()
                    phoneNumber() == personEntity.phoneNumber()
                    personalId() == personEntity.personalId()
                    gender() == personEntity.gender()
                    bankAccountNumber() == personEntity.bankAccountNumber()
                    documentNumber() == personEntity.documentNumber()
                    address().street() == personEntity.street()
                    address().buildingNumber() == personEntity.buildingNumber()
                    address().apartmentNumber() == personEntity.apartmentNumber()
                    address().postalCode() == personEntity.postalCode()
                    address().city() == personEntity.city()
                }
                with(last) {
                    name() == secondPersonEntity.name()
                    lastName() == secondPersonEntity.lastName()
                    emailAddress() == secondPersonEntity.emailAddress()
                    phoneNumber() == secondPersonEntity.phoneNumber()
                    personalId() == secondPersonEntity.personalId()
                    gender() == secondPersonEntity.gender()
                    bankAccountNumber() == secondPersonEntity.bankAccountNumber()
                    documentNumber() == secondPersonEntity.documentNumber()
                    address().street() == secondPersonEntity.street()
                    address().buildingNumber() == secondPersonEntity.buildingNumber()
                    address().apartmentNumber() == secondPersonEntity.apartmentNumber()
                    address().postalCode() == secondPersonEntity.postalCode()
                    address().city() == secondPersonEntity.city()
                }
            }
    }

    void 'should return empty list'() {
        given:
            saveUser()
        when:
            PersonsDto result = performGetPersonsRequest(USER_TOKEN)
                .statusCode(OK.value())
                .extract()
                .as(PersonsDto)
        then:
            result.persons().empty
    }

    void 'should create person'() {
        given:
            saveUser()
        when:
            PersonDto result = performCreatePersonRequest(USER_TOKEN)
                .statusCode(CREATED.value())
                .extract()
                .as(PersonDto)
        then:
            with(result) {
                name() != null
                lastName() != null
                emailAddress() != null
                phoneNumber() != null
                personalId() != null
                gender() != null
                bankAccountNumber() != null
                documentNumber() != null
                address() != null
                address().street() != null
                address().buildingNumber() > 0
                address().apartmentNumber() > 0
                address().postalCode() != null
                address().city() != null
            }
    }

    void 'should not create person when limit is exceeded'() {
        given:
            saveUser(new LimitEntity(dailyLimit: 100, availableLimit: 0))
        when:
            ErrorResponse result = performCreatePersonRequest(USER_TOKEN)
                .statusCode(TOO_MANY_REQUESTS.value())
                .extract()
                .as(ErrorResponse)
        then:
            result.error() == 'Daily limit exceeded, please try again tomorrow or contact support'
    }

    private static ValidatableMockMvcResponse performGetPersonsRequest(String token) {
        given()
            .header('Authorization', 'Bearer ' + token)
            .when()
            .get('/users/persons')
            .then()
    }

    private static ValidatableMockMvcResponse performCreatePersonRequest(String token) {
        given()
            .header('Authorization', 'Bearer ' + token)
            .when()
            .post('/users/persons')
            .then()
    }

}
