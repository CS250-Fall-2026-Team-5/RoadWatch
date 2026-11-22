# PR4 Team Report

Team 6: Kolina Katoanga, Yomna Fassatoui, Jordan Baugh, Leo Freije

## 1. Bug Triage Table

| Test name | Bucket | Issue link | Severity | Priority | Owner | Status |
|---|---|---|---|---|---|---|
| YF-04: Form does not open when the pin is put back in its box | Code bug | [#83](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/83) | Low | Medium | Yomna | Fixed |
| Second hazard report in the same session (new test) | Code bug | [#107](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/107) | Medium | High | Yomna | Fixed |
| YF-03: Pin stays visible on the map | Code bug | [#82](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/82) | High | Medium | Yomna | Open |
| YF-05: Report stored at the pin position | Missing feature | [#81](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/81) | High | High | Yomna | Open |
| | | | | | | |

## 2. Regression Log

| Fix (PR) | Test that broke | How it was resolved |
|---|---|---|
| | | |

## 3. Final Test Run

Tests still failing:
- YF-03 (#82): on the desktop app the map is a Chromium view drawn on top of the UI, so the pin goes under it. Fixing it needs a new way to place the pin (clicking on the map).
- YF-05 (#81): the pin location was never geo-mapped, so every report gets the fixed location 32.7757, -117.0719. Sorted as a missing feature.
-

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

