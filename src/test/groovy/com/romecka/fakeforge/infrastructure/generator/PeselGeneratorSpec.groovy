package com.romecka.fakeforge.infrastructure.generator

import spock.lang.Specification

import static com.romecka.fakeforge.infrastructure.generator.PeselGenerator.peselGenerator

class PeselGeneratorSpec extends Specification {

    void 'should return new instance'() {
        when:
            PeselGenerator firstGenerator = peselGenerator()
            PeselGenerator secondGenerator = peselGenerator()
        then:
            firstGenerator instanceof PeselGenerator
            secondGenerator instanceof PeselGenerator
            firstGenerator !== secondGenerator
    }

}
