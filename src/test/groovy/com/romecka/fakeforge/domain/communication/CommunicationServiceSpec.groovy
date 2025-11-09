package com.romecka.fakeforge.domain.communication

import spock.lang.Specification
import spock.lang.Subject

class CommunicationServiceSpec extends Specification {

    EmailSender emailSender = Mock()

    EmailTemplate emailTemplate = Mock()

    @Subject
    CommunicationService communicationService = new CommunicationService(emailSender, emailTemplate)

    void 'should send registration email'() {
        given:
            String name = 'Bob'
            Mail mail = new Mail(
                'user@mail.com',
                'subject',
                'body'
            )
        when:
            communicationService.sendRegistrationEmail(mail.mailTo(), name)
        then:
            1 * emailTemplate.createRegistrationEmail(mail.mailTo(), name) >> mail
            1 * emailSender.sendEmail(mail)
    }

}
