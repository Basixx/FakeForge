package com.romecka.fakeforge.infrastructure.generator;

import static com.romecka.fakeforge.utils.RandomUtils.randomDigit;
import static com.romecka.fakeforge.utils.RandomUtils.randomLetter;
import static java.lang.Character.digit;

public class DocumentNumber {

    private final char firstLetter;

    private final char secondLetter;

    private final char thirdLetter;

    private final char fourthDigit;

    private final char fifthDigit;

    private final char sixthDigit;

    private final char seventhDigit;

    private final char eightDigit;

    private final char ninthDigit;

    public DocumentNumber() {
        this.firstLetter = randomLetter();
        this.secondLetter = randomLetter();
        this.thirdLetter = randomLetter();
        this.fifthDigit = randomDigit();
        this.sixthDigit = randomDigit();
        this.seventhDigit = randomDigit();
        this.eightDigit = randomDigit();
        this.ninthDigit = randomDigit();
        this.fourthDigit = controlDigit();
    }

    private char controlDigit() {
        int sum = digit(firstLetter, 36) * 7
                + digit(secondLetter, 36) * 3
                + digit(thirdLetter, 36)
                + digit(fifthDigit, 10) * 7
                + digit(sixthDigit, 10) * 3
                + digit(seventhDigit, 10)
                + digit(eightDigit, 10) * 7
                + digit(ninthDigit, 10) * 3;
        int aMod = ((sum % 10) + 10) % 10;
        int needed = (10 - aMod) % 10;
        return String.valueOf((9 * needed) % 10).charAt(0);
    }

    @Override
    public String toString() {
        return "%c%c%c%c%c%c%c%c%c".formatted(
                firstLetter,
                secondLetter,
                thirdLetter,
                fourthDigit,
                fifthDigit,
                sixthDigit,
                seventhDigit,
                eightDigit,
                ninthDigit
        );
    }

}
