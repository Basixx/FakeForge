package com.romecka.fakeforge.infrastructure.generator;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

import static com.romecka.fakeforge.infrastructure.generator.DataGenerator.dataGenerator;
import static java.lang.String.valueOf;

public class BankAccountNumber {

    private static final String PL_CODE = "252100";

    private final Bank bank;

    private final String controlSum;

    private final String bankBranchCode;

    private final String customerNumber;

    public BankAccountNumber(Bank bank) {
        this.bank = bank;
        this.bankBranchCode = bankBranchCode();
        this.customerNumber = customerNumber();
        this.controlSum = controlSum();
    }

    private String bankBranchCode() {
        String branch = bank.code() + "0" + bank.code();
        String controlDigit = controlDigit(branch);
        return branch + controlDigit;
    }

    private String controlDigit(String branch) {
        char[] digits = branch.toCharArray();
        long sum = (int) digits[0] * 3
                + (int) digits[1] * 9
                + (int) digits[2] * 7
                + (int) digits[3]
                + (int) digits[4] * 3
                + (int) digits[5] * 9
                + (int) digits[6] * 7;
        return valueOf(sum % 10);
    }

    private String customerNumber() {
        return dataGenerator().regexify("[0-9]{16}");
    }

    private String controlSum() {
        int result = 98 - modulo(bankBranchCode
                .concat(customerNumber)
                .concat(PL_CODE));
        return result >= 10 ? valueOf(result) : result + "0";
    }

    private int modulo(String number) {
        BigInteger bigNumber = new BigInteger(number);
        BigInteger modulus = new BigInteger("97");
        return bigNumber.mod(modulus).intValue();
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
