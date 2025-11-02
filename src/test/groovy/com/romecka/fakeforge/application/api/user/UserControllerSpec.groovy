package com.romecka.fakeforge.application.api.user

import com.romecka.fakeforge.application.api.ControllerIntegrationSpec
import com.romecka.fakeforge.application.api.generic.ErrorResponse
import com.romecka.fakeforge.infrastructure.db.user.UserEntity
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given
import static org.mockito.Mockito.when
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED
import static org.springframework.http.MediaType.APPLICATION_JSON

class UserControllerSpec extends ControllerIntegrationSpec {

    private UserRequest userRequest = new UserRequest(
        'name',
        'lastName',
        USER_EMAIL,
        'password'
    )

    void 'should register user'() {
        when:
            UserResponse result = performRegisterUserRequest(userRequest)
                .statusCode(CREATED.value())
                .extract()
                .as(UserResponse)
        then:
            with(result) {
                name() == userRequest.name()
                lastName() == userRequest.lastName()
                emailAddress() == userRequest.emailAddress()
            }
    }

    void 'should return error when user already exists'() {
        given:
            saveUser()
        when:
            ErrorResponse result = performRegisterUserRequest(userRequest)
                .statusCode(BAD_REQUEST.value())
                .extract()
                .as(ErrorResponse)
        then:
            result.error() == 'User with email address already exists'
    }

    void 'should return list of users'() {
        given:
            UserEntity user = saveUser()
            UserEntity admin = saveAdmin()
        when:
            UsersResponse result = performGetUsersRequest(ADMIN_TOKEN)
                .statusCode(OK.value())
                .extract()
                .as(UsersResponse)
        then:
            with(result.users()) {
                size() == 2
                with(first) {
                    name() == user.name()
                    lastName() == user.lastName()
                    emailAddress() == user.emailAddress()
                    role() == user.role()
                }
                with(last) {
                    name() == admin.name()
                    lastName() == admin.lastName()
                    emailAddress() == admin.emailAddress()
                    role() == admin.role()
                }
            }
    }

    void 'should not return users for unauthorized user'() {
        when:
            ErrorResponse result = performGetUsersRequest('abc')
                .statusCode(401)
                .extract()
                .as(ErrorResponse)
        then:
            result.error() == 'Authorization failed. Try logging in again.'
    }

    void 'should login user and return token'() {
        given:
            saveUser()
            when(jwtService.generateToken(USER_EMAIL)).thenReturn('test-jwt-token')
        when:
            String token = performLoginUsersRequest(new UserAuthenticationRequest(USER_EMAIL, 'pass'))
                .statusCode(OK.value())
                .extract()
                .asString()
        then:
            token == 'test-jwt-token'
    }

    void 'should not login non existing user'() {
        when:
            ErrorResponse result = performLoginUsersRequest(new UserAuthenticationRequest(USER_EMAIL, 'password'))
                .statusCode(UNAUTHORIZED.value())
                .extract()
                .as(ErrorResponse)
        then:
            result.error() == 'Wrong email address or password'
    }

    void 'should not login user with wrong password'() {
        given:
            performRegisterUserRequest(userRequest)
                .statusCode(CREATED.value())
        when:
            ErrorResponse result = performLoginUsersRequest(new UserAuthenticationRequest(USER_EMAIL, 'password1'))
                .statusCode(UNAUTHORIZED.value())
                .extract()
                .as(ErrorResponse)
        then:
            result.error() == 'Wrong email address or password'
    }

    private static ValidatableMockMvcResponse performRegisterUserRequest(UserRequest userRequest) {
        given()
            .contentType(APPLICATION_JSON)
            .body(userRequest)
            .when()
            .post('/users/register')
            .then()
    }

    private static ValidatableMockMvcResponse performGetUsersRequest(String token) {
        given()
            .header('Authorization', 'Bearer ' + token)
            .when()
            .get('/users')
            .then()
    }

    private static ValidatableMockMvcResponse performLoginUsersRequest(UserAuthenticationRequest authenticationRequest) {
        given()
            .contentType(APPLICATION_JSON)
            .body(authenticationRequest)
            .when()
            .post('/login')
            .then()
    }

}
