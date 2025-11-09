package com.romecka.fakeforge.domain.user

import com.romecka.fakeforge.domain.communication.CommunicationService
import com.romecka.fakeforge.domain.token.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import spock.lang.Specification
import spock.lang.Subject

class UserServiceSpec extends Specification {

    User user = Stub {
        id() >> 1
        name() >> 'name'
        lastName() >> 'lastName'
        emailAddress() >> 'user@user.com'
        password() >> 'password'
        role() >> 'USER'
    }

    User secondUser = Stub {
        id() >> 2
        name() >> 'name2'
        lastName() >> 'lastName2'
        emailAddress() >> 'user2@user.com'
        password() >> 'password2'
        role() >> 'USER2'
    }

    Users users = Mock()

    AuthenticationManager authenticationManager = Mock()

    TokenService tokenService = Mock()

    CommunicationService communicationService = Mock()

    @Subject
    UserService userService = new UserService(users,
        authenticationManager,
        tokenService,
        communicationService)

    void 'should invoke register user'() {
        given:
            UserParams userParams = Stub()
        when:
            User result = userService.registerUser(userParams)
        then:
            1 * users.registerUser(userParams) >> user
            1 * communicationService.sendRegistrationEmail(user.emailAddress(), user.name())
        and:
            with(result) {
                id() == user.id()
                name() == user.name()
                lastName() == user.lastName()
                emailAddress() == user.emailAddress()
                password() == user.password()
                role() == user.role()
            }
    }

    void 'should invoke get users'() {
        when:
            List<User> result = userService.getUsers(1, 10)
        then:
            1 * users.getUsers(1, 10) >> [user, secondUser]
        and:
            with(result) {
                size() == 2
                with(first) {
                    id() == user.id()
                    name() == user.name()
                    lastName() == user.lastName()
                    emailAddress() == user.emailAddress()
                    password() == user.password()
                    role() == user.role()
                }
                with(last) {
                    id() == secondUser.id()
                    name() == secondUser.name()
                    lastName() == secondUser.lastName()
                    emailAddress() == secondUser.emailAddress()
                    password() == secondUser.password()
                    role() == secondUser.role()
                }
            }
    }

    void 'should invoke user authentication'() {
        given:
            String token = 'token'
            UserAuthenticationParams authenticationParams = Stub()
        when:
            String result = userService.authenticateAndGenerateToken(authenticationParams)
        then:
            1 * authenticationManager.authenticate(_ as UsernamePasswordAuthenticationToken)
            1 * tokenService.generateToken(_ as String) >> token
        and:
            result == token
    }

    void 'should throw LoginFailedException when authentication fails'() {
        given:
            UserAuthenticationParams authenticationParams = Stub()
        when:
            userService.authenticateAndGenerateToken(authenticationParams)
        then:
            1 * authenticationManager.authenticate(_ as UsernamePasswordAuthenticationToken) >> {
                throw new AuthenticationException('fail') {
                }
            }
            0 * tokenService.generateToken(_)
        and:
            thrown(LoginFailedException)
    }

}
