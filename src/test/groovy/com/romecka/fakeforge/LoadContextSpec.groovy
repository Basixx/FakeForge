package com.romecka.fakeforge

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest
class LoadContextSpec extends Specification {

    @Autowired
    private ApplicationContext context

    void 'context loads properly'() {
        expect:
            context
    }
}
