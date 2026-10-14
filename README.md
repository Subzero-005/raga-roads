# Raga Roads – Artist Allocation Platform

A Spring Boot REST API that books musicians for concerts. A Band Manager asks for an artist to be allocated to a concert, and the platform checks the request against a set of business rules using one of two allocation strategies.

## Tech stack

Java 17 · Spring Boot 3.2 · Spring Security + JWT · Spring JDBC · PostgreSQL · springdoc OpenAPI (Swagger UI) · JUnit 5 · Docker Compose

## How allocation works

Each concert names the strategy it uses:

| Strategy | Rules checked, in order |
|---|---|
| `BALANCED` | role → location (same state) → availability → budget |
| `AVAILABILITY` | role → availability → budget |

Rules are separate classes (`strategies/rules`), and a factory picks the strategy, so adding a new one doesn't touch the booking service. Every request is stored as a booking, either `Confirmed` or `Rejected` with the reason.

## Running it

**In-memory (no database needed):**

```bash
mvn spring-boot:run
```

**With PostgreSQL (Docker):**

```bash
docker compose up --build
```

The API runs on `http://localhost:8080`, and Swagger UI is at `http://localhost:8080/swagger-ui.html`.

## Demo users

| Username | Password | Role |
|---|---|---|
| `admin` | `admin123` | Administrator |
| `manager` | `manager123` | Band Manager (the only role allowed to book) |
| `artist1` | `artist123` | Artist |

## API

| Method | Endpoint | Access |
|---|---|---|
| POST | `/auth/login` | public |
| POST | `/auth/validate` | public |
| GET | `/artists`, `/artists/{id}` | any logged-in user |
| GET | `/concerts`, `/concerts/{id}` | any logged-in user |
| POST | `/bookings/allocate` | Band Manager |
| GET | `/bookings` | any logged-in user |

```bash
TOKEN=$(curl -s -X POST localhost:8080/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"manager","password":"manager123"}' | jq -r .token)

curl -X POST localhost:8080/bookings/allocate \
  -H "Authorization: Bearer $TOKEN" -H 'Content-Type: application/json' \
  -d '{"concertId":1,"artistId":1}'
```

## Tests

```bash
mvn test
```

28 unit tests cover each validation rule and both strategies.

## Notes

The demo users and JWT secret are hard-coded for local use. Set `APP_JWT_SECRET` and replace the user list before deploying anywhere real.

See also: [raga-roads-microservices](https://github.com/Subzero-005/raga-roads-microservices), the same domain split into separate services.
