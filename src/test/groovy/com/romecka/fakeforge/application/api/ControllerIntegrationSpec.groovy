package com.romecka.fakeforge.application.api

import com.romecka.fakeforge.application.service.token.JWTService
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity
import com.romecka.fakeforge.infrastructure.db.person.PersonRepository
import com.romecka.fakeforge.infrastructure.db.user.UserEntity
import com.romecka.fakeforge.infrastructure.db.user.UserRepository
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Stepwise

import static org.mockito.Mockito.when

@SpringBootTest
@AutoConfigureMockMvc
@Stepwise
abstract class ControllerIntegrationSpec extends Specification {

    protected static final String USER_TOKEN = 'user-token'

    protected static final String ADMIN_TOKEN = 'admin-token'

    protected static final String USER_EMAIL = 'user@example.com'

    protected static final String ADMIN_EMAIL = 'admin@example.com'

    @Autowired
    MockMvc mockMvc

    @Autowired
    UserRepository userRepository

    @Autowired
    PersonRepository personRepository

    @Autowired
    PasswordEncoder passwordEncoder

    @MockitoBean
    JWTService jwtService

    @AfterEach
    void cleanup() {
        userRepository.deleteAll()
    }

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc)
        when(jwtService.extractEmail(USER_TOKEN)).thenReturn(USER_EMAIL)
        when(jwtService.validateToken(Mockito.eq(USER_TOKEN), ArgumentMatchers.any())).thenReturn(true)
        when(jwtService.extractEmail(ADMIN_TOKEN)).thenReturn(ADMIN_EMAIL)
        when(jwtService.validateToken(Mockito.eq(ADMIN_TOKEN), ArgumentMatchers.any())).thenReturn(true)
    }

    UserEntity saveUser() {
        LimitEntity limitEntity = new LimitEntity(dailyLimit: 100, availableLimit: 100)
        saveUser(limitEntity)
    }

    UserEntity saveUser(LimitEntity limitEntity) {
        UserEntity user = new UserEntity(name: 'name',
            lastName: 'lastName',
            emailAddress: USER_EMAIL,
            password: passwordEncoder.encode('pass'),
            role: 'ROLE_USER',
            limit: limitEntity)
        userRepository.save(user)
    }

    UserEntity saveAdmin() {
        LimitEntity adminLimitEntity = new LimitEntity(dailyLimit: 100, availableLimit: 100)
        UserEntity adminUser = new UserEntity(name: 'name',
            lastName: 'lastName',
            emailAddress: ADMIN_EMAIL,
            password: 'pass',
            role: 'ROLE_ADMIN',
            limit: adminLimitEntity)
        userRepository.save(adminUser)
    }

}
