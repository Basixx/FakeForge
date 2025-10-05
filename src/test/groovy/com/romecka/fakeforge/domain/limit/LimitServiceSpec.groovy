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

    void "should invoke get limit for existing user"() {
        given:
            limits.getLimit(1) >> Optional.of(primaryLimit)
        when:
            Limit result = limitService.getUserLimit(1)
        then:
            1 * limits.getLimit(1) >> Optional.of(primaryLimit)
        and:
            with(result) {
                dailyLimit() == 100
                availableLimit() == 50
            }
    }

    void "should throw LimitNotFoundException when limit does not exist"() {
        given:
            Long userId = 99L
        when:
            limitService.getUserLimit(userId)
        then:
            1 * limits.getLimit(userId) >> Optional.empty()
        and:
            thrown(LimitNotFoundException)
    }

    void "should handle different user IDs correctly"() {
        given:
            long userId1 = 123L
            long userId2 = 456L
        and:
            limits.getLimit(userId1) >> Optional.of(primaryLimit)
            limits.getLimit(userId2) >> Optional.of(secondaryLimit)
        when:
            Limit result1 = limitService.getUserLimit(userId1)
            Limit result2 = limitService.getUserLimit(userId2)
        then:
            1 * limits.getLimit(userId1) >> Optional.of(primaryLimit)
            1 * limits.getLimit(userId2) >> Optional.of(secondaryLimit)
        and:
            result1 == primaryLimit
            result2 == secondaryLimit
    }

}
