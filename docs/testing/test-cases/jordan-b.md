# Test Cases: [Your First Name Last Initial]

**Team number:** Group 6
**My area:** Search Query & Login

---

**Test ID:** JB-01

**From Part 6:** New

**Written by:** Jordan Baugh

**Title:** Update the map to the correct location when searching using an address

**Traces to:** REQ-JB-01, G-JB-01

**Test type:** Integration & Valid Partition

**Preconditions:** The map is functional

**Test data:** Search Query: "2101 Stadium Wy, San Diego, CA 92108"

**Steps:**
1. Type "2101 Stadium Wy, San Diego, CA 92108" into the search bar
2. Press the "Search" Button

**Expected result:** The map updates to show Snapdragon Stadium in the center

**Actual result:** Nothing Happens

**Status:** Fail

**Severity:** Critical

**V or V:** Verification

**GitHub Issue:** 92

---


**Test ID:** JB-02

**From Part 6:** New

**Written by:** Jordan Baugh

**Title:** Reject bad input in username registration

**Traces to:** REQ-JB-04

**Test type:** Unit & Negative

**Preconditions:** None

**Test data:** Username: "", Password: "P4ssw0rd

**Steps:**
1. Press the "Login" Button
2. Press the "Sign Up" Button
3. Enter "P4ssw0rd" as the password
4. Press the "Confirm" Button

**Expected result:** Return an error stating "Password must contain 3+ characters"

**Actual result:** The system has not implemented a Sign Up feature and thus cannot be tested

**Status:** Fail

**Severity:** Critical

**V or V:** Validation

**GitHub Issue:** 93

---

**Test ID:** JB-03

**From Part 6:** New

**Written by:** Jordan Baugh

**Title:** Require account password to contain 8 - 20 characters

**Traces to:** REQ-JB-05

**Test type:** Unit & Boundary

**Preconditions:** None

**Test data:** Username: username, Password: P4ssw0rd

**Steps:**
1. Press the "Login" Button
2. Press the "Sign Up" Button
3. Enter "username" as the username
4. Enter "P4ssw0rd" as the password
5. Press the "Confirm" Button

**Expected result:** The user successfully creates an account and the system displays the message "Account created successfully"

**Actual result:** The system has not implemented a Sign Up feature and thus cannot be tested

**Status:** Fail

**Severity:** Critical

**V or V:** Validation

**GitHub Issue:** 94

---

**Test ID:** JB-04

**From Part 6:** New

**Written by:** Jordan Baugh

**Title:** Let user create an account

**Traces to:** G-JB-02

**Test type:** Acceptance & Valid Partition

**Preconditions:** None

**Test data:** Username: username, Password: V3ryS3cureP@ssw0rd

**Steps:**
1. Press the "Login" Button
2. Press the "Sign Up" Button
3. Enter "username" as the username
4. Enter "V3ryS3cureP@ssw0rd" as the password
5. Press the "Confirm" Button

**Expected result:** The user successfully creates an account and the system displays the message "Account created successfully"

**Actual result:** The system has not implemented a Sign Up feature and therefore can't create an account

**Status:** Fail

**Severity:** Critical

**V or V:** Validation

**GitHub Issue:** 95

---

