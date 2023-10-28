# Cryptoviewer

Simple cryptocurrency viewer web project using Coinmarketcap API. This project is built using HTML, CSS, Bootstrap, JS, JQuery, and Spring Boot.

This project shows information regarding crypto such as name of the crypto, price, market cap, and 24h volume.

Price, market cap, and 24h volume are displayed in IDR.

## Backend
Add `application.properties` to `src/main/resources`.

Example:

```
apiKey = <YOUR_API_KEY>

spring.jpa.database = POSTGRESQL
spring.datasource.url = <POSTGRE_URL>
spring.datasource.username = <POSTGRE_USERNAME>
spring.datasource.password= <POSTGRE_PASSWORD>
spring.jpa.hibernate.ddl-auto = update
```
