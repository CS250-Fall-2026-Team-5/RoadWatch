# Testable Requirements: RoadWatch

**Team number:** 6
**Inherited from:** Team 5

## Who Owns What

| Area of the system | Owner | ID prefix |
|---|---|---|
| [example: Accounts and login] | [name] | [initials, like MG] |
| Hazard reporting/Validation | Leo | LF |
| | | |
| | | |

<!--
RULES
- Only edit YOUR OWN section below. This keeps merge conflicts away.
- Categories: G = Stakeholder Goal, REQ = Functional, NFR = Non-Functional.
- "From Part 5": write the original CR ID and its text from SYSTEM_MAP.md. Write "New" if you added it this sprint.
- Status: Confirmed or Suspected bug. No Unconfirmed allowed. Every Suspected bug needs a GitHub Issue.
- The test: could a stranger write a Pass or Fail test without asking you a question?
-->

---

## Section: Hazard reporting/Validation (Leo Freije)

### Stakeholder Goals

| ID | Stakeholder | Goal |
|---|---|---|
| G-LF-01 | RoadWatch user | Users need to submit valid hazard reports with enough information for road hazards to be recorded accurately and be useful to other road users.|

### Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| REQ-LF-01 |The system shall reject a hazard report when its attached image filename does not end in .jpg, .jpeg, or .png.|Suspected Bug|CR-1 (Unconfirmed): The system shall reject image files that aren't a jpg, jpeg, or a png.|
| REQ-LF-02 |The system shall reject descriptions containing fewer than 3 characters.|Suspected Bug|CR-2 (Confirmed): The system shall store a hazard report when a user submits a hazard description with at least 3 characters.|
| REQ-LF-03 |The system shall reject an empty or whitespace only hazard report description.|Confirmed|New|
| REQ-LF-04 |The system shall accept a valid hazard report without an attached image.|Confirmed|New|
| REQ-LF-05 |The system shall reject descriptions containing more than 500 characters.|Suspected Bug|New|

### Non-Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| NFR-LF-01 |When a hazard report is rejected, a message will be displayed to the user that identifies the corresponding validation error.|Suspected Bug|New|

### Example of a Good Rewrite

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| REQ-MG-01 | The system shall reject a profile bio longer than 280 characters with the error "Bio must be 280 characters or fewer." | Suspected bug | CR-3 (Unconfirmed): The system shall limit the bio to 280 characters. |
| NFR-MG-01 | The profile page loads in under 2 seconds with 100 profiles in the database. | Confirmed | New |

---

## Section: [Your Area] ([Your Name])

### Stakeholder Goals

| ID | Stakeholder | Goal |
|---|---|---|
| | | |

### Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| | | | |
| | | | |
| | | | |

### Non-Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| | | | |


---

## Section: [Your Area] ([Your Name])

### Stakeholder Goals

| ID | Stakeholder | Goal |
|---|---|---|
| | | |

### Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| | | | |
| | | | |
| | | | |

### Non-Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| | | | |

---

## Section: [Your Area] ([Your Name])

### Stakeholder Goals

| ID | Stakeholder | Goal |
|---|---|---|
| | | |

### Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| | | | |
| | | | |
| | | | |

### Non-Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| | | | |
