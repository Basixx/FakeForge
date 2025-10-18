package com.romecka.fakeforge.infrastructure.generator

import spock.lang.Specification

import static com.romecka.fakeforge.infrastructure.generator.DataGenerator.dataGenerator

class DataGeneratorSpec extends Specification {

    void 'should return new instance'() {
        when:
            DataGenerator firstGenerator = dataGenerator()
            DataGenerator secondGenerator = dataGenerator()
        then:
            firstGenerator instanceof DataGenerator
            secondGenerator instanceof DataGenerator
            firstGenerator !== secondGenerator
    }

}
