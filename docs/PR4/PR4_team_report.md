# PR4 Team Report

Team 6: Kolina Katoanga, Yomna Fassatoui, Jordan Baugh, Leo Freije

## 1. Bug Triage Table

| Test name | Bucket | Issue link | Severity | Priority | Owner | Status |
|---|---|---|---|---|---|---|
| YF-04: Form does not open when the pin is put back in its box | Code bug | [#83](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/83) | Low | Medium | Yomna | Fixed |
| Second hazard report in the same session (new test) | Code bug | [#107](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/107) | Medium | High | Yomna | Fixed |
| YF-03: Pin stays visible on the map | Code bug | [#82](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/82) | High | Medium | Yomna | Open |
| YF-05: Report stored at the pin position | Missing feature | [#81](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/81) | High | High | Yomna | Open |
| JB-01: Update the map to the correct location when searching using an address | Missing Feature | [#92](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/92) | High | High | Jordan | Open |
| JB-02: Reject bad input in username registration | Code Bug | [#93](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/93) | High | Medium | Jordan | Fixed |
| JB-03: Require account password to contain 8 - 20 characters | Code bug | [#94](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/95) | Medium | Medium | Jordan | Fixed |
| JB-04: Let user create an account | Missing feature | [#95](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/95) | High | High | Jordan | Open |
| LF-03: Fix rejecting hazard description below minimum length | Code Bug | [#85](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/85) | High | High | Leo | Fixed |
| LF-06: Fix rejecting hazard description above maximum length  | Code Bug | [#86](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/86) | High| High | Leo | Fixed |
| LF-01: Resolve rejecting unsupported image file type | Missing feature | [#84](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/84) | High | Medium | Leo | Open |


## 2. Regression Log

| Fix (PR) | Test that broke | How it was resolved |
|---|---|---|
| | | |

## 3. Final Test Run

Tests still failing:
- YF-03 (#82): on the desktop app the map is a Chromium view drawn on top of the UI, so the pin goes under it. Fixing it needs a new way to place the pin (clicking on the map).
- YF-05 (#81): the pin location was never geo-mapped, so every report gets the fixed location 32.7757, -117.0719. Sorted as a missing feature.
- JB-01 (#92): Although the search value is stored, and there seems to be a way to turn addresses into coordinates for google maps, there is no call directly to the map. To fix it we need to know how exactly the map is implemented on the main screen of the app and be able to call a function on said map to change the location.
- JB-04 (#95): There is no storage for accounts implemented yet, but the app has implementation for storing text inputs of username and passwords and verifying the data within.
- LF-01 (#84): The option to attach an image to a hazard report is not yet implemented, so the test that checks if the attachment is a valid file type cannot pass yet before the feature is implemented.

## 4. Feature Decision

Feature:
User story: As a ___, I want ___, so that ___.
Why we chose it:
Who owns which part:
Test case 1:
Test case 2:
Issue:

## 5. Board Snapshot

## 6. Sprint Summary

