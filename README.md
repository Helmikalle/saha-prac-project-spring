# saha-prac-project-spring

To build and run the project run from root `mvn install` and to get the server started `mvn spring-boot:run`

The default local profile is `h2`, which starts an in-memory H2 database on port `8081`.

- API base URL: `http://localhost:8081`
- H2 console: `http://localhost:8081/h2-console`
- H2 JDBC URL: `jdbc:h2:mem:saha`
- Sample local content is seeded for `/text`, `/property/sauna1`, and `/property/venevaja1`.

To run against the old PostgreSQL setup instead, start with the `postgres` profile:

`mvn spring-boot:run -Dspring-boot.run.profiles=postgres`

The `postgres` profile reads database settings from environment variables:

- `SAHA_POSTGRES_URL`
- `SAHA_POSTGRES_USERNAME`
- `SAHA_POSTGRES_PASSWORD`

API examples are documented in `docs/api-examples.md`.
