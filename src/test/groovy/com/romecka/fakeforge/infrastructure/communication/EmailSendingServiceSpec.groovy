package com.romecka.fakeforge.infrastructure.communication

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.romecka.fakeforge.domain.communication.Mail
import org.slf4j.LoggerFactory
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Specification
import spock.lang.Subject

class EmailSendingServiceSpec extends Specification {

    private Mail mail = new Mail('user@mail.com',
        'subject',
        'body')

    JavaMailSender javaMailSender = Mock()

    @Subject
    EmailSendingService emailSendingService = new EmailSendingService(javaMailSender)

    void 'should send an email'() {
        when:
            emailSendingService.sendEmail(mail)
        then:
            1 * javaMailSender.send({ SimpleMailMessage msg ->
                assert msg.to == [mail.mailTo()] as String[]
                assert msg.subject == mail.subject()
                assert msg.text == mail.message()
                true
            })
    }

    void 'should log info of failed email'() {
        given:
            Logger logger = (Logger) LoggerFactory.getLogger(EmailSendingService)
            ListAppender<ILoggingEvent> appender = new ListAppender<>()
            appender.start()
            logger.addAppender(appender)
            javaMailSender.send(_ as SimpleMailMessage) >> {
                throw new MailException("mail exception") {
                }
            }
        when:
            emailSendingService.sendEmail(mail)
        then:
            appender.list.size() == 1
            with(appender.list.last()) {
                level == Level.ERROR
                formattedMessage == "Failed to process email sending: mail exception"
            }
    }

}
