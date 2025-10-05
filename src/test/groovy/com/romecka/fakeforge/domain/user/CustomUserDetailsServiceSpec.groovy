package com.romecka.fakeforge.domain.user

import org.springframework.security.core.userdetails.UserDetails
import spock.lang.Specification
import spock.lang.Subject

class CustomUserDetailsServiceSpec extends Specification {

    User user = Stub {
        id() >> 1
        name() >> 'name'
        lastName() >> 'lastName'
        emailAddress() >> 'user@user.com'
        password() >> 'password'
        role() >> 'USER'
    }

    Users users = Mock()

    @Subject
    CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(users)

    void 'should invoke get users by email'() {
        given:
            String email = 'user@user.com'
        when:
            UserDetails result = customUserDetailsService.loadUserByUsername(email)
        then:
            1 * users.getUserByEmail(email) >> user
        and:
            with(result) {
                username == user.emailAddress()
                password == user.password()
            }
    }

    void 'should throw exception when user not found'() {
        given:
            String email = 'user@user.com'
        when:
            customUserDetailsService.loadUserByUsername(email)
        then:
            1 * users.getUserByEmail(email) >> { throw new UserNotFoundException(email) }
        and:
            thrown(UserNotFoundException)
    }

}
