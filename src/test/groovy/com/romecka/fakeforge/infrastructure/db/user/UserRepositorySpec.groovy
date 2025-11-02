package com.romecka.fakeforge.infrastructure.db.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
@Transactional
class UserRepositorySpec extends Specification {

    @Subject
    @Autowired
    UserRepository userRepository

    void 'should save user entity'() {
        given:
            UserEntity userEntity = userEntity('email')
        expect:
            with(userRepository.save(userEntity)) {
                id() != null
                name() == userEntity.name()
                lastName() == userEntity.lastName()
                emailAddress() == userEntity.emailAddress()
                password() == userEntity.password()
                role() == userEntity.role()
            }
    }

    void 'should find user entity by ID'() {
        given:
            UserEntity userEntity = saveEntity('email1')
        when:
            Optional<UserEntity> result = userRepository.findById(userEntity.id())
        then:
            with(result.get()) {
                id() == userEntity.id()
                name() == userEntity.name()
                lastName() == userEntity.lastName()
                emailAddress() == userEntity.emailAddress()
                password() == userEntity.password()
                role() == userEntity.role()
            }
    }

    void 'should return empty optional if entity not found by ID'() {
        expect:
            userRepository.findById(99) == Optional.empty()
    }

    void 'should find user entity by email'() {
        given:
            String email = 'email2'
            UserEntity userEntity = saveEntity(email)
        when:
            Optional<UserEntity> result = userRepository.findByEmailAddress(email)
        then:
            with(result.get()) {
                id() == userEntity.id()
                name() == userEntity.name()
                lastName() == userEntity.lastName()
                emailAddress() == userEntity.emailAddress()
                password() == userEntity.password()
                role() == userEntity.role()
            }
    }

    void 'should return proper list for pageable user list'() {
        given:
            UserEntity userEntity = saveEntity('email3')
            UserEntity secondUserEntity = saveEntity('email4')
        when:
            List<UserEntity> result = userRepository.findAll(0, 10)
        then:
            with(result) {
                size() == 2
                with(first) {
                    id() == userEntity.id()
                    name() == userEntity.name()
                    lastName() == userEntity.lastName()
                    emailAddress() == userEntity.emailAddress()
                    password() == userEntity.password()
                    role() == userEntity.role()
                }
                with(last) {
                    id() == secondUserEntity.id()
                    name() == secondUserEntity.name()
                    lastName() == secondUserEntity.lastName()
                    emailAddress() == secondUserEntity.emailAddress()
                    password() == secondUserEntity.password()
                    role() == secondUserEntity.role()
                }
            }
    }

    void 'should return empty list'() {
        expect:
            userRepository.findAll(0, 10).size() == 0
    }

    private static UserEntity userEntity(String email) {
        new UserEntity(
            name: 'abc',
            lastName: 'xyz',
            emailAddress: email,
            password: 'asd',
            role: 'ad'
        )
    }

    private UserEntity saveEntity(UserEntity userEntity) {
        userRepository.save(userEntity)
    }

    private UserEntity saveEntity(String email) {
        saveEntity(userEntity(email))
    }

}
