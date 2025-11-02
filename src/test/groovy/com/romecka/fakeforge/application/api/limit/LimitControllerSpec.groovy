package com.romecka.fakeforge.application.api.limit

import com.romecka.fakeforge.application.api.ControllerIntegrationSpec
import com.romecka.fakeforge.application.api.generic.ErrorResponse
import com.romecka.fakeforge.application.api.generic.MessageResponse
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED

class LimitControllerSpec extends ControllerIntegrationSpec {

    int expectedDailyLimit = 777

    int expectedAvailableLimit = 321

    void 'should return limit for authenticated user'() {
        given:
            saveUser(new LimitEntity(dailyLimit: expectedDailyLimit, availableLimit: expectedAvailableLimit))
        when:
            LimitResponse result = performGetLimitRequest(USER_TOKEN)
                .statusCode(OK.value())
                .extract()
                .as(LimitResponse)
        then:
            with(result) {
                dailyLimit() == expectedDailyLimit
                availableLimit() == expectedAvailableLimit
            }
    }

    void 'should return unauthorized for wrong token'() {
        when:
            ErrorResponse result = performGetLimitRequest('random_token')
                .statusCode(UNAUTHORIZED.value())
                .extract()
                .as(ErrorResponse)
        then:
            result.error() == 'Authorization failed. Try logging in again.'
    }

    void 'should change user\'s limit'() {
        given:
            saveAdmin()
            long userId = saveUser().id()
            int newLimit = 500
        when:
            MessageResponse result = performEditUserLimitRequest(ADMIN_TOKEN, newLimit, userId)
                .statusCode(OK.value())
                .extract()
                .as(MessageResponse)
        then:
            result.message() == "Daily limit %s for user with ID %s will be refreshed after midnight".formatted(newLimit, userId)
    }

    void 'should return error when user not found'() {
        given:
            saveAdmin()
            int newLimit = 500
        expect:
            performEditUserLimitRequest(ADMIN_TOKEN, newLimit, 99L)
                .statusCode(NOT_FOUND.value())
    }

    private static ValidatableMockMvcResponse performGetLimitRequest(String token) {
        given()
            .header('Authorization', 'Bearer ' + token)
            .when()
            .get('/users/limit')
            .then()
    }

    private static ValidatableMockMvcResponse performEditUserLimitRequest(String token, int newLimit, long id) {
        given()
            .header('Authorization', 'Bearer ' + token)
            .queryParam('dailyLimit', newLimit)
            .when()
            .put('/users/' + id + '/limit')
            .then()
    }

}
