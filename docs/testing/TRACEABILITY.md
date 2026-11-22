# Traceability Matrix

**Team number:** Group 6
**Filled in by:** Jordan Baugh, Leo Freije

Fill this in **after** everyone's work is merged. One row per requirement.

## Requirements to Tests

| Requirement ID | From Part 5 | Owner | Test case IDs | Result | GitHub Issue # |
|---|---|---|---|---|---|
| G-LF-01 | N/A | Leo Freije | LF-04 | 1 Pass | N/A |
| REQ-LF-01 | CR-1 | Leo Freije | LF-01 | 1 Fail | #84 |
| REQ-LF-02 | CR-2 | Leo Freije | LF-02, LF-03 | 1 Pass, 1 Fail | #85 |
| REQ-LF-03 | New | Leo Freije | LF-05 | 1 Pass | N/A |
| REQ-LF-04 | New | Leo Freije | LF-04 | 1 Pass | N/A |
| REQ-LF-05 | New | Leo Freije | LF-06 | 1 Fail | #86 |
| NFR-LF-01 | New | Leo Freije | LF-03 | 1 Fail | #85 |
| G-YF-01 | N/A | Yomna F | YF-05 | 1 Fail | #81 |
| REQ-YF-01 | CR-3 | Yomna F | YF-01 | 1 Pass | N/A |
| REQ-YF-02 | CR-4 | Yomna F | YF-05 | 1 Fail | #81 |
| REQ-YF-03 | New | Yomna F | YF-02 | 1 Pass | N/A |
| REQ-YF-04 | New | Yomna F | YF-03 | 1 Fail | #82 |
| REQ-YF-05 | New | Yomna F | YF-04 | 1 Fail | #83 |
| NFR-YF-01 | New | Yomna F | N/A | N/A | N/A |
| G-KK-01 | N/A | Kolina Katoanga | KK-03 | 1 Pass | N/A |
| REQ-KK-01 | CR-5 | Kolina Katoanga | KK-01 | 1 Pass | N/A |
| REQ-KK-02 | New | Kolina Katoanga | KK-02, KK-04 | 2 Pass | N/A |
| REQ-KK-03 | New | Kolina Katoanga | KK-03 | 1 Pass | N/A |
| NFR-KK-01 | New | Kolina Katoanga | KK-04 | 1 Pass | N/A |
| G-JB-01 | N/A | Jordan Baugh | JB-01 | 1 Fail | #92 |
| G-JB-02 | N/A | Jordan Baugh | JB-04 | 1 Fail | #95 |
| REQ-JB-01 | New | Jordan Baugh | JB-01 | 1 Fail | #92 |
| REQ-JB-02 | New | Jordan Baugh | N/A | N/A | N/A |
| REQ-JB-03 | New | Jordan Baugh | N/A | N/A | N/A |
| REQ-JB-04 | New | Jordan Baugh | JB-02 | 1 Fail | #93 |
| REQ-JB-05 | New | Jordan Baugh | JB-03 | 1 Fail | #94 |
| NFR-JB-01 | New | Jordan Baugh | N/A | N/A | N/A |

## Gaps

List any requirement with no test, any test with no requirement, and any Part 5 requirement or Part 6 test that nobody brought over. Write "None" if there are none.

| Requirements with no test | Test without requirements | P5 or P6 parts left behind | 
|---|---|---|
| NFR-YF-01 | None | None |
| REQ-JB-02 | | |
| REQ-JB-03 | | |
| NFR-JB-01 | | |

## Test Type Coverage

Put a Test ID next to each type. Team minimum: all 7.

| Test type | Test ID |
|---|---|
| Unit | KK-01 |
| Integration | JB-01 |
| System (E2E) | YF-01 |
| Acceptance | LF-04 |
| Boundary Value | LF-02 |
| Negative | YF-04 |
| Security | LF-01 |

## Validation Findings

A Validation finding means the system does what the requirement says, but the requirement does not serve the Stakeholder Goal. Remember the alphabetical leaderboard.

| Requirement ID | Goal it fails | Why | Issue # |
|---|---|---|---|
| REQ-YF-05 | The system shall show the Hazard Report form only when the pin was released on the map, not when it was released inside its starting box. | The Hazard Report form should open only when the pin is released on the map, but it also opens when the pin is returned to its starting box. | 83 |

**If you found none,** explain in 2 to 3 sentences why every requirement truly serves its Goal:
> N/A

## Summary

| | Count |
|---|---|
| Part 5 requirements carried over | 5 |
| New requirements added | 18 |
| Part 6 tests upgraded (out of 3) | 3 |
| Total requirements | 21 |
| Total test cases | 19 |
| Passed | 9 |
| Failed | 10 |
| Issues opened | 15 |
| Critical issues | 5 |

**In one sentence, would you ship this system today? Why or why not?**
> No, I would not ship this system, as almost every feature implemented is incomplete, except for the map.
