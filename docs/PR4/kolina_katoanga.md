# Kolina Katoanga

## Bug 1: Stored hazard reports were not displayed

- **GitHub Issue:** https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/110
- **Pull Request:** https://github.com/CS250-Fall-2026-Team-5/RoadWatch/pull/119#issue-6551679501
- **Before screenshot:**  
  <img width="783" height="587" alt="kolina_katoanga_issue110_before" src="https://github.com/user-attachments/assets/019bca4b-e6f0-4567-b3cd-c9f01d11fdfd" />
- **After screenshot:**  
  <img width="783" height="591" alt="kolina_katoanga_issue110_after" src="https://github.com/user-attachments/assets/75b8f13b-b3b9-4cdb-a900-87ef2d14d3c4" />


### What happened

Stored hazard reports were successfully saved in the database, but the application did not display the reports to the user after they were retrieved. The hazard count could show that reports existed, but users could not see the report details.

### Classification

This was a missing feature/code problem because the repository could retrieve stored reports, but the user interface did not display the returned reports.

### How I reproduced it

1. Start the RoadWatch desktop application.
2. Create and submit a hazard report.
3. Close or restart the application.
4. Open the application again.
5. Check the stored hazard count and report display area.

### Expected result

The application should display each stored hazard report, including its type, description, location, and image information.

### Actual result

The stored report was not displayed to the user.

### Fix

I updated the application interface to retrieve the stored reports and display their information in the main screen. The displayed information includes the hazard type, description, latitude, longitude, and image value.

### How I tested the fix

I created hazard reports, restarted the application, and confirmed that the stored reports were retrieved and displayed. I also ran the repository tests to confirm that reports, ordering, empty databases, and images were handled correctly.

---

## Bug 2: Multiple hazard reports could run off the visible screen

- **GitHub Issue:** (https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/117)
- **Pull Request:** https://github.com/CS250-Fall-2026-Team-5/RoadWatch/pull/119#issue-6551679501
- **Before screenshot:**  
  <img width="775" height="588" alt="kolina_katoanga_issue117_before" src="https://github.com/user-attachments/assets/3382d7ff-b2dd-4533-b7d9-ec13b6128d8e" />
- **After screenshot:**  
 <img width="778" height="585" alt="kolina_katoanga_issue117_after" src="https://github.com/user-attachments/assets/9530aa95-b09d-4745-ac8a-1ad58026d10f" />


### What happened

When multiple stored hazard reports were displayed, the reports could overlap other interface elements or extend beyond the visible application window. This made some report information difficult or impossible to read.

### Classification

This was a code bug in the user interface layout. The reports were being retrieved, but the display area did not properly control the size or placement of multiple reports.

### How I reproduced it

1. Start the RoadWatch application.
2. Submit or save multiple hazard reports.
3. Return to the main screen.
4. Observe the area displaying the stored reports.
5. Resize the application window or add enough reports to exceed the available space.

### Expected result

Multiple reports should remain inside a defined display area. They should not overlap other controls or extend off the screen. Users should be able to view all reports using the provided display behavior.

### Actual result

The reports extended beyond the visible area and some content was cut off or overlapped other parts of the interface.

### Fix

I constrained the report display area and placed the stored reports in a dedicated layout section. I also adjusted the spacing and sizing so that multiple reports are displayed without covering the other controls.

### How I tested the fix

I ran the application with multiple stored hazard reports and confirmed that the reports appeared in the intended display area. I checked that the report count, report details, map area, and report form remained visible.

---
AI Disclosure: I used ChatGPT to interpret failed test output.
---

## Test Evidence

- **Repository test command:**

```text
.\gradlew.bat :shared:jvmTest
