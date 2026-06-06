# Saha Spring Backend Agent Instructions

Read this entire file before starting any task in this repository.

This file contains project-specific instructions for:

`C:\Users\HelmiKalle\Projektit\saha-prac-project-spring`

Also check `PROJECT_CONTEXT.md` for the current project state, backlog, decisions, and session log.

## Project Summary

This repository is the Spring Boot backend for a practice/showcase project about an old saw complex. The frontend is a separate React repository:

`C:\Users\HelmiKalle\Projektit\saha-prac-project-react`

Current direction:

- Use H2 for local/development persistence.
- Store text content and image metadata in the backend database.
- Keep actual images externally hosted for now.
- Treat Google Photos as the current image host, but keep the backend model generic.
- Understand the backend/frontend contract before broad refactoring.

## Self-Correcting Rules Engine

This file contains a growing ruleset that improves over time. At session start, read the entire "Learned Rules" section before doing project work.

### How It Works

1. When the user corrects the agent or the agent makes a mistake, immediately append a new rule to the "Learned Rules" section at the bottom of this file.
2. Rules are numbered sequentially and written as clear, imperative instructions.
3. Format: `N. [CATEGORY] Never/Always do X - because Y.`
4. Categories: `[STYLE]`, `[CODE]`, `[ARCH]`, `[TOOL]`, `[PROCESS]`, `[DATA]`, `[UX]`, `[OTHER]`
5. Before starting any task, scan all rules below for relevant constraints.
6. If two rules conflict, the higher-numbered, newer rule wins.
7. Never delete rules. If a rule becomes obsolete, append a new rule that supersedes it.

### When To Add A Rule

- The user explicitly corrects an output, approach, or assumption.
- The user rejects a file, implementation pattern, or workflow.
- A bug is caused by a wrong assumption about this codebase.
- The user states a durable preference, such as "always use X" or "never do Y".

### Rule Format Example

```text
14. [CODE] Always use constructor injection for new Spring services - because it makes dependencies explicit and easier to test.
15. [PROCESS] Always update PROJECT_CONTEXT.md after completing backlog-relevant work - because this project is being revived across sessions.
16. [ARCH] Never hardcode Google Photos-specific assumptions into entity names - because image hosting may change later.
```

## Baseline Project Rules

1. [PROCESS] Always read `PROJECT_CONTEXT.md` before starting non-trivial work - because it records the current state, decisions, backlog, and open questions.
2. [DATA] Always prefer H2 for local/development work unless the user explicitly asks for PostgreSQL or another database - because the current revival goal is easy local verification.
3. [ARCH] Always store image metadata and external URLs generically rather than tying the backend model to Google Photos - because Google Photos is only the current free host and may change later.
4. [PROCESS] Always verify the backend/frontend API contract before broad endpoint or model refactoring - because the React frontend lives in a separate repository and may depend on current response shapes.
5. [PROCESS] Always update `PROJECT_CONTEXT.md` when completing meaningful project work or changing a durable decision - because it is the lightweight project memory and board.

---

## Learned Rules

<!-- New rules are appended below this line. Do not edit above this section. -->
6. [PROCESS] Always notify the user before starting the last remaining Todo backlog ticket - because the user wants a small review and next-step planning session before the backlog is exhausted.
7. [PROCESS] Always prioritize improving tests after the application is confirmed to run and baseline tests pass - because the user wants test coverage updated before deeper feature or model work.
8. [PROCESS] Always treat this project as a mentoring project: explain meaningful Java, Spring Boot, React, and UI corrections briefly after making them - because the user wants to learn the better practice, not just receive changed code.
9. [PROCESS] Always keep backend and frontend tasks in separate backlog sections - because the user wants UI work and Java/Spring work tracked independently.
10. [PROCESS] Always update frontend backlog items when backend API/model changes create frontend follow-up work - because the project should move toward a robust coordinated implementation instead of preserving old compatibility forever.
11. [PROCESS] Always consider whether a completed ticket contains a reusable architecture lesson worth adding to `C:\Users\HelmiKalle\Projektit\ai-skills\skills\architecture-guidelines\references\decision-log.md`; add only durable, cross-project, decision-shaping lessons, and prefer open questions for uncertain lessons - because the architecture skill should improve without becoming cluttered with project-specific details.
12. [PROCESS] Always assign a priority from P1 to P5 when creating backlog tasks, where P5 is most important - because the user wants tasks ranked by importance.
