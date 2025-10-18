package com.romecka.fakeforge.infrastructure.db.user

import com.romecka.fakeforge.domain.limit.LimitProvider
import com.romecka.fakeforge.domain.user.DataEncoder
import com.romecka.fakeforge.domain.user.User
import com.romecka.fakeforge.domain.user.UserNotFoundException
import com.romecka.fakeforge.domain.user.UserParams
import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity
import spock.lang.Specification
import spock.lang.Subject

class UserStorageSpec extends Specification {

    private UserEntity user = Stub {
        name() >> 'name'
        lastName() >> 'lastName'
        emailAddress() >> 'email'
        password() >> 'password'
        role() >> 'ROLE_USER'
    }

    private UserEntity secondUser = Stub {
        name() >> 'name2'
        lastName() >> 'lastName2'
        emailAddress() >> 'email2'
        password() >> 'password2'
        role() >> 'ROLE_ADMIN'
    }

    UserRepository userRepository = Mock()

    DataEncoder dataEncoder = Mock()

    LimitProvider limitProvider = Mock()

    @Subject
    UserStorage userStorage = new UserStorage(userRepository,
        dataEncoder,
        limitProvider)

    void 'should invoke register user'() {
        given:
            UserParams userParams = Stub()
            LimitEntity limitEntity = Stub()
        when:
            User result = userStorage.registerUser(userParams)
        then:
            1 * limitProvider.generateDefaultLimit() >> limitEntity
            1 * dataEncoder.encode(_ as String)
            1 * userRepository.save(_ as User) >> user
        and:
            with(result) {
                name() == user.name()
                lastName() == user.lastName()
                emailAddress() == user.emailAddress()
                password() == user.password()
                role() == user.role()
            }
    }

    void 'should invoke get all users'() {
        when:
            List<User> result = userStorage.getUsers(0, 10)
        then:
            1 * userRepository.findAll(0, 10) >> [user, secondUser]
        and:
            with(result) {
                size() == 2
                with(first) {
                    name() == user.name()
                    lastName() == user.lastName()
                    emailAddress() == user.emailAddress()
                    password() == user.password()
                    role() == user.role()
                }
                with(last) {
                    name() == secondUser.name()
                    lastName() == secondUser.lastName()
                    emailAddress() == secondUser.emailAddress()
                    password() == secondUser.password()
                    role() == secondUser.role()
                }
            }
    }

    void 'should return empty list if there are no users'() {
        when:
            List<User> result = userStorage.getUsers(0, 10)
        then:
            1 * userRepository.findAll(0, 10) >> []
        and:
            result.size() == 0
    }

    void 'should invoke get user by email'() {
        given:
            String email = 'email'
        when:
            User result = userStorage.getUserByEmail(email)
        then:
            1 * userRepository.findByEmailAddress(email) >> Optional.of(user)
        and:
            with(result) {
                name() == user.name()
                lastName() == user.lastName()
                emailAddress() == user.emailAddress()
                password() == user.password()
                role() == user.role()
            }
    }

    void 'should throw an exception when user not found by email'() {
        given:
            String email = 'email'
        when:
            userStorage.getUserByEmail(email)
        then:
            1 * userRepository.findByEmailAddress(email) >> Optional.empty()
        and:
            thrown(UserNotFoundException)
    }

}
