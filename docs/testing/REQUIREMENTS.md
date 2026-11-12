# Testable Requirements: [Project Name]

**Team number:** [XX]
**Inherited from:** Team [XX]

## Who Owns What

| Area of the system | Owner | ID prefix |
|---|---|---|
| [example: Accounts and login] | [name] | [initials, like MG] |
| | | |
| Map, Pin and Location |Yomna F | YF |
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

## Section: [Your Area] ([Your Name])

### Stakeholder Goals

| ID | Stakeholder | Goal |
|---|---|---|
| G-XX-01 | | |

### Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| REQ-XX-01 | | | |
| REQ-XX-02 | | | |
| REQ-XX-03 | | | |

### Non-Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| NFR-XX-01 | | | |

### Example of a Good Rewrite

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| REQ-MG-01 | The system shall reject a profile bio longer than 280 characters with the error "Bio must be 280 characters or fewer." | Suspected bug | CR-3 (Unconfirmed): The system shall limit the bio to 280 characters. |
| NFR-MG-01 | The profile page loads in under 2 seconds with 100 profiles in the database. | Confirmed | New |

---

## Section: Map, Pin and Location (Yomna F)

### Stakeholder Goals

| ID | Stakeholder | Goal |
|---|---|---|
| G-YF-01 | Driver who reports a road hazard | When I report a hazard, it is saved at the place where I dropped the pin, so other drivers can avoid that exact spot. |

### Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| REQ-YF-01 | When a logged-in user releases the pin after dragging it, the system shows the Hazard Report form (title "Hazard Report", a description field and a Submit Report button). | Confirmed | CR-3 (Confirmed): The system shall display the hazard report form when a logged-in user releases the hazard pin. |
| REQ-YF-02 | When a logged-in user submits the Hazard Report form, the system stores the latitude and longitude of the point where the pin was released, within 0.001 degrees. It does not store the fixed default 32.7757, -117.0719 for a pin dropped somewhere else. | Suspected bug | CR-4 (Suspected Bug): The system shall submit the latitude and longitude of the geographical position selected by the pin feature. |
| REQ-YF-03 | The system shall reject a report whose latitude is outside -90 to 90 or whose longitude is outside -180 to 180 (both limits are valid values), with the error "Latitude must be between -90 and 90" or "Longitude must be between -180 and 180." | Confirmed | New |
| REQ-YF-04 | After the user releases the pin on the map, the pin stays visible on the map at the point where it was released. | Suspected bug | New |
| REQ-YF-05 | The system shall show the Hazard Report form only when the pin was released on the map, not when it was released inside its starting box (the gray square at the bottom left). | Suspected bug | New |

### Non-Functional Requirements

| ID | Requirement | Status | From Part 5 |
|---|---|---|---|
| NFR-YF-01 | The map shows its tiles within 5 seconds of the app window opening, on a normal Wi-Fi connection.|	Confirmed | New |

---

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
