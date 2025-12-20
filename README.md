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
    - link do instalacji: https://docs.docker.com/compose/install/

## Konfiguracja

Skopiuj szablon środowiska i wypełnij wartości:

1) Skopiuj plik `.env.example` do pliku `.env` w katalogu głównym projektu.
2) Ustaw zmienne zgodnie z potrzebami. Kluczowe:

- Baza danych:

  Dowolne wartości ustalone przez osobę uruchamiającą, pamiętając o nieupublicznianiu ich.
    - `MYSQL_DATABASE` - nazwa bazy danych
    - `MYSQL_ROOT_PASSWORD` - hasło użytkownika root
    - `DB_USER` - nazwa użytkownika bazy danych
    - `DB_PASSWORD` - hasło użytkownika bazy danych `DB_USER`

- JWT:

  Poufny ciąg znaków używany do cyfrowego podpisywania i weryfikacji tokenów JWT, polecany
  generator: https://jwtsecrets.com/
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

### Opcja A: Docker Compose (zalecana)

1) Upewnij się, że `.env` jest skonfigurowany
2) Uruchom stos:

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
2) Wyeksportuj wymagane zmienne środowiskowe, tak by Spring mógł się do nich podłączyć
   -
   `SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/<YOUR_DB>?serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true`
    - `MYSQL_USER=<your_db_user>`
    - `MYSQL_PASSWORD=<your_db_password>`
    - `JWT_SECRET=<your_secret>`
    - Opcjonalnie: wysyłka i weryfikacja adresu email jak w Konfiguracji
3) Uruchom aplikację

```bash
./gradlew bootRun
```

Liquibase zaaplikuje migracje podczas uruchamiania.

## Kompilacja

### Z testami

Uruchom

```bash
./gradlew build
```

### Bez testów

```bash
./gradlew build -x test
```

## Testy

Uruchom

```bash
./gradlew test
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
- Gradle Wrapper (provided)
- MySQL 8.x (if running locally without Docker)
- Docker and Docker Compose (recommended for the easiest start)

## Configuration

1) Copy the environment template and fill values:

- Duplicate `.env.example` to `.env` in the project root
- Set the variables as needed. Key ones:
    - Database: `MYSQL_DATABASE`, `MYSQL_ROOT_PASSWORD`, `DB_USER`, `DB_PASSWORD`
    - JWT: `JWT_SECRET`
    - Email sending: `SEND_EMAIL` (true/false), `FAKEFORGE_MAIL_USERNAME`, `FAKEFORGE_MAIL_PASSWORD`
    - Email verification: `VERIFY_EMAIL` (true/false), `MAIL_API_KEY`
    - CORS (optional, defaults exist): `APP_CORS_ALLOWED_ORIGINS`, `APP_CORS_ALLOWED_METHODS`,
      `APP_CORS_ALLOWED_HEADERS`, `APP_CORS_EXPOSED_HEADERS`, `APP_CORS_ALLOW_CREDENTIALS`, `APP_CORS_MAX_AGE`

The application reads configuration from `src/main/resources/application.yml` and environment variables.

## Run

### Option A: Docker Compose (recommended)

1) Ensure `.env` is configured
2) Start the stack:

```bash
docker compose up --build
```

Services and ports:

- App: http://localhost:8080 (debug port 5005 exposed)
- MySQL: localhost:3307 (container internal port 3306)

### Option B: Local run (without Docker)

1) Provide a running MySQL instance and create a database (matches `MYSQL_DATABASE`)
2) Export required environment variables so Spring can connect:
   -
   `SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/<YOUR_DB> serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true`
    - `MYSQL_USER=<your_db_user>`
    - `MYSQL_PASSWORD=<your_db_password>`
    - `JWT_SECRET=<your_secret>`
    - Optionally: mail and verification variables as in Configuration
3) Run the app:

```bash
./gradlew bootRun
```

Liquibase will apply migrations on startup.

## Build

### With tests

run

```bash
./gradlew build
```

### Without tests

run

```bash
./gradlew build -x test
```

## Tests

run

```bash
./gradlew test
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

If OpenAPI UI is enabled, it will be available at:

http://localhost:8080/swagger-ui/index.html

## Database & Migrations

- MySQL is used in production/runtime. H2 is available for tests.
- Liquibase changelogs are under `src/main/resources/db/changelog` and run automatically on startup.

## Troubleshooting

- Cannot connect to DB (Docker): ensure port 3307 is free and `.env` DB vars are set
- JWT errors: verify `JWT_SECRET` is non-empty and consistent between app restarts when using persisted data
- Email sending/verification: set `SEND_EMAIL`/`VERIFY_EMAIL` to `false` if you do not provide credentials/APIs during
  local development
