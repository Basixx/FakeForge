package com.romecka.fakeforge.domain

import com.romecka.fakeforge.domain.limit.Limit
import com.romecka.fakeforge.domain.limit.LimitNotFoundException
import com.romecka.fakeforge.domain.limit.LimitService
import com.romecka.fakeforge.domain.limit.Limits
import spock.lang.Specification
import spock.lang.Subject

class LimitServiceSpec extends Specification {

    Limit limit = Stub({
        dailyLimit() >> 100
        availableLimit() >> 50
    })

    Limit limit2 = Stub({
        dailyLimit() >> 200
        availableLimit() >> 100
    })

    Limits limits = Mock()

    @Subject
    LimitService limitService = new LimitService(limits)


    void "should return limit for existing user"() {
        given: "configure the limits stub to return the limit for a specific user ID"
            limits.getLimit(1) >> Optional.of(limit)

        when: "we request the limit for this user"
            Limit result = limitService.getUserLimit(1)

        then: "the correct limit should be returned"
            1 * limits.getLimit(1) >> Optional.of(limit)
            result.with {
                dailyLimit() == 100
                availableLimit() == 50
            }
    }

    void "should throw LimitNotFoundException when limit does not exist"() {
        given: "a user ID with no limit"
            Long userId = 99L

        when: "we request the limit for this user"
            limitService.getUserLimit(userId)

        then: "the limits service should have been called exactly once"
            1 * limits.getLimit(userId) >> Optional.empty()

        and: "a LimitNotFoundException should be thrown"
            thrown(LimitNotFoundException)

    }

    void "should handle different user IDs correctly"() {
        given: "two different user IDs"
            long userId1 = 123L
            long userId2 = 456L

        and: "the limits service returns different limits for different users"
            limits.getLimit(userId1) >> Optional.of(limit)
            limits.getLimit(userId2) >> Optional.of(limit2)

        when: "we request limits for both users"
            Limit result1 = limitService.getUserLimit(userId1)
            Limit result2 = limitService.getUserLimit(userId2)

        then: "the correct limits should be returned for each user"
            result1 == limit
            result2 == limit2
            1 * limits.getLimit(userId1) >> Optional.of(limit)
            1 * limits.getLimit(userId2) >> Optional.of(limit2)

    }

}
