package com.romecka.fakeforge.infrastructure.db.limit

import com.romecka.fakeforge.infrastructure.db.user.UserEntity
import com.romecka.fakeforge.infrastructure.db.user.UserRepository
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

import static org.apache.commons.lang3.RandomStringUtils.insecure

@SpringBootTest
@Transactional
class LimitRepositorySpec extends Specification {

    @Subject
    @Autowired
    LimitRepository limitRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    EntityManager entityManager

    void 'should save limit entity'() {
        given:
            LimitEntity limitEntity = limitEntity(100, 50)
        expect:
            with(limitRepository.save(limitEntity)) {
                id() != null
                dailyLimit() == limitEntity.dailyLimit()
                availableLimit() == limitEntity.availableLimit()
            }
    }

    void 'should find limit entity by ID'() {
        given:
            LimitEntity limitEntity = saveEntity(100, 75)
        when:
            Optional<LimitEntity> result = limitRepository.findById(limitEntity.id())
        then:
            with(result.get()) {
                id() == limitEntity.id()
                dailyLimit() == limitEntity.dailyLimit()
                availableLimit() == limitEntity.availableLimit()
            }
    }

    void 'should return empty optional if entity not found by ID'() {
        expect:
            limitRepository.findById(99L) == Optional.empty()
    }

    void 'should find limit entity by user ID'() {
        given:
            UserEntity user = saveUserWithLimit(200, 150)
        when:
            Optional<LimitEntity> result = limitRepository.findByUserId(user.id())
        then:
            with(result.get()) {
                id() == user.limit().id()
                dailyLimit() == 200
                availableLimit() == 150
                user.id() == user.id()
            }
    }

    void 'should return empty optional if limit not found by user ID'() {
        expect:
            limitRepository.findByUserId(99L) == Optional.empty()
    }

    void 'should reset all limits to daily limit'() {
        given:
            saveUserWithLimit(100, 30)
            saveUserWithLimit(200, 50)
            entityManager.flush()
        when:
            limitRepository.resetAllLimits()
            entityManager.clear()
        then:
            List<LimitEntity> allLimits = limitRepository.findAll()
            allLimits.size() == 2
            allLimits.every { it.availableLimit() == it.dailyLimit() }
    }

    private static LimitEntity limitEntity(int dailyLimit, int availableLimit) {
        new LimitEntity(
            dailyLimit: dailyLimit,
            availableLimit: availableLimit
        )
    }

    private LimitEntity saveEntity(int dailyLimit, int availableLimit) {
        limitRepository.save(limitEntity(dailyLimit, availableLimit))
    }

    private UserEntity saveUserWithLimit(int dailyLimit, int availableLimit) {
        LimitEntity limit = saveEntity(dailyLimit, availableLimit)
        UserEntity user = new UserEntity(
            name: 'Test',
            lastName: 'User',
            emailAddress: "user${insecure().nextAlphabetic(3)}@example.com",
            password: 'password',
            role: 'USER',
            limit: limit
        )
        userRepository.save(user)
    }

}
