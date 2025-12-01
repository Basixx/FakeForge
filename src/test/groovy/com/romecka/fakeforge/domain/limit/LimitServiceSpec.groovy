package com.romecka.fakeforge.domain.limit

import spock.lang.Specification
import spock.lang.Subject

class LimitServiceSpec extends Specification {

    Limit primaryLimit = Stub {
        dailyLimit() >> 100
        availableLimit() >> 50
    }

    Limit secondaryLimit = Stub {
        dailyLimit() >> 200
        availableLimit() >> 100
    }

    Limits limits = Mock()

    @Subject
    LimitService limitService = new LimitService(limits)

    void 'should invoke get limit for existing user'() {
        when:
            Limit result = limitService.getUserLimit(1)
        then:
            1 * limits.getLimit(1) >> primaryLimit
        and:
            with(result) {
                dailyLimit() == primaryLimit.dailyLimit()
                availableLimit() == primaryLimit.availableLimit()
            }
    }

    void 'should throw LimitNotFoundException when limit does not exist'() {
        given:
            Long userId = 99L
        when:
            limitService.getUserLimit(userId)
        then:
            1 * limits.getLimit(userId) >> {
                throw new LimitForUserNotFoundException(userId)
            }
        and:
            thrown(LimitForUserNotFoundException)
    }

    void 'should handle different user IDs correctly'() {
        given:
            long userId1 = 123L
            long userId2 = 456L
        when:
            Limit result1 = limitService.getUserLimit(userId1)
            Limit result2 = limitService.getUserLimit(userId2)
        then:
            1 * limits.getLimit(userId1) >> primaryLimit
            1 * limits.getLimit(userId2) >> secondaryLimit
        and:
            result1 == primaryLimit
            result2 == secondaryLimit
    }

    void 'should invoke reset limits'() {
        when:
            limitService.resetLimits()
        then:
            1 * limits.resetLimits()
    }

    void 'should invoke update limit'() {
        given:
            long userId = 1
            int dailyLimit = 200
        when:
            limitService.updateLimit(userId, dailyLimit)
        then:
            1 * limits.updateLimit(userId, dailyLimit)
    }

}
