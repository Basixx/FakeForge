package com.romecka.fakeforge.infrastructure.api.email

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import org.slf4j.LoggerFactory
import spock.lang.Specification
import spock.lang.Subject

class EmailVerificationLoggingServiceSpec extends Specification {

    @Subject
    EmailVerificationLoggingService service = new EmailVerificationLoggingService()

    void 'should log info of disabled feature'() {
        given:
            String email = 'email@example.com'
            Logger logger = (Logger) LoggerFactory.getLogger(EmailVerificationLoggingService)
            ListAppender<ILoggingEvent> appender = new ListAppender<>()
            appender.start()
            logger.addAppender(appender)
        when:
            service.verify(email)
        then:
            noExceptionThrown()
            appender.list.size() == 1
            with(appender.list.first()) {
                level == Level.INFO
                formattedMessage == "Email verification disabled, no validation provided for address $email"
            }
    }

}
