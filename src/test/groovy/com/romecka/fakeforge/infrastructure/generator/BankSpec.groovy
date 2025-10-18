package com.romecka.fakeforge.infrastructure.generator

import spock.lang.Specification
import spock.lang.Subject

class BankSpec extends Specification {

    @Subject
    Bank bank = Bank.ALIOR

    void 'should return bank code'() {
        when:
            String result = bank.code()
        then:
            result == '249'
    }

}
