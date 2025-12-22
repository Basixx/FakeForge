# FakeForge

---

# Spis treści / Table of contents

<!-- TOC -->
* [FakeForge](#fakeforge)
* [Spis treści / Table of contents](#spis-treści--table-of-contents)
* [Polski](#polski)
  * [Opis](#opis)
  * [Wymagania](#wymagania)
  * [Konfiguracja](#konfiguracja)
  * [Uruchomienie](#uruchomienie)
    * [Opcja A: Docker Compose (zalecana)](#opcja-a-docker-compose-zalecana)
    * [Opcja B: Uruchomienie lokalne (bez Dockera)](#opcja-b-uruchomienie-lokalne-bez-dockera)
  * [Kompilacja](#kompilacja)
    * [Z testami](#z-testami)
    * [Bez testów](#bez-testów)
  * [Testy](#testy)
  * [Przegląd interfejsu API](#przegląd-interfejsu-api)
  * [Baza danych i migracje](#baza-danych-i-migracje)
  * [Rozwiązywanie problemów](#rozwiązywanie-problemów)
* [English](#english)
  * [Description](#description)
  * [Requirements](#requirements)
  * [Configuration](#configuration)
  * [Run](#run)
    * [Option A: Docker Compose (recommended)](#option-a-docker-compose-recommended)
    * [Option B: Local run (without Docker)](#option-b-local-run-without-docker)
  * [Build](#build)
    * [With tests](#with-tests)
    * [Without tests](#without-tests)
  * [Tests](#tests)
  * [API Overview](#api-overview)
  * [Database & Migrations](#database--migrations)
  * [Troubleshooting](#troubleshooting)
<!-- TOC -->

---

# Polski

**English version below.** ⬇

## Opis

FakeForge to aplikacja Spring Boot do generowania i zarządzania fałszywymi danymi osobowymi dla każdego użytkownika z
dziennymi limitami użytkowania. Zapewnia:

- Rejestrację użytkownika i uwierzytelnianie oparte na JWT
- Generowanie fałszywych danych osobowych z konfigurowalnymi parametrami (wiek, płeć)
- Dzienne limity dla każdego użytkownika i kontrolę administracyjną nad nimi
- Opcjonalną integrację z wysyłaniem i weryfikacją wiadomości e-mail
- Trwałość MySQL ze schematem zarządzanym przez Liquibase

Technologia: Java 21, Spring Boot 3, Spring Security (JWT), JPA/Hibernate, Liquibase, Thymeleaf (szablony
wiadomości e-mail), DataFaker, Spock (Groovy) do testów, Docker/Docker Compose.

## Wymagania

- JDK 21
    - Link do pobierania:
      https://www.oracle.com/java/technologies/downloads/#java21
- Gradle Wrapper (zapewniony)
- MySQL 8.x (jeśli uruchamiane lokalnie bez Dockera)
    - Link do pobierania:
      https://dev.mysql.com/downloads/mysql/
- Docker oraz Docker Compose (zalecane dla najłatwiejszego startu)
    - Link do instalacji:
      https://docs.docker.com/compose/install/

## Konfiguracja

Skopiuj szablon środowiska i wypełnij wartości:

1) Skopiuj plik `.env.example` do pliku `.env` w katalogu głównym projektu.
2) Ustaw zmienne zgodnie z potrzebami. Kluczowe:

- Baza danych:

  Dowolne wartości ustalone przez osobę uruchamiającą, pamiętając o nieupublicznianiu ich.
    - `MYSQL_DATABASE` - nazwa bazy danych
    - `MYSQL_ROOT_PASSWORD` - hasło użytkownika root
    - `MYSQL_USER` - nazwa użytkownika bazy danych
    - `MYSQL_PASSWORD` - hasło użytkownika bazy danych `MYSQL_USER`

- JWT:

  Poufny ciąg znaków używany do cyfrowego podpisywania i weryfikacji tokenów JWT. Polecany
  generator: https://jwtsecrets.com/

  **Ważne!** Klucz musi być przynajmniej 256-bitowy.
    - `JWT_SECRET` - klucz tajny

- Wysyłanie wiadomości e-mail:

  Funkcja wysyłki wiadomości email jest opcjonalna, można ją włączyć za pomocą poniższych zmiennych.

  Instrukcje dotyczące tworzenia konta email oraz pozyskania hasła dla aplikacji dla poczty Gmail:

  https://support.google.com/mail/answer/56256

  https://support.google.com/mail/answer/185833
    - `SEND_EMAIL` (true/false) - zmienna służąca do włączania/wyłączania funkcjonalności wysyłki wiadomości email
    - `FAKEFORGE_MAIL_USERNAME` - adres email, z którego aplikacja wysyła wiadomości email, konieczna w przypadku
      włączenia funkcjonalności, w innym przypadku, może zostać nieuzupełniona
    - `FAKEFORGE_MAIL_PASSWORD` - hasło konta email, z którego aplikacja wysyła wiadomości, konieczna w przypadku
      włączenia funkcjonalności, w innym przypadku, może zostać nieuzupełniona

- Weryfikacja adresu e-mail:

  Funkcja weryfikacji adresu e-mail jest opcjonalna, można ją włączyć za pomocą poniższych zmiennych.

  Instrukcja dotycząca pozyskania klucza API do weryfikacji adresu e-mail:

  Należy założyć konto na stronie https://emailverification.whoisxmlapi.com/api/signup, po poprawnej rejestracji należy
  przejść do zakładki `My products`, na górze strony widoczny jest wgenerowany klucz API, potrzebny do działania
  funkcji.

  **Ważne!** Darmowa wersja konta na stronie pozwala jednorazowo na 100 żądań sprawdzających poprawność adresu email.

    - `VERIFY_EMAIL` (true/false) - zmienna służąca do włączania/wyłączania funkcjonalności wysyłki wiadomości email
    - `MAIL_API_KEY` - klucz API pozyskany na stronie https://emailverification.whoisxmlapi.com/

Aplikacja odczytuje konfigurację z pliku `src/main/resources/application.yml` i zmiennych środowiskowych.

## Uruchomienie

**Ważne!** Wykonuj komendy w katalogu projektu.

### Opcja A: Docker Compose (zalecana)

1) Upewnij się, że `.env` jest skonfigurowany
2) Uruchom stos:

```bash
./gradlew clean build -x test
```

```bash
docker-compose up -d --build
```

Serwisy i porty:

- Aplikacja: http://localhost:8080 (wystawiony port debugowania 5005)
- Baza danych MySQL: localhost:3307 (wewnętrzny port kontenera 3306)

By wyłączyć kontenery, uruchom

```bash
docker-compose down
````

### Opcja B: Uruchomienie lokalne (bez Dockera)

1) Zapewnij działającą instancję MySQL i stwórz bazę danych (zgodnie z `MYSQL_DATABASE`)

   tutorial: https://dev.mysql.com/doc/mysql-getting-started/en/

   Możliwe jest połączenie Dockerowej bazy danych z lokalnie postawioną aplikacją.

    ```bash
    docker-compose up -d --build ff-db
    ```

2) Upewnij się, że `.env` jest skonfigurowany

3) Uruchom aplikację:

```bash
./gradlew bootRun
```

Liquibase zaaplikuje migracje podczas uruchamiania.

## Kompilacja

**Ważne!** Wykonuj komendy w katalogu projektu.

### Z testami

Uruchom

```bash
./gradlew clean build
```

### Bez testów

```bash
./gradlew clean build -x test
```

## Testy

**Ważne!** Wykonuj komendy w katalogu projektu.

Uruchom

```bash
./gradlew clean test
```

## Przegląd interfejsu API

Uwierzytelnianie wykorzystuje JWT. Uzyskaj token za pomocą `/login` i wyślij go jako `Authorization: Bearer <token>` dla
chronionych punktów końcowych.

Przykładowy schemat działania (zakładając aplikację na localhost:8080):

1) Zarejestruj użytkownika

    ```bash
    curl -X POST http://localhost:8080/users/register \
      -H "Content-Type: application/json" \
      -d '{
            "name":"John",
            "lastName":"Doe",
            "emailAddress":"john.doe@example.com",
            "password":"Str0ngP@ss"
          }'
    ```

2) Zaloguj się by uzyskać token JWT

    ```bash
    TOKEN=$(curl -s -X POST http://localhost:8080/login \
      -H "Content-Type: application/json" \
      -d '{"emailAddress":"john.doe@example.com","password":"Str0ngP@ss"}')
    echo $TOKEN
    ```

3) Chronione endpointy

- Obejrzyj wygenerowane dane użytkownika (USER role):

    ```bash
    curl -H "Authorization: Bearer $TOKEN" \
      "http://localhost:8080/users/persons?page=0&size=10"
    ```

- Wygeneruj dane użytkownika (USER role):

    ```bash
    curl -X POST -H "Authorization: Bearer $TOKEN" \
      "http://localhost:8080/users/persons?age=30&gender=MALE"
    ```

- Sprawdź swój dzienny limit (USER role):

    ```bash
    curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/users/limit
    ```

- Endpointy administracyjne:
    - List users (ADMIN role): `GET /users?page=0&size=10`
    - Edit user limit (ADMIN role): `PUT /users/{userId}/limit?dailyLimit=25`

## Baza danych i migracje

- Baza danych MySQL jest używana w środowisku produkcyjnym/wykonawczym. H2 jest dostępny do testów.
- Dzienniki zmian w Liquibase znajdują się w katalogu `src/main/resources/db/changelog` i są uruchamiane automatycznie
  podczas uruchamiania.

## Rozwiązywanie problemów

- Nie można połączyć się z bazą danych (Docker): upewnij się, że port 3307 jest wolny, a zmienne bazy danych `.env` są
  ustawione.
- Błędy JWT: sprawdź, czy `JWT_SECRET` nie jest pusty i czy jest spójny między restartami aplikacji podczas korzystania
  z utrwalonych danych.
- Wysyłanie/weryfikacja wiadomości e-mail: ustaw `SEND_EMAIL`/`VERIFY_EMAIL` na `false`, jeśli nie podasz danych
  uwierzytelniających/interfejsów API podczas
  lokalnego tworzenia.

---

# English

## Description

FakeForge is a Spring Boot application for generating and managing fake personal data per user with daily usage limits.
It provides:

- User registration and JWT-based authentication
- Generating fake persons with configurable parameters (age, gender)
- Per-user daily limits and admin control over limits
- Optional email sending and email verification integrations
- MySQL persistence with Liquibase-managed schema

Tech stack: Java 21, Spring Boot 3, Spring Security (JWT), JPA/Hibernate, Liquibase, Thymeleaf (email templates),
DataFaker, Spock (Groovy) for tests, Docker/Docker Compose.

## Requirements

- JDK 21
    - Link to download:
      https://www.oracle.com/java/technologies/downloads/#java21
- Gradle Wrapper (provided)
- MySQL 8.x (if running locally without Docker)
    - Link to download:
      https://dev.mysql.com/downloads/mysql/
- Docker and Docker Compose (recommended for the easiest start)
    - Link to installation:
      https://docs.docker.com/compose/install/

## Configuration

Copy the environment template and fill values:

1) Duplicate `.env.example` to `.env` in the project root
2) Set the variables as needed. Key ones:

- Database:

  Any values set by the person running it, remembering not to make them public.
    - `MYSQL_DATABASE` - database name
    - `MYSQL_ROOT_PASSWORD` - root user password
    - `MYSQL_USER` - database user name
    - `MYSQL_PASSWORD` - database user password

- JWT:

  A secret string used to digitally sign and verify JWT tokens. Recommended generator: https://jwtsecrets.com/

  **Important!** The secret must be at least 256-bit.
    - `JWT_SECRET` - secret key

- Email sending:

  The email sending feature is optional and can be enabled using the variables below.

  Instructions for creating an email account and getting a password for the Gmail app:

  https://support.google.com/mail/answer/56256

  https://support.google.com/mail/answer/185833
    - `SEND_EMAIL` (true/false) - a variable used to enable/disable the functionality of sending email messages
    - `FAKEFORGE_MAIL_USERNAME` - the email address from which the application sends emails. This is required if the
      functionality is enabled; otherwise, it can be left blank.
    - `FAKEFORGE_MAIL_PASSWORD` - the password of the email account from which the application sends messages, required
      if the functionality is enabled; otherwise, it can be left blank.

- Email verification:

  The email verification feature is optional and can be enabled using the variables below.

  Instructions for getting an API key for email verification:

  Create an account at https://emailverification.whoisxmlapi.com/api/signup. After successful registration, go to the
  `My Products` tab. At the top of the page, you will see the generated API key required for this feature to work.

  **Important!** The free version of the website account allows for 100 email address validation requests at a time.

    - `VERIFY_EMAIL` (true/false) - a variable used to enable/disable the functionality of sending email messages
    - `MAIL_API_KEY` - API key obtained from https://emailverification.whoisxmlapi.com/

The application reads configuration from `src/main/resources/application.yml` and environment variables.

## Run

**Important!** Run commands in the project root directory.

### Option A: Docker Compose (recommended)

1) Ensure `.env` is configured
2) Start the stack:

```bash
./gradlew clean build -x test
```

```bash
docker-compose up -d --build
```

Services and ports:

- App: http://localhost:8080 (debug port 5005 exposed)
- MySQL: localhost:3307 (container internal port 3306)

To stop the containers, run

```bash
docker-compose down
````

### Option B: Local run (without Docker)

1) Provide a running MySQL instance and create a database (matches `MYSQL_DATABASE`)

   tutorial: https://dev.mysql.com/doc/mysql-getting-started/en/

   It's possible to connect a Docker-based MySQL database to the local application.

    ```bash
     docker-compose up -d --build ff-db
    ``` 

2) Ensure `.env` is configured

3) Run the app:

```bash
./gradlew bootRun
```

Liquibase will apply migrations on startup.

## Build

**Important!** Run commands in the project root directory.

### With tests

run

```bash
./gradlew clean build
```

### Without tests

run

```bash
./gradlew clean build -x test
```

## Tests

**Important!** Run commands in the project root directory.

run

```bash
./gradlew clean test
```

## API Overview

Authentication uses JWT. Get a token via `/login` and send it as `Authorization: Bearer <token>` for protected
endpoints.

Example flow (assuming app on localhost:8080):

1) Register a user

    ```bash
    curl -X POST http://localhost:8080/users/register \
      -H "Content-Type: application/json" \
      -d '{
            "name":"John",
            "lastName":"Doe",
            "emailAddress":"john.doe@example.com",
            "password":"Str0ngP@ss"
          }'
    ```

2) Login to receive JWT

    ```bash
    TOKEN=$(curl -s -X POST http://localhost:8080/login \
      -H "Content-Type: application/json" \
      -d '{"emailAddress":"john.doe@example.com","password":"Str0ngP@ss"}')
    echo $TOKEN
    ```

3) Use protected endpoints

- Get persons (USER role):

    ```bash
    curl -H "Authorization: Bearer $TOKEN" \
      "http://localhost:8080/users/persons?page=0&size=10"
    ```

- Create a person (USER role):

    ```bash
    curl -X POST -H "Authorization: Bearer $TOKEN" \
      "http://localhost:8080/users/persons?age=30&gender=MALE"
    ```

- Get your daily limit (USER role):

    ```bash
    curl -H "Authorization: Bearer $TOKEN" http://localhost:8080/users/limit
    ```

- Admin endpoints:
    - List users (ADMIN role): `GET /users?page=0&size=10`
    - Edit user limit (ADMIN role): `PUT /users/{userId}/limit?dailyLimit=25`

## Database & Migrations

- MySQL is used in production/runtime. H2 is available for tests.
- Liquibase changelogs are under `src/main/resources/db/changelog` and run automatically on startup.

## Troubleshooting

- Cannot connect to DB (Docker): ensure port 3307 is free and `.env` DB vars are set
- JWT errors: verify `JWT_SECRET` is non-empty and consistent between app restarts when using persisted data
- Email sending/verification: set `SEND_EMAIL`/`VERIFY_EMAIL` to `false` if you do not provide credentials/APIs during
  local development
