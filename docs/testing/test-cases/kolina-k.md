# Test Cases: Kolina K

**Team number:** Group 6
**My area:** Hazard storage/display

---

**Test ID:** KK-01
**From Part 6:** CR-5

**Written by:** Kolina K

**Title:** Return stored hazard reports in ascending ID order

**Traces to:** REQ-KK-01

**Test type:** Unit

**Preconditions.** The database contains multiple hazard reports with different ID numbers.

**Test data:**
-Hazard report ID 1
-Hazard report ID 2
-Hazard report ID 3

**Steps:**
1. Call 'SqliteHazardRepository.getAllReports()'.
2. Examine the list of returned hazard reports.
3. Compare the order of the reports by ID number.

**Expected result:** The reports are returned from the lowest ID number to the highest ID number.

**Actual result:** Three hazard reports were stored and 'getAllReports()' returned their IDs in ascending order. 

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**Github Issue:** N/A

---

**Test ID:** KK-02
**From Part 6:** New

**Written by:** Kolina K

**Title:** Stored hazard report is retrieved unchanged

**Traces to:** REQ-KK-02

**Test type:** Unit

**Preconditions.** An isolated database is initialized.

**Test data:**
- Type: Pothole
- Description: "Deep pothole in right lane"
- Latitude: '32.7757'
- Longitude: '-117.0719'
- Image: None

**Steps:**
1. Store a hazard report into database.
2. Create a new repository connected to the same database.
3. Retrieve all stored reports.
4. Compare the retrieved report with the original.

**Expected result:** The retrieved report has the same ID, type, description, latitude, longitude, and image value as the one stored.

**Actual result:** The hazard report was stored in a temporary database and retrieved unchanged. It's ID, type, description, latitude, and longitude matched. 

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**Github Issue:** N/A

---

**Test ID:** KK-03
**From Part 6:** New

**Written by:** Kolina K

**Title:** Hazard count updates after a report is submitted

**Traces to:** REQ-KK-03

**Test type:** Acceptance

**Preconditions.** The user is logged in and the application is displaying the hazard count.

**Test data:**
-Description: "Pothole in the road"
Image: None

**Steps:**
1. Open the application
2. Record the current hazard count.
3. Log in.
4. Drag and release the hazard pin
5. Enter "Pothole in the road" as the description
6. Click submit report
7. Observe the hazard count

**Expected result:** The report is stored, the form closes, and the hazard count increases by 1.

**Actual result:** The report was submitted successfully, the form closed, and the hazard count increased by 1.

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**Github Issue:** N/A

---

**Test ID:** KK-04
**From Part 6:** New

**Written by:** Kolina K

**Title:** Empty database returns no hazard reports

**Traces to:** REQ-KK-02, NFR-KK-01

**Test type:** Negative

**Preconditions.** The empty database in initialized.

**Test data:**
-Hazard report in database: None

**Steps:**
1. Initialize an empty test database
2. Call 'SqliteHazardRepository.getAllReports()'.
3. Check the number of reports returned

**Expected result:** The repository returns an empty list without crashing.

**Actual result:** The initialized empty database returned an empty list of hazard reports

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**Github Issue:** N/A
