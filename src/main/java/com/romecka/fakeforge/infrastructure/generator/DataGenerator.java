package com.romecka.fakeforge.infrastructure.generator;

import net.datafaker.Faker;

import java.util.Locale;

public class DataGenerator extends Faker {

    private static final Locale LOCALE = Locale.of("pl", "PL");

    DataGenerator() {
        super(LOCALE);
    }

    public static DataGenerator dataGenerator() {
        return new DataGenerator();
    }

}
