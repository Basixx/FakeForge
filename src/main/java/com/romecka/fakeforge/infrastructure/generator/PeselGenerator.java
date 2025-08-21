package com.romecka.fakeforge.infrastructure.generator;

import net.datafaker.idnumbers.PolishIdNumber;

public class PeselGenerator extends PolishIdNumber {

    PeselGenerator() {
        super();
    }

    public static PeselGenerator peselGenerator() {
        return new PeselGenerator();
    }

}
