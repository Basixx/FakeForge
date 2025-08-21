package com.romecka.fakeforge.infrastructure.generator;

import org.apache.commons.lang3.StringUtils;

import static com.romecka.fakeforge.infrastructure.generator.DataGenerator.dataGenerator;
import static com.romecka.fakeforge.utils.RandomUtils.randomListItem;
import static java.lang.String.valueOf;

public class BankAccountNumber {

    private static final String PL_CODE = "252100";

    private final Bank bank;

    private final String bankBranchCode;

    private final String customerNumber;

    private final String controlSum;

    private int modulo;

    public BankAccountNumber(Bank bank) {
        this.bank = bank;
        this.bankBranchCode = bankBranchCode();
        this.customerNumber = customerNumber();
        this.controlSum = controlSum();
    }

    private String bankBranchCode() {
//        String branch = bank.code() + bank.code().substring(0, 3);
//        String controlDigit = controlDigit(branch);
//        return branch + controlDigit;
        return bank.code() + randomListItem(bank.sortCodes());
    }

    private String controlDigit(String branch) {
        char[] digits = branch.toCharArray();
        int sum = (int) digits[0] * 7
                + (int) digits[1]
                + (int) digits[2] * 3
                + (int) digits[3] * 9
                + (int) digits[4] * 7
                + (int) digits[5]
                + (int) digits[6] * 3;
        return valueOf(sum % 10);
    }

    private String customerNumber() {
        return dataGenerator().numerify("################");
    }

    private String controlSum() {
        int result = 98 - modulo(bankBranchCode.concat(customerNumber).concat(PL_CODE));
        return result < 10 ? "0" + result : valueOf(result);
    }

    private int modulo(String number) {
        number.chars().forEach(ch ->
                modulo = ((10 * modulo) + ch) % 97
        );
        return modulo;
    }

    @Override
    public String toString() {
        return String.join(
                StringUtils.EMPTY,
                "PL",
                controlSum,
                bankBranchCode,
                customerNumber
        );
    }

}
