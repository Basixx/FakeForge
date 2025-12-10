package com.romecka.fakeforge.infrastructure.generator;

import com.romecka.fakeforge.domain.person.Gender;
import net.datafaker.providers.base.Address;
import net.datafaker.providers.base.IdNumber.GenderRequest;
import net.datafaker.providers.base.IdNumber.IdNumberRequest;

import java.util.List;

import static com.romecka.fakeforge.infrastructure.generator.DataGenerator.dataGenerator;
import static com.romecka.fakeforge.infrastructure.generator.PeselGenerator.peselGenerator;
import static com.romecka.fakeforge.utils.CsvUtils.fromClasspathCsv;
import static com.romecka.fakeforge.utils.RandomUtils.randomEnum;
import static com.romecka.fakeforge.utils.RandomUtils.randomListItem;
import static com.romecka.fakeforge.utils.StringUtils.polishToLatin;

public class PersonalDataGenerator {

    private static final List<String> FEMALE_FIRST_NAMES;

    private static final List<String> FEMALE_LAST_NAMES;

    private static final List<String> MALE_FIRST_NAMES;

    private static final List<String> MALE_LAST_NAMES;

    private static final String FEMALE_LAST_NAMES_FILE = "data/female_last_names.csv";

    private static final String FEMALE_FIRST_NAMES_FILE = "data/female_first_names.csv";

    private static final String MALE_FIRST_NAMES_FILE = "data/male_first_names.csv";

    private static final String MALE_LAST_NAMES_FILE = "data/male_last_names.csv";

    static {
        FEMALE_FIRST_NAMES = fromClasspathCsv(FEMALE_FIRST_NAMES_FILE);
        FEMALE_LAST_NAMES = fromClasspathCsv(FEMALE_LAST_NAMES_FILE);
        MALE_FIRST_NAMES = fromClasspathCsv(MALE_FIRST_NAMES_FILE);
        MALE_LAST_NAMES = fromClasspathCsv(MALE_LAST_NAMES_FILE);
    }

    public static String firstName(Gender gender) {
        return switch (gender) {
            case MALE -> randomListItem(MALE_FIRST_NAMES);
            case FEMALE -> randomListItem(FEMALE_FIRST_NAMES);
        };
    }

    public static String lastName(Gender gender) {
        return switch (gender) {
            case MALE -> randomListItem(MALE_LAST_NAMES);
            case FEMALE -> randomListItem(FEMALE_LAST_NAMES);
        };
    }

    public static String emailAddress(String firstName, String lastName) {
        return dataGenerator().internet().emailAddress(emailLocalPart(firstName, lastName));
    }

    public static String personalId(Gender gender, int age) {
        return peselGenerator().generateValid(
                dataGenerator(),
                new IdNumberRequest(
                        age,
                        age + 1,
                        GenderRequest.valueOf(gender.name())
                )
        ).idNumber();

    }

    public static String cellPhoneNumber() {
        return dataGenerator().regexify("(45|50|51|52|53|57|60|66|69|72|73|78|79|88)[0-9]{7}");
    }

    public static String generateBankAccountNumber() {
        Bank bank = randomEnum(Bank.class);
        return new BankAccountNumber(bank).toString();
    }

    public static String generateDocumentNumber() {
        return new DocumentNumber().toString();
    }

    public static Address address() {
        return dataGenerator().address();
    }

    private static String emailLocalPart(String firstName, String lastName) {
        return polishToLatin((firstName + "." + lastName).toLowerCase());
    }

}
