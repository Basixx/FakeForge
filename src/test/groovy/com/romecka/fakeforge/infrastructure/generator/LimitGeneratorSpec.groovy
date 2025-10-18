package com.romecka.fakeforge.infrastructure.generator

import com.romecka.fakeforge.infrastructure.db.limit.LimitEntity
import spock.lang.Specification
import spock.lang.Subject

class LimitGeneratorSpec extends Specification {

    @Subject
    LimitGenerator limitGenerator = new LimitGenerator()

    void 'should return new default limit entity'() {
        when:
            LimitEntity result = limitGenerator.generateDefaultLimit()
            LimitEntity secondResult = limitGenerator.generateDefaultLimit()
        then:
            with(result) {
                dailyLimit() == 100
                availableLimit() == 100
            }
            with(secondResult) {
                dailyLimit() == 100
                availableLimit() == 100
            }
            result !== secondResult
    }
    
}
