package com.romecka.fakeforge.infrastructure.communication

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.romecka.fakeforge.domain.communication.Mail
import org.slf4j.LoggerFactory
import spock.lang.Specification
import spock.lang.Subject

class EmailSendingLoggingServiceSpec extends Specification {

    @Subject
    EmailSendingLoggingService service = new EmailSendingLoggingService()

    void 'should log info of disabled feature'() {
        given:
            Mail mail = new Mail(
                'user@mail.com',
                'subject',
                'body'
            )
            Logger logger = (Logger) LoggerFactory.getLogger(EmailSendingLoggingService)
            ListAppender<ILoggingEvent> appender = new ListAppender<>()
            appender.start()
            logger.addAppender(appender)
        when:
            service.sendEmail(mail)
        then:
            noExceptionThrown()
            appender.list.size() == 1
            with(appender.list.first()) {
                level == Level.INFO
                formattedMessage.contains("Email sending disabled, skipping email to")
            }
    }
}
