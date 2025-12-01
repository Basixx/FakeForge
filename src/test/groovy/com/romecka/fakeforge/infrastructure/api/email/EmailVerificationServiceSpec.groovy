package com.romecka.fakeforge.infrastructure.api.email

import com.romecka.fakeforge.domain.user.EmailAddressInvalidException
import com.romecka.fakeforge.domain.user.EmailVerificationFailedException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

import java.lang.reflect.Field

class EmailVerificationServiceSpec extends Specification {

    RestTemplate restTemplate = Mock()

    @Subject
    EmailVerificationService service = new EmailVerificationService(restTemplate)

    void setup() {
        setField(service, 'emailCheckEndpoint', 'https://api.example.com/check')
        setField(service, 'emailCheckKey', 'secret-key')
    }

    void 'should pass when provider returns emailExists=true'() {
        given:
            restTemplate.getForObject(_ as URI, EmailVerificationDto) >> new EmailVerificationDto(true)
        when:
            service.verify('john@example.com')
        then:
            noExceptionThrown()
    }

    void 'should throw EmailAddressInvalidException when emailExists=false'() {
        given:
            restTemplate.getForObject(_ as URI, EmailVerificationDto) >> new EmailVerificationDto(false)
        when:
            service.verify('bad@example.com')
        then:
            thrown(EmailAddressInvalidException)
    }

    void 'should throw EmailVerificationFailedException when response is null'() {
        given:
            restTemplate.getForObject(_ as URI, EmailVerificationDto) >> null
        when:
            service.verify('any@example.com')
        then:
            thrown(EmailVerificationFailedException)
    }

    void 'should throw EmailVerificationFailedException when RestTemplate throws'() {
        given:
            restTemplate.getForObject(_ as URI, EmailVerificationDto) >> {
                throw new RuntimeException('network')
            }
        when:
            service.verify('any@example.com')
        then:
            thrown(EmailVerificationFailedException)
    }

    private static void setField(Object target,
                                 String name,
                                 Object value) {
        Field f = target.class.getDeclaredField(name)
        f.accessible = true
        f.set(target, value)
    }

}
