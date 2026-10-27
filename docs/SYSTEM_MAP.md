# **Trace the Click: System Map**

Oct 22, 2026 · @Manju Muralidharan

## **Instructions**

You are not fixing anything today. You are reading. Your team will map the inherited system as it actually exists, not as the handoff docs say it exists.

* **Time:** 40 minutes.  
* **Goal:** Trace one user action end to end and draw the as-built architecture.  
* **Submit:** Commit this map to your repo as docs/SYSTEM\_MAP.md with the diagram as an image and submit a photo of the paper version.  
* **Rule:** Every claim needs proof in the code. "Probably somewhere in utils" does not count.  
* **AI use:** AI may explain unfamiliar syntax. The diagram and trace must come from your own reading of the code.

| Time | Phase |
| :---- | :---- |
| 0 to 5 min | Pick one user action that touches the backend and database |
| 5 to 15 min | Draw the architecture diagram |
| 15 to 30 min | Tracers trace their halves. Analyst and Architect start Part 4\. |
| 30 to 35 min | Cross-check: each person explains one section they did not write to a teammate. |
| 35 to 40 min | Clean up and submit |

## **Team and roles**

**Team name:**

**Inherited project:**

Each of the four members owns one part of the map. Everyone reads code. Nobody spectates.

| Role | Owns | Specific task | Name |
| :---- | :---- | :---- | :---- |
| Product Analyst | Part 1 and the questions in Part 4 | Runs the app, picks the user action, writes the purpose sentence, and collects the team’s three questions for the original team. | Kolina |
| Architect | Part 2 and the mismatches in Part 4 | Draws the as-built diagram from the code, then compares it to the handoff UML and records every mismatch. | Yomna |
| Frontend Tracer | Part 3, client side | Traces the action from the button click to the request leaving the client. Every row gets a file path. | Jordan |
| Backend Tracer | Part 3, server side | Traces from the API route to the database and back to the response. Every row gets a file path. | Leo |

The two Tracers must meet at the same API endpoint. If your halves don’t connect, your trace is wrong.

## **Part 1: Purpose**

In one sentence: what does this system do, and for whom? Be specific. "It's a web app for users" is not an answer.

**User action we will trace:** (for example, "create an account" or "submit a post". It must touch the backend and the database.)

This system lets users report road hazards so that they can identify potentially hazardous material.

User Action: Submit a hazard report. After logging in, a user can drag a pin to where they find a hazardous issue, then write a description of the report. After clicking submit, the report is sent to the repository to be stored in the database.

## **Part 2: As-built architecture diagram**

Draw boxes and arrows for every major component. Draw only what the code proves exists. Label each arrow with what travels along it (HTTP request, SQL query, event, file).

Include any of these that exist:

* Client (web, mobile, CLI)  
* Server or API layer  
* Database or storage  
* External services and APIs  
* CI/CD pipeline and hosting

**Insert diagram image here.**

![][image1]

## **Part 3: Trace the click**

Follow your one user action from the button click to the database and back. Minimum five hops. Every row needs a real file path and function name. Add rows as needed.

***Example (create a user profile).** Rows 1 and 2 belong to the Frontend Tracer. Rows 3 to 7 belong to the Backend Tracer. They meet at POST /api/profiles.*

| Step | Layer | File and function | Data in / data out | Notes or surprises |
| :---- | :---- | :---- | :---- | :---- |
| 1 | Frontend | client/src/pages/CreateProfile.jsx → handleSave() | Form state → calls createProfile(data) | Photo uploads to image storage before Save |
| 2 | Frontend | client/src/api/profiles.js → createProfile() | Profile object → POST /api/profiles with JWT |  |
| 3 | API route | server/routes/profiles.js → router.post('/') | JSON body → passed to auth middleware |  |
| 4 | Middleware | server/middleware/auth.js → requireAuth() | JWT → req.user.id | Token expiry is never checked |
| 5 | Backend logic | server/controllers/profileController.js → createProfile() | Body \+ user id → validated profile | Bio length is not validated |
| 6 | Database | server/models/Profile.js → Profile.create() | Profile → INSERT INTO profiles, returns id | Interests saved as one comma-separated string |
| 7 | Response | profileController.js → res.status(201).json() | New profile → client redirects to /profile/:id |  |

**Your trace:**

| Step | Layer | File and function | Data in / data out | Notes or surprises |
| :---- | :---- | :---- | :---- | :---- |
| 1 | Frontend | shared/src/commonMain/kotlin/edu/sdsu/cs250/team5/road\_watch/[App.kt](http://App.kt) →  onClick() | Form field →  calls repo.addReport(type, description, lat, lng ); calls repo.getAllReports().size; resets hazard report box; | Hazard type, latitude, and longitude are hardcoded. |
| 2 | API route | Does not exist |  |  |
| 3 | Backend Logic | /shared/src/jvmMain/kotlin/edu/sdsu/cs250/team5/road\_watch/storage/SqliteHazardRepository.kt → getAllReports().size() | SELECT from reports → List\<HazardReport\> |  |
| 4 | Backend logic | shared/src/jvmMain/kotlin/edu/sdsu/cs250/team5/road\_watch/storage/SqliteHazardRepository.kt → addReport() | Report fields → validated values |  |
| 5 | Database | shared/src/jvmMain/kotlin/edu/sdsu/cs250/team5/road\_watch/storage/SqliteHazardRepository.kt → addReport() | Report fields → INSERT INTO reports; database generates ID | Uses PreparedStatement with ? parameters. SQLite assigns the ID. |
| 6 | Response back | shared/src/jvmMain/kotlin/edu/sdsu/cs250/team5/road\_watch/storage/SqliteHazardRepository.kt → addReport() / getAllReports() | Stored row → HazardReport; all database rows → List\<HazardReport\> | Repository converts SQLite rows back into Kotlin objects. |

## **Part 4: Reality check and open questions**

Open the handoff UML and compare it to your diagram. Circle every mismatch on your diagram.

**What the handoff docs got wrong or left out:**

1. The handoff document did not specify which API keys needed to be enabled on Google Cloud  
2. The planned diagram shows Search as part of the Compose UI. In the code, the search bar is not wired to anything yet

3. The planned diagram has an Application Layer (controllers, validation, map/location logic) between the UI and the repository, but in the code there is no such layer. App.kt calls repo.addReport(...) directly, and the validation happens inside SqliteHazardRepository.addReport().

**Three questions for the original team.** For each, note whether it is about intent ("was this on purpose?") or design ("why is it built this way?").

1. Design: How do we get the API key to work for Google maps?  
2. Intent: Was the draggable pin meant to determine the actual location of the hazard, or was it meant to only open the report?  
3. Intent: Are users supposed to be able to register for an account, or is the app only meant to have a login? Is it necessary?

   ***Example.***

   ***Mismatches:** (1) The handoff UML shows a separate Interests table, but the code stores interests as a comma-separated string in profiles. (2) The UML never mentions the image storage service.*

## **Grading checklist**

- [x]  ~~Purpose sentence is specific, not generic~~  
- [x]  ~~Diagram matches the code, not the handoff docs~~  
- [x]  ~~Trace has at least five hops, each with a file path~~  
- [x]  ~~At least one doc-vs-reality mismatch is identified~~  
- [x]  ~~Three real questions for the original team~~  
- [x] ~~Every role has a named owner, and the two trace halves meet at the same endpoint.~~
