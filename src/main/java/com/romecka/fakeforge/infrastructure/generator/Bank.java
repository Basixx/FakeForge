package com.romecka.fakeforge.infrastructure.generator;

import java.util.List;

public enum Bank {

    //    TODO fix bank generating
    // @formatter:off
    NBP               ("Narodowy Bank Polski",          "1010", List.of("1111")),
    PKO_BP            ("PKO BP",                        "1020", List.of("1111")),
    CITY              ("Citi Bank Hanldowy",            "1030", List.of("1111")),
    ING               ("ING Bank Śląski",               "1050", List.of("1111")),
    BPH               ("Bank BPH",                      "1060", List.of("1111")),
    SANTANDER         ("Santander Bank Polska",         "1090", List.of("1111")),
    BGK               ("Bank Gospodarstwa Krajowego",   "1130", List.of("1111")),
    MBANK             ("mBank",                         "1140", List.of("1111")),
    MILLENIUM         ("Bank Millennium",               "1160", List.of("1111")),
    PEKAO             ("Bank Pekao SA",                 "1240", List.of("1111")),
    HSBC              ("HSBC",                          "1280", List.of("1111")),
    POCZTOWY          ("Bank Pocztowy",                 "1320", List.of("1111")),
    ENVELO            ("Envelo Bank",                   "1320", List.of("1111")),
    EUROBANK          ("Eurobank",                      "1470", List.of("1111")),
    BOS               ("Bank Ochrony Środowiska",       "1540", List.of("1111")),
    MERCEDES          ("Mercedes Bank Polska",          "1580", List.of("1111")),
    SGB               ("SGB Bank",                      "1610", List.of("1111")),
    RBS               ("RBS Bank Polska",               "1670", List.of("1111")),
    PLUS              ("Plus Bank",                     "1680", List.of("1111")),
    RAIFFEISEN        ("Raiffeisen Bank",               "1750", List.of("1111")),
    SOCIETE_GENERALE  ("Societe Generale",              "1840", List.of("1111")),
    NEST              ("Nest Bank",                     "1870", List.of("1111")),
    DEUTSCHE          ("Deutsche Bank Polska",          "1910", List.of("1111")),
    BPS               ("Bank Polskiej Spółdzielczości", "1930", List.of("1111")),
    CREDIT_AGRICOLE   ("Credit Agricole",               "1940", List.of("1111")),
    IDEA              ("Idea Bank",                     "1950", List.of("1111")),
    BNP               ("BNP Paribas",                   "2030", List.of("1111")),
    FCE               ("FCE Bank Polska",               "2070", List.of("1111")),
    SANDANDER_CONSUMER("Santander Consumer Bank",       "2120", List.of("1111")),
    VOLKSWAGEN        ("Volkswagen Bank",               "2130", List.of("1111")),
    FIAT              ("Fiat Bank Polska",              "2140", List.of("1111")),
    TOYOTA            ("Toyota Bank",                   "2160", List.of("1111")),
    DNB               ("DNB Nord",                      "2190", List.of("1111")),
    GETIN_NOBLE       ("Getin Noble Bank",              "2480", List.of("1111")),
    ALIOR             ("Alior Bank",                    "1060", List.of("0021")),
    T_MOBILE          ("T-Mobile Usługi Bankowe",       "2490", List.of("1111"));
    // @formatter:on

    private final String bankName;

    private final String code;

    private final List<String> sortCodes;

    Bank(String bankName,
         String code,
         List<String> sortCodes) {
        this.bankName = bankName;
        this.code = code;
        this.sortCodes = sortCodes;
    }

    public String bankName() {
        return bankName;
    }

    public String code() {
        return code;
    }

    public List<String> sortCodes() {
        return sortCodes;
    }
}
