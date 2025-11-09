package com.romecka.fakeforge.infrastructure.communication

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.romecka.fakeforge.domain.communication.Mail
import jakarta.mail.Session
import jakarta.mail.internet.MimeMessage
import org.slf4j.LoggerFactory
import org.springframework.mail.MailException
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
        given:
            MimeMessage mimeMessage = new MimeMessage((Session) null)
            javaMailSender.createMimeMessage() >> mimeMessage
        when:
            emailSendingService.sendEmail(mail)
        then:
            1 * javaMailSender.send({ MimeMessage msg ->
                assert msg.getAllRecipients()*.address == [mail.mailTo()]
                assert msg.getSubject() == mail.subject()
                def content = msg.getContent()
                assert content instanceof String
                assert content == mail.message()
                true
            })
    }

    void 'should log info of failed email'() {
        given:
            Logger logger = (Logger) LoggerFactory.getLogger(EmailSendingService)
            ListAppender<ILoggingEvent> appender = new ListAppender<>()
            appender.start()
            logger.addAppender(appender)
            MimeMessage mimeMessage = new MimeMessage((Session) null)
            javaMailSender.createMimeMessage() >> mimeMessage
            javaMailSender.send(_ as MimeMessage) >> {
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
