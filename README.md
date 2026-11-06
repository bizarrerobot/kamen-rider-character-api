# Kamen Rider Character Gallery

A web app and REST API for storing, viewing, searching, adding, editing, and deleting Kamen Rider characters. The web pages are built on the server with Spring Boot and FreeMarker, and the same data is also available as JSON through a REST API. Built with Java 25, Spring Boot, Spring Data JPA, FreeMarker, and PostgreSQL.

## Technologies

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- FreeMarker (server-side page templates)
- PostgreSQL (Neon)
- Bean Validation
- Docker
- Render

## Live Site

**Home page (gallery):** https://kamen-rider-character-api.onrender.com/

**API endpoint (GET all characters):** https://kamen-rider-character-api.onrender.com/api/characters

Note: the free Render service sleeps when it is idle, so the first request may take about a minute to wake up.

## Web Pages

| Route | Description |
|---|---|
| GET `/` | Gallery of all characters as cards, with search by name and filter by universe (both can be combined) |
| GET `/characters/{id}` | Details page for one character, with Edit and Delete buttons |
| GET `/characters/new` | Form to add a character |
| POST `/characters` | Saves a new character, then redirects to the gallery |
| GET `/characters/{id}/edit` | The same form, pre-filled with the character's current values |
| POST `/characters/{id}` | Saves changes to the existing character, then redirects to its details page |
| POST `/characters/{id}/delete` | Deletes a character after a confirmation prompt, then redirects to the gallery |
| GET `/about` | About page, including how the app is built |

An unknown character ID shows a friendly "Character not found" page with a 404 status. Success messages appear once after a character is added, updated, or removed.

## Character Attributes

| Attribute | Type | Rules |
|---|---|---|
| `characterId` | long | Unique, auto-generated |
| `name` | String | Required (not null, not blank) |
| `description` | String | Required (not null, not blank) |
| `universe` | String | The category used for filtering (for example Heisei or Reiwa) |
| `riderType` | String | The type of rider (for example Main Rider) |
| `imageUrl` | String | Optional link or path to an image. A placeholder is shown when it is empty |

## API Endpoints

All API endpoints use the `/api/characters` base path.

| Method | Endpoint | Description | Success status |
|---|---|---|---|
| GET | `/api/characters` | Retrieve all characters | 200 OK |
| GET | `/api/characters/{id}` | Retrieve a character by ID | 200 OK |
| POST | `/api/characters` | Create a character | 201 Created |
| PUT | `/api/characters/{id}` | Update a character | 200 OK |
| DELETE | `/api/characters/{id}` | Delete a character | 204 No Content |
| GET | `/api/characters?universe=Heisei` | Filter by universe | 200 OK |
| GET | `/api/characters?name=Kuuga` | Search names partially, ignoring case | 200 OK |

A nonexistent character ID returns `404 Not Found`. Requests with a missing or blank name or description return `400 Bad Request`.

## Character JSON Example

Use this JSON body when creating (POST) or updating (PUT) a character:

```json
{
  "name": "Kamen Rider Kuuga",
  "description": "A warrior who protects people from the Gurongi.",
  "universe": "Heisei",
  "riderType": "Main Rider",
  "imageUrl": "https://example.com/kuuga.png"
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

6. Visit http://localhost:8080/ for the gallery or http://localhost:8080/api/characters for the API.

Never commit database passwords or other secrets to the repository.

## Architecture

The application separates responsibilities into layers:

- **Model:** `RiderCharacter` defines the character entity and validation rules.
- **Repository:** `RiderCharacterRepository` performs database operations.
- **Service:** `RiderCharacterService` implements application logic, including search and update.
- **REST controller:** `RiderCharacterController` handles JSON requests at `/api/characters`.
- **Page controller:** `CharacterPageController` handles the web pages and renders FreeMarker templates.
- **Templates:** `layout.ftlh` holds the shared head and navigation bar used by every page.

Both controllers call the same service, and neither calls the repository directly. A character added through the web form is stored in the same database table and appears in the API, and the other way around.

The database table is named `characters`. Character data is persisted in PostgreSQL.

## Deployment

The application uses a multi-stage Dockerfile to build and run the Spring Boot JAR. Render hosts the application, while Neon provides the PostgreSQL database. Database credentials are configured as environment variables rather than stored in source code.