# Saha Project Context

## Current State

This repository is the Spring Boot backend for a practice project about an old saw complex. The intended frontend is a separate React project:

`C:\Users\HelmiKalle\Projektit\saha-prac-project-react`

The backend currently exposes REST endpoints for property/section content, image records, and generic text content. H2 is now the default local/dev profile so the project can be revived without recreating the old PostgreSQL database setup. PostgreSQL is preserved as an explicit `postgres` profile.

Current verification status:

- `java` is available on PATH in the shell used by Codex.
- `JAVA_HOME` is set to `C:\Program Files\Java\jdk-26.0.1`.
- H2 local profile has been configured.
- `.\mvnw.cmd test` passes with the H2 profile: 6 tests run, 0 failures, 0 errors.
- `.\mvnw.cmd spring-boot:run` starts successfully with the H2 profile.
- `GET http://localhost:8081/property` returns `200` with an empty JSON array.
- `GET http://localhost:8081/h2-console` returns `200`.
- H2 local startup seeds sample `/text`, `/property/sauna1`, and `/property/venevaja1` content when the database is empty.

## Product Goal

Create a small showcase website for an old saw complex, using:

- A Spring Boot backend for REST APIs and persistence practice.
- A React frontend for the user-facing UI.
- Text content stored in the backend/database.
- Image metadata stored in the backend/database.
- Actual images hosted externally for now, likely Google Photos because it is free and already contains the photos.

The intended user is someone who wants to learn more about the saw complex. The project is also a learning/practice project for Spring Boot backend development.

## Architecture Notes

Backend repository:

`C:\Users\HelmiKalle\Projektit\saha-prac-project-spring`

Frontend repository:

`C:\Users\HelmiKalle\Projektit\saha-prac-project-react`

Backend package root:

`src/main/java/com/own/prac/saha`

Main layers:

- `controller`: REST entry points.
- `service`: thin service layer.
- `repository`: Spring Data repositories.
- `entity`: JPA persistence entities.
- `utils`: CORS filter.

Known backend endpoints:

- `GET /property`
- `GET /property/{propertyId}`
- `POST /property`
- `DELETE /property/{propertyId}`
- `POST /image`
- `DELETE /image/{id}`
- `GET /text`
- `POST /text`

Known frontend API calls:

- `GET http://localhost:8081/text`
- `GET http://localhost:8081/property/sauna1`
- `GET http://localhost:8081/property/venevaja1`

Current persistence model:

- `PropertyContent`: section/property content with `propertyId`, `paragraph`, and an eager image list.
- `ImgContent`: image record with `name`, `imageUrl`, transitional `sahaPhotoURL`, `thumbnailUrl`, `caption`, `altText`, `sortOrder`, `source`, and `propertyId`.
- `TextContent`: generic text block with `paragraph` and `type`.

Current frontend response expectations:

- `/text` returns an array of text records with `id`, `paragraph`, and `type`.
- `/property/{propertyId}` returns one property object with `paragraph` and `imageList`.
- `imageList` items now use preferred `imageUrl` in the frontend, with `sahaPhotoURL` fallback for compatibility.
- The frontend currently hardcodes property IDs `sauna1` and `venevaja1`.
- Intro and property API flows now expose loading, empty, and error states.
- API-driven intro and property rendering now has focused Jest coverage.

## Decisions

- Use H2 for local/dev for now.
- Keep PostgreSQL as a possible later/published database option.
- Store image metadata and external image URLs in the backend.
- Keep image hosting generic; Google Photos can be the current host, but the backend should not become tightly coupled to Google Photos.
- Understand the current backend and frontend contract before major refactoring.
- Keep backend and frontend in separate repositories for now. The existing split is useful for learning API contracts and avoids a repo migration while the main work is still backend/frontend cleanup.
- Use this file as the lightweight project memory and board.
- Do not add service interfaces just because a class is a service. Prefer concrete service dependencies until there is meaningful variation, an external boundary, or a ports-and-adapters design reason.
- PostgreSQL credentials must come from environment variables, not committed property files.

## Backlog

Workflow note: before starting the last remaining Todo backlog ticket, pause and notify the user so there can be a small project review and next-step planning session.

Workflow note: keep backend and frontend tasks separate. This is both a revival project and a mentoring project, so meaningful corrections should include a short explanation of what was wrong, what changed, and why the new approach is better.

Workflow note: when backend API or model changes create frontend follow-up work, update the Frontend Todo list in the same session. Compatibility fields can be temporary bridges, but the backlog should track the robust end state.

Workflow note: after meaningful tickets, check whether there is a reusable architecture lesson for `C:\Users\HelmiKalle\Projektit\ai-skills\skills\architecture-guidelines`. Add only durable, cross-project, decision-shaping lessons. Keep project-specific facts in this file instead.

Priority scale: P5 is most important, P1 is lowest priority.

### Backend Todo

- P4 - Replace field injection with constructor injection in controllers/services.
- P4 - Review CORS configuration and replace the global wildcard filter with Spring configuration that is explicit per environment.
- P3 - Introduce DTOs or response models before larger API changes so entity persistence shape is not the long-term public API contract.
- P3 - Review JPA relationship between `PropertyContent` and `ImgContent`; consider a clearer foreign-key relationship or documented business-key join constraints for `propertyId`.

### Frontend Todo

- P3 - Extract duplicated property slider logic from `Sauna` and `Venevaja`.
- P3 - Create a small API layer for backend calls instead of calling `apiClient` directly inside UI components as flows grow.
- P3 - Make property sections data-driven instead of hardcoding separate `Sauna` and `Venevaja` fetching components.
- P3 - Plan dependency modernization/security cleanup for the old React Scripts and Axios stack.
- P2 - Review UI layout and interaction patterns for mobile/desktop polish.

### In Progress

- Paused between tickets. Next recommended backend ticket is constructor injection cleanup.

### Done

- Loaded project-onboarding-mapper skill instructions.
- Completed condensed discovery interview.
- Scanned backend repository structure.
- Identified current backend endpoints, layers, entities, repositories, and runtime config.
- Decided to use H2 for local/dev.
- Decided to store image metadata plus external URLs.
- Created this project context file.
- Configured H2 local development profile.
- Confirmed `JAVA_HOME` is visible after VS Code restart.
- Ran `.\mvnw.cmd test` successfully with 1 test passing.
- Ran the backend locally with H2 and confirmed startup on port `8081`.
- Reprioritized backlog so test coverage is the next task after confirming the application runs and baseline tests pass.
- Added focused H2-backed API integration tests for current REST behavior.
- Scanned the React frontend repository.
- Mapped the frontend's current backend endpoint and response-shape expectations.
- Decided to treat the project as a mentoring project with brief explanations for meaningful code corrections.
- Split backlog into separate Backend and Frontend sections.
- Decided to keep frontend backlog updated as backend API/model work creates frontend follow-up tasks.
- Documented current backend API examples.
- Improved image metadata model with generic fields while preserving `sahaPhotoURL` compatibility.
- Added H2-only seed/sample content for local development.
- Decided to keep frontend and backend in separate repositories for now.
- Created frontend project context and agent files in the React repository.
- Replaced hardcoded frontend API base URLs with a shared configurable Axios client.
- Updated frontend image usage from `sahaPhotoURL` to generic `imageUrl`.
- Fixed small frontend correctness issues in intro components.
- Updated `project-onboarding-mapper` with explicit frontend architecture, UI flow, API contract, and frontend test discovery sections.
- Created `react-test-generator` skill for API-driven React component tests and missing frontend test coverage.
- Added frontend loading, empty, and error states for intro and property API content.
- Added focused frontend Jest tests for API-driven intro/property rendering and image URL compatibility.
- Improved REST not-found/delete behavior for property and image endpoints.

## Open Questions

- Should H2 data be seeded through SQL files, Java bootstrapping, or manual API calls?
- Which Java version should be used for the revived baseline: Java 8, Java 11, Java 17, or a later upgrade path?
- If publishing later, should image hosting remain Google Photos or move to static assets/object storage?

## Session Log

### 2026-06-06

- Re-entered old Spring Boot backend project.
- Established project purpose: saw-complex showcase backend for a separate React frontend.
- Found Maven Spring Boot project using Spring Boot `2.1.1.RELEASE` and Java 8 target.
- Found current PostgreSQL config in `application.properties`.
- Found H2 dependency present but not active.
- Verification blocked because `java` is not on PATH and `JAVA_HOME` is unset.
- Agreed to use H2 for local development and keep images externally hosted.
- Created `PROJECT_CONTEXT.md` as project memory and lightweight backlog.
- Configured `application.properties` to use the `h2` profile by default.
- Added `application-h2.properties` for local H2 runtime.
- Added `application-postgres.properties` to preserve the old PostgreSQL setup as an explicit profile.
- Retried `.\mvnw.cmd test`; it still cannot start until `JAVA_HOME` is set.
- After VS Code restart, confirmed `java -version` reports OpenJDK `26.0.1`.
- Confirmed `JAVA_HOME` is `C:\Program Files\Java\jdk-26.0.1`.
- Ran `.\mvnw.cmd test` successfully: 1 test run, 0 failures, 0 errors, build success.
- Ran `.\mvnw.cmd spring-boot:run`; Tomcat started on port `8081`.
- Confirmed `GET /property` returns `200` and `[]` against the empty H2 database.
- Confirmed `/h2-console` returns `200`.
- Stopped the temporary Spring Boot process after verification.
- Added project rule to prioritize test improvements after the runtime and baseline test checks are green.
- Added `SahaApiIntegrationTests` covering empty property list, text creation/listing, and property image lookup by `propertyId`.
- Ran `.\mvnw.cmd test` successfully: 4 tests run, 0 failures, 0 errors, build success.
- Scanned `C:\Users\HelmiKalle\Projektit\saha-prac-project-react`.
- Found frontend dependencies include React `16.7.0`, React Scripts `2.1.2`, Axios `0.18.0`, React Tabs, and React Scrollable Anchor.
- Found frontend API calls to `/text`, `/property/sauna1`, and `/property/venevaja1`.
- Found frontend expects image URLs in `sahaPhotoURL`.
- Found small frontend cleanup candidates: typo in `Introduction` class name, invalid `<di>` tag in `IntroContent`, duplicated slider logic in `Sauna` and `Venevaja`, and hardcoded API base URL.
- Added project mentoring expectation: explain Java/Spring/React/UI corrections so the user can learn the better practice.
- Split backlog into Backend Todo and Frontend Todo to keep UI tasks separate from backend tasks.
- Added `docs/api-examples.md` documenting current REST endpoints, example requests/responses, frontend compatibility notes, and follow-up REST behavior improvements.
- Added project rule to update frontend tickets when backend API/model changes require frontend follow-up work.
- Added image metadata fields: `imageUrl`, `thumbnailUrl`, `caption`, `altText`, `sortOrder`, and `source`.
- Kept `sahaPhotoURL` as a compatibility field for the existing React frontend.
- Added fallback getters so `imageUrl` and `sahaPhotoURL` serialize with the available URL during the transition.
- Updated API integration tests to cover generic image metadata and compatibility fallback behavior.
- Updated `docs/api-examples.md` to show `imageUrl` as the preferred field and `sahaPhotoURL` as temporary compatibility.
- Added `DevDataConfiguration` for H2-only startup seed data.
- Seeded sample text plus `sauna1` and `venevaja1` property content for the current React frontend.
- Added `SahaDevDataTests` to verify the H2 profile seeds frontend development content.
- Updated README and API docs with local seed-data notes.
- Fixed seed initialization to be idempotent per expected record after tests exposed that all-or-nothing seed skipping could miss text content when partial data already existed.
- Ran `.\mvnw.cmd test` successfully: 6 tests run, 0 failures, 0 errors, build success.
- Decided to keep `saha-prac-project-spring` and `saha-prac-project-react` as separate repositories for now.
- Reasoning: the split helps practice API boundaries, both repos already exist, and current next work is frontend cleanup rather than repo migration.
- Added `AGENTS.md` and `PROJECT_CONTEXT.md` to `C:\Users\HelmiKalle\Projektit\saha-prac-project-react` so frontend work has its own memory and backlog.
- Added `src/api/client.js` in the React repository and updated frontend API calls to use `REACT_APP_API_BASE_URL` with default `http://localhost:8081`.
- Ran frontend tests successfully after installing dependencies: 1 test passed.
- Updated React property components to render `imageUrl`, with `sahaPhotoURL` fallback for compatibility.
- Renamed React `Intoduction` class to `Introduction` and replaced invalid `IntroContent` `<di>` markup with `<div>`.
- Updated `project-onboarding-mapper` so future onboarding maps include frontend architecture/UI and frontend-backend contract sections.
- Created `C:\Users\HelmiKalle\Projektit\ai-skills\skills\react-test-generator` for behavior-focused React tests, including API mocking and loading/error/empty/success states.
- Added React tests in the frontend repo for intro loading/success/empty/error states and property rendering/image fallback behavior.
- Added property slide `role="img"` and `aria-label` values so background-image slides are more accessible and testable.
- Ran frontend `npm.cmd test -- --watchAll=false`: 3 test suites passed, 10 tests passed.
- Updated `GET /property/{propertyId}` to return `404 Not Found` instead of `200 OK` with an empty optional/null payload when the property is missing.
- Updated `DELETE /property/{propertyId}` and `DELETE /image/{id}` to return `204 No Content` on successful deletes and `404 Not Found` for missing resources.
- Added MockMvc integration coverage for missing property lookup, successful/missing property delete, and successful/missing image delete.
- Updated `docs/api-examples.md` with the new REST status-code behavior.
- Ran backend `.\mvnw.cmd test`: 11 tests passed, 0 failures, 0 errors.
- Discussed whether controllers should depend on service interfaces. Current decision: do not introduce service interfaces yet; constructor injection is the better next cleanup, and interfaces can wait until there are multiple implementations or a real architectural boundary.
- Ended the session with the highest-priority remaining backend task as P4 constructor injection cleanup.
- Before pushing, replaced the PostgreSQL profile's hardcoded password with `SAHA_POSTGRES_PASSWORD` and documented the PostgreSQL environment variables in `README.md`.
