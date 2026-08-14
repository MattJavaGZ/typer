# ⚽ Typer

## 📋 O Projekcie

**Typer** to aplikacja webowa wspierająca analizę meczów piłkarskich pod zakłady bukmacherskie poprzez porównywanie statystyk drużyn, 
            analizę ich ostatnich spotkań oraz bezpośrednich starć H2H. Dostępne są również tabele rozgrywek.

## ✨ Funkcjonalności

- **Porównanie dwóch drużyn**
    - wykaz ostatnich meczów obu drużyn wraz z wynikami,
    - statystyki drużyn z możliwością wyboru zakresu od 1 do 10 ostatnich meczów,
    - liczba zwycięstw, remisów i porażek,
    - BTTS (obie drużyny strzeliły gola),
    - czyste konta,
    - gole strzelone i stracone,
    - średnia liczba goli strzelonych, straconych oraz łączna średnia goli na mecz,
    - bezpośrednie starcia H2H wraz z wynikami,
    - bilans bezpośrednich spotkań oraz zdobytych bramek.

- **Statystyki pojedynczej drużyny**
    - wykaz ostatnich meczów wraz z wynikami,
    - szczegółowe statystyki drużyny.

- **Tabele ligowe**
    - aktualne tabele rozgrywek.

- **Automatyczna aktualizacja danych**
    - pobieranie wyników meczów i tabel ligowych z API po uruchomieniu aplikacji,
    - cykliczna aktualizacja danych w godzinach nocnych.

## 🛠️ Technologie

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


