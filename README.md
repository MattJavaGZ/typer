# 🔐 Konfiguracja API Key

## application.yml

```yaml
app:
  api:
    url: https://api.football-data.org/v4/
    key: ${API_KEY}  # ← przeczytana ze zmiennej środowiskowej
```

# 🔐 Konfiguracja DATABASE

## application.prod.yml

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/typer
    username: ${DATABASE_USERNAME}  # ← przeczytana ze zmiennej środowiskowej
    password: ${DATABASE_KEY}       # ← przeczytana ze zmiennej środowiskowej
```


