package com.romecka.fakeforge.infrastructure.db.limit

import com.romecka.fakeforge.domain.limit.Limit
import com.romecka.fakeforge.domain.limit.LimitExceededException
import com.romecka.fakeforge.domain.limit.LimitForUserNotFoundException
import spock.lang.Specification
import spock.lang.Subject

class LimitStorageSpec extends Specification {

    LimitRepository limitRepository = Mock()

    @Subject
    LimitStorage limitStorage = new LimitStorage(limitRepository)

    void 'should invoke find limit'() {
        given:
            LimitEntity limitEntity = Stub {
                dailyLimit() >> 100
                availableLimit() >> 50
            }
        when:
            Limit result = limitStorage.getLimit(1L)
        then:
            1 * limitRepository.findByUserId(1L) >> Optional.of(limitEntity)
        and:
            with(result) {
                dailyLimit() == limitEntity.dailyLimit()
                availableLimit() == limitEntity.availableLimit()
            }
    }

    void 'should throw exception when limit not found'() {
        when:
            limitStorage.getLimit(1L)
        then:
            1 * limitRepository.findByUserId(1L) >> Optional.empty()
        and:
            thrown(LimitForUserNotFoundException)
    }

    void 'should invoke find and decrement limit'() {
        given:
            LimitEntity limitEntity = Stub {
                availableLimit() >> 1
            }
        when:
            limitStorage.useLimit(1)
        then:
            1 * limitRepository.findByUserId(1) >> Optional.of(limitEntity)
            1 * limitRepository.save(_ as LimitEntity)
    }

    void 'should throw exception when decremented limit is exceeded'() {
        given:
            LimitEntity limitEntity = Stub {
                availableLimit() >> 0
            }
        when:
            limitStorage.useLimit(1)
        then:
            1 * limitRepository.findByUserId(1) >> Optional.of(limitEntity)
            0 * limitRepository.save(_ as LimitEntity)
        and:
            thrown(LimitExceededException)
    }

    void 'should throw exception when decremented limit is not found'() {
        when:
            limitStorage.useLimit(1)
        then:
            1 * limitRepository.findByUserId(1) >> Optional.empty()
            0 * limitRepository.save(_ as LimitEntity)
        and:
            thrown(LimitForUserNotFoundException)
    }

    void 'should invoke reset limits'() {
        when:
            limitStorage.resetLimits()
        then:
            1 * limitRepository.resetAllLimits()
    }

    void 'should invoke update limit'() {
        long userId = 1
        int dailyLimit = 200
        LimitEntity limitEntity = Stub()
        when:
            limitStorage.updateLimit(userId, dailyLimit)
        then:
            1 * limitRepository.findByUserId(userId) >> Optional.of(limitEntity)
            1 * limitRepository.save(_ as LimitEntity)
    }

    void 'should throw exception when updated limit not found'() {
        long userId = 1
        int dailyLimit = 200
        when:
            limitStorage.updateLimit(userId, dailyLimit)
        then:
            1 * limitRepository.findByUserId(userId) >> Optional.empty()
            0 * limitRepository.save(_ as LimitEntity)
        and:
            thrown(LimitForUserNotFoundException)
    }

}
