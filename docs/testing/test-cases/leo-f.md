# Test Cases: Leo Freije

**Team number:** Group 6
**My area:** Hazard reporting/Validation

---

**Test ID:** LF-01
**From Part 6:** TC-1

**Written by:** Leo Freije

**Title:** Reject unsupported image file type

**Traces to:** REQ-LF-01

**Test type:** Negative

**Preconditions:** The user is logged in by pressing the log in button, and the map is displayed.

**Test data:** Valid hazard description (between 3-500 characters) and an image filename ending in .php
- description = "Pothole in the road"
- lat = 32.7757
- lng = -117.0719
- image = "hazard.php"

**Steps:**
1. Open the hazard report form using the draggable pin
2. Enter the valid hazard description "Pothole in the road" and attach an image ending in .php
3. Try to submit the hazard report

**Expected result:** The system rejects the hazard report because .php is not a supported image type, as only .jpg, .jpeg, and .png image files should be accepted.

**Actual result:** The test could not be completed through the hazard-report UI because image attachment is not currently implemented. A separate ReportValidator unit test confirmed that hazard.php is correctly rejected by the validation logic, but functionality cannot currently be tested from the user interface.

**Status:** Fail

**Severity:** Major

**V or V:** Verification

**GitHub Issue:** "[LF-01] Resolve rejecting unsupported image file type"

---

**Test ID:** LF-02
**From Part 6:** TC-3

**Written by:** Leo Freije

**Title:** Accept minimum valid hazard-description length

**Traces to:** REQ-LF-02

**Test type:** Boundary

**Preconditions:** The user is logged in by pressing the log in button, and the map is displayed.

**Test data:** 
- description = "see"
- lat = 32.7757
- lng = -117.0719
- image = none

**Steps:**
1. Open the hazard report form using the draggable pin
2. Enter "see" as the hazard description
3. Submit the hazard report

**Expected result:** The report is accepted, the form closes, and the hazard count increases by one

**Actual result:** The report was accepted, the form closed, and the "Hazards reported" counter increased by one. Unit test result: ReportValidator.validate() returned null for the 3-character description "see".

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**GitHub Issue:** N/A

---

**Test ID:** LF-03
**From Part 6:** New

**Written by:** Leo Freije

**Title:** Reject hazard description below minimum length

**Traces to:** REQ-LF-02, NFR-LF-01

**Test type:** Boundary

**Preconditions:** The user is logged in by pressing the log in button, and the map is displayed

**Test data:**
- description = "se"
- lat = 32.7757
- lng = -117.0719
- image = none

**Steps:**
1. Open the hazard report form using the draggable pin
2. Enter "se" as the hazard description
3. Submit the hazard report

**Expected result:** The report is not accepted and displays an error because the description contains fewer than 3 characters

**Actual result:** The report is accepted, the form closes, and the hazard count increases by one

**Status:** Fail

**Severity:** Major

**V or V:** Verification

**GitHub Issue:** "[LF-03] Fix rejecting hazard description below minimum length"

---

**Test ID:** LF-04
**From Part 6:** New

**Written by:** Leo Freije

**Title:** A valid hazard report can be submitted without an image

**Traces to:** G-LF-01, REQ-LF-04

**Test type:** Acceptance

**Preconditions:** The user is logged in by pressing the log in button, and the map is displayed.

**Test data:**
- description = "Pothole in the road"
- lat = 32.7757
- lng = -117.0719
- image = none

**Steps:**
1. Open the hazard report form using the draggable pin
2. Enter "Pothole in the road" as the hazard description and leave the image field empty
3. Submit the hazard report

**Expected result:** The form closes after being submitted and the "Hazards reported" counter increments by one

**Actual result:** The form was accepted, the form closed, and the hazard count increased by one

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**GitHub Issue:** N/A

---

**Test ID:** LF-05
**From Part 6:** New

**Written by:** Leo Freije

**Title:** Reject an empty hazard report

**Traces to:** REQ-LF-03

**Test type:** Negative

**Preconditions:** The user is logged in by pressing the log in button, and the map is displayed.

**Test data:** 
- case 1: description = ""
- case 2: description = " "
- lat = 32.7757
- lng = -117.0719
- image = none

**Steps:**
1. Open the hazard report form using the draggable pin
2. Try submitting the hazard report without typing anything
3. Try submitting the hazard report after only typing in " " (a single space)

**Expected result:** The report is not accepted when the submit button is clicked with no description or whitespace

**Actual result:** The report was not accepted in both cases, and it still allows the user to write a description

**Status:** Pass

**Severity:** N/A

**V or V:** N/A

**GitHub Issue:** N/A

---

**Test ID:** LF-06
**From Part 6:** New

**Written by:** Leo Freije

**Title:** Reject a hazard report description that is more than 500 characters

**Traces to:** REQ-LF-05

**Test type:** Unit

**Preconditions:** The ReportValidator unit tests can be executed on the JVM target.

**Test data:** 
- case 1: description containing exactly 500 characters
- case 2: description containing 501 characters
- lat = 32.7757
- lng = -117.0719
- image = none

**Steps:**
1. Call ReportValidator.validate() with a 500-character description
2. Verify that the description is accepted
3. Call ReportValidator.validate() with a 501-character description
4. Verify that the description is accepted

**Expected result:** The 500-character description is accepted, and the 501-character description is rejected

**Actual result:** ReportValidator.validate() accepted a 500-character description and a 501-character description, which means that the test ran successfully and shows that the description enforcement failed

**Status:** Fail

**Severity:** Major

**V or V:** Verification

**GitHub Issue:** "[LF-06] Fix rejecting hazard description above maximum length"

---
