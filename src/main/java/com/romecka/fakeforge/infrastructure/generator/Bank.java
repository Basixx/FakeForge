package com.romecka.fakeforge.infrastructure.generator;

public enum Bank {

    // @formatter:off
    NBP               ("Narodowy Bank Polski",          "101"),
    PKO_BP            ("PKO BP",                        "102"),
    CITY              ("Citi Bank Hanldowy",            "103"),
    ING               ("ING Bank Śląski",               "105"),
    BPH               ("Bank BPH",                      "106"),
    SANTANDER         ("Santander Bank Polska",         "109"),
    BGK               ("Bank Gospodarstwa Krajowego",   "113"),
    MBANK             ("mBank",                         "114"),
    MILLENIUM         ("Bank Millennium",               "116"),
    PEKAO             ("Bank Pekao SA",                 "124"),
    HSBC              ("HSBC",                          "128"),
    POCZTOWY          ("Bank Pocztowy",                 "132"),
    ENVELO            ("Envelo Bank",                   "132"),
    EUROBANK          ("Eurobank",                      "147"),
    BOS               ("Bank Ochrony Środowiska",       "154"),
    MERCEDES          ("Mercedes Bank Polska",          "158"),
    SGB               ("SGB Bank",                      "161"),
    RBS               ("RBS Bank Polska",               "167"),
    PLUS              ("Plus Bank",                     "168"),
    RAIFFEISEN        ("Raiffeisen Bank",               "175"),
    SOCIETE_GENERALE  ("Societe Generale",              "184"),
    NEST              ("Nest Bank",                     "187"),
    DEUTSCHE          ("Deutsche Bank Polska",          "191"),
    BPS               ("Bank Polskiej Spółdzielczości", "193"),
    CREDIT_AGRICOLE   ("Credit Agricole",               "194"),
    IDEA              ("Idea Bank",                     "195"),
    BNP               ("BNP Paribas",                   "203"),
    FCE               ("FCE Bank Polska",               "207"),
    SANDANDER_CONSUMER("Santander Consumer Bank",       "212"),
    VOLKSWAGEN        ("Volkswagen Bank",               "213"),
    FIAT              ("Fiat Bank Polska",              "214"),
    TOYOTA            ("Toyota Bank",                   "216"),
    DNB               ("DNB Nord",                      "219"),
    GETIN_NOBLE       ("Getin Noble Bank",              "248"),
    ALIOR             ("Alior Bank",                    "249"),
    T_MOBILE          ("T-Mobile Usługi Bankowe",       "249");
    // @formatter:on

    private final String bankName;

    private final String code;

    Bank(String bankName, String code) {
        this.bankName = bankName;
        this.code = code;
    }

    public String bankName() {
        return bankName;
    }

    public String code() {
        return code;
    }

}
