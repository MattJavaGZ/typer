# ⚽ Typer

## 📋 O Projekcie

**Typer** to aplikacja webowa do analizy wyników piłkarskich i tworzenia porównań między drużynami.
Integruje się z API [football-data.org](https://www.football-data.org/) aby pobierać dane o meczach, drużynach i sezonach.

- **Język**: Java 21
- **Framework**: Spring Boot 4.0.3
- **Baza danych**: MySQL (produkcja) / H2 (development)
- **Szablony**: Thymeleaf
- **ORM**: JPA/Hibernate
- **Migracje**: Liquibase


# 🔐 Konfiguracja API Key

## application.yml

```yaml
app:
  api:
    url: https://api.football-data.org/v4/
    key: ${API_KEY}  # ← przeczytana ze zmiennej środowiskowej
```

# 🔐 Konfiguracja DATABASE

## application-prod.yml

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/typer
    username: ${DATABASE_USERNAME}  # ← przeczytana ze zmiennej środowiskowej
    password: ${DATABASE_PASSWORD}       # ← przeczytana ze zmiennej środowiskowej
```


