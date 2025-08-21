package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.infrastructure.db.person.AddressEntity;

import static com.romecka.fakeforge.infrastructure.generator.DataGenerator.dataGenerator;
import static com.romecka.fakeforge.utils.RandomUtils.randomInt;

public class AddressGenerator {

    public static AddressEntity address() {
        return new AddressEntity()
                .street(street())
                .buildingNumber(randomInt(1, 100))
                .apartmentNumber(randomInt(1, 200))
                .postalCode(postalCode())
                .city(city());
    }

    private static String street() {
        return dataGenerator().address().streetName();
    }

    private static String city() {
        return dataGenerator().address().city();
    }

    private static String postalCode() {
        return dataGenerator().address().zipCode();
    }

}
