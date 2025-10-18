package com.romecka.fakeforge.infrastructure.generator;

public enum Bank {

    // @formatter:off
    NBP               ("101"),      // Narodowy Bank Polski
    PKO_BP            ("102"),      // PKO BP
    CITY              ("103"),      // Citi Bank Hanldowy
    ING               ("105"),      // ING Bank Śląski
    BPH               ("106"),      // Bank BPH
    SANTANDER         ("109"),      // Santander Bank Polska
    BGK               ("113"),      // Bank Gospodarstwa Krajowego
    MBANK             ("114"),      // mBank
    MILLENIUM         ("116"),      // Bank Millennium
    PEKAO             ("124"),      // Bank Pekao SA
    HSBC              ("128"),      // HSBC
    POCZTOWY          ("132"),      // Bank Pocztowy
    ENVELO            ("132"),      // Envelo Bank
    EUROBANK          ("147"),      // Eurobank",
    BOS               ("154"),      // Bank Ochrony Środowiska
    PLUS              ("168"),      // Plus Bank
    RAIFFEISEN        ("175"),      // Raiffeisen Bank
    SOCIETE_GENERALE  ("184"),      // Societe Generale
    NEST              ("187"),      // Nest Bank
    DEUTSCHE          ("191"),      // Deutsche Bank Polska
    CREDIT_AGRICOLE   ("194"),      // Credit Agricole
    IDEA              ("195"),      // Idea Bank
    BNP               ("203"),      // BNP Paribas
    SANDANDER_CONSUMER("212"),      // Santander Consumer Bank
    DNB               ("219"),      // DNB Nord
    GETIN_NOBLE       ("248"),      // Getin Noble Bank
    ALIOR             ("249");      // Alior Bank
    // @formatter:on

    private final String code;

    Bank(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
