package com.romecka.fakeforge.infrastructure.encoder

import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject

class DataEncoderImplSpec extends Specification {

    private PasswordEncoder passwordEncoder = Mock()

    @Subject
    DataEncoderImpl dataEncoder = new DataEncoderImpl(passwordEncoder)

    void 'should invoke encode'() {
        given:
            String data = 'abc'
            String encodedData = 'xxx'
        when:
            String result = dataEncoder.encode(data)
        then:
            1 * passwordEncoder.encode(data) >> encodedData
        and:
            result == encodedData
    }

}
