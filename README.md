# Kamen Rider Character API

A RESTful API for storing, retrieving, updating, searching, and deleting Kamen Rider character information. Built with Java 25, Spring Boot, Spring Data JPA, and PostgreSQL.

## Technologies

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- PostgreSQL (Neon)
- Bean Validation
- Docker
- Render

## Deployed API

**Base URL:** https://kamen-rider-character-api.onrender.com

**Deployed API Endpoint (GET all characters):** https://kamen-rider-character-api.onrender.com/api/characters

The base URL identifies the deployed application. To retrieve character data, visit the API endpoint above. All API endpoints use the `/api/characters` base path.

The service may take a little time to respond after being inactive because the free hosting instance can sleep.

## API Endpoints

All endpoints use the `/api/characters` base path.

| Method | Endpoint | Description | Success status |
|---|---|---|---|
| GET | `/api/characters` | Retrieve all characters | 200 OK |
| GET | `/api/characters/{id}` | Retrieve a character by ID | 200 OK |
| POST | `/api/characters` | Create a character | 201 Created |
| PUT | `/api/characters/{id}` | Update a character | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a character | 204 No Content |
| GET | `/api/characters?universe=Kamen%20Rider` | Filter by universe | 200 OK |
| GET | `/api/characters?name=Kuuga` | Search names partially, ignoring case | 200 OK |

A nonexistent character ID returns `404 Not Found`. Requests with a missing or blank name or description return `400 Bad Request`.

## Character JSON Example

Use this JSON body when creating or updating a character:

```json
{
  "name": "Kamen Rider Kuuga",
  "description": "A warrior who protects people from the Gurongi.",
  "universe": "Kamen Rider",
  "riderType": "Mighty Form"
}
```

The API generates `characterId` automatically.

## Running Locally

1. Install Java 25.
2. Clone this repository.
3. Configure the following environment variables:
   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
4. Set the JDBC URL to your PostgreSQL database, including `?sslmode=require`.
5. Start the application in PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

6. Visit http://localhost:8080/api/characters.

Never commit database passwords or other secrets to the repository.

## Architecture

The application separates responsibilities into four layers:

- **Model:** `RiderCharacter` defines the character entity and validation rules.
- **Repository:** `RiderCharacterRepository` performs database operations.
- **Service:** `RiderCharacterService` implements application logic.
- **Controller:** `RiderCharacterController` handles HTTP requests and responses.

The database table is named `characters`. Character data is persisted in PostgreSQL.

## Deployment

The application uses a multi-stage Dockerfile to build and run the Spring Boot JAR. Render hosts the application, while Neon provides the PostgreSQL database. Database credentials are configured as environment variables rather than stored in source code.
