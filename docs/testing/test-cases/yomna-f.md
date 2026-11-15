# Test Cases: Yomna F

**Team number:** 6
**My area:** Map, Pin and Location

---

**Test ID:** YF-01
**From Part 6:** TC-2 (upgraded)
**Written by:** Yomna F
**Title:** Hazard Report form opens when a logged-in user releases the pin on the map
**Traces to:** REQ-YF-01
**Test type:** System
**Preconditions:** The desktop app is running and the map is visible. The pin is in its starting box.
**Test data:** None. The Log In button has no username or password.

**Steps:**
1. Click Login, then click Log In.
2. Drag the pin from its box onto the map.
3. Release the pin.

**Expected result:** The Hazard Report box appears with the title "Hazard Report", a description field and a Submit Report button.
**Actual result:** The Hazard Report box appeared. Platform: Desktop (macOS)
**Status:** Pass
**Severity:** N/A
**V or V:** N/A
**GitHub Issue:** N/A

---

**Test ID:** YF-02
**From Part 6:** New
**Written by:** Yomna F
**Title:** The latitude limit of 90 is respected (three values around the limit)
**Traces to:** REQ-YF-03
**Test type:** Unit, Boundary Value
**Preconditions:** Unit under test: ReportValidator.validate() in ReportValidator.kt. Test code: CoordinateLimitsTest.kt in shared/src/commonTest.
**Test data:** Description "Pothole near entrance", longitude -117.0719, image null. Latitude values: 89.9999, 90.0 and 90.0001.

**Steps:**
1. Call ReportValidator.validate() with latitude 89.9999.
2. Call it with latitude 90.0.
3. Call it with latitude 90.0001.

**Expected result:** 89.9999 and 90.0 return null (valid). 90.0001 returns "Latitude must be between -90 and 90".
**Actual result:** validate() returned null for 89.9999 and 90.0, and "Latitude must be between -90 and 90" for 90.0001. Platform: JVM unit test (macOS)
**Status:** Pass
**Severity:** N/A
**V or V:** N/A
**GitHub Issue:** N/A

---

**Test ID:** YF-03
**From Part 6:** New
**Written by:** Yomna F
**Title:** The pin stays visible on the map where it was released
**Traces to:** REQ-YF-04
**Test type:** System
**Preconditions:** The desktop app is running and the map is visible. The user is logged in.
**Test data:** None.

**Steps:**
1. Drag the pin from its box onto the middle of the map.
2. Release the pin.
3. Look for the pin on the map.

**Expected result:** The pin is visible on the map at the spot where it was released.
**Actual result:** The pin disappears when it is dropped on the map, so the user cannot tell where it landed. Platform: Desktop (macOS)
**Status:** Fail
**Severity:** Major
**V or V:** Verification
**GitHub Issue:** #82

---

**Test ID:** YF-04
**From Part 6:** New
**Written by:** Yomna F
**Title:** The form does not open when the pin is put back in its starting box
**Traces to:** REQ-YF-05
**Test type:** Negative
**Preconditions:** The desktop app is running. The user is logged in.
**Test data:** None.

**Steps:**
1. Press and hold the pin in its box, move it a little and bring it back to its starting spot.
2. Release the pin.

**Expected result:** The Hazard Report box does not appear, because the pin was not placed on the map.
**Actual result:** The Hazard Report box appears even though the pin was put back in its box. Platform: Desktop (macOS)
**Status:** Fail
**Severity:** Minor
**V or V:** Validation
**GitHub Issue:** #83

---

**Test ID:** YF-05
**From Part 6:** New
**Written by:** Yomna F
**Title:** A hazard report is stored at the spot where the pin was dropped
**Traces to:** G-YF-01, REQ-YF-02
**Test type:** Acceptance
**Preconditions:** The desktop app is running and the user is logged in. A SQLite viewer is used to read roadwatch.db.
**Test data:** Spot A and Spot B: two landmarks on the map, at least 2 km apart and at least 2 km from the SDSU campus. Description: "Pothole near entrance".

**Steps:**
1. Drop the pin on Spot A, enter the description and click Submit Report.
2. Drop the pin on Spot B, enter the description and click Submit Report.
3. Read lat and lng of the two new rows in the reports table of roadwatch.db.
4. Read the real coordinates of Spot A and Spot B in Google Maps.

**Expected result:** The two new rows have different lat and lng values, neither equals 32.7757, -117.0719, and each is within 0.001 degrees of its spot.
**Actual result:** Both new rows have lat 32.7757 and lng -117.0719, the fixed default, even though the pin was dropped on two different positions. Platform: Desktop (macOS)
**Status:** Fail
**Severity:** Critical
**V or V:** Verification
**GitHub Issue:** #81 (related to #59, the original team's issue for the same bug)

---
