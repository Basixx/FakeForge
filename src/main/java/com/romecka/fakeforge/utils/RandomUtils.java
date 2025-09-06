package com.romecka.fakeforge.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.random.RandomGenerator;

public class RandomUtils {

    private static final RandomGenerator RANDOM_GENERATOR = RandomGenerator.getDefault();

    public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[randomInt(values.length)];
    }

    public static int randomInt(int min, int max) {
        return RANDOM_GENERATOR.nextInt(min, max);
    }

    public static <T> T randomListItem(List<T> list) {
        return list.get(randomInt(list.size()));
    }

    private static int randomInt(int max) {
        return randomInt(0, max);
    }

    public static char randomLetter() {
        return RandomStringUtils.secure().nextAlphabetic(1).toUpperCase().charAt(0);
    }

    public static char randomDigit() {
        return RandomStringUtils.secure().nextNumeric(1).charAt(0);
    }

}
