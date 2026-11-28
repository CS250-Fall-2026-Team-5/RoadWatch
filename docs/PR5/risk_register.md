# Risk Register

| ID | Risk (CTC) | Category | P | Impact | Cost (days) | Exposure | Response | Trigger | Owner | Status |
|---|---|---|---|---|---|---|---|---|---|---|
| YF-R3 | Given that login, registration and logout all need the account database and we only have Dec 3 to Dec 10 to build, then there is concern that if the database is late the rest of the feature can't be tested in time. | Project | 0.4 | 2 Critical | 3 | 1.2 | | | | Open |
| YF-R1 | Given that the inherited login only sets isLoggedIn to true and nothing else checks who is logged in, then there is concern that an open Hazard Report box stays usable after logout and a logged-out user can still submit a report. | Technical | 0.5 | 3 Marginal | 2 | 1.0 | Mitigate: Logout closes every box and resets the fields and the pin, with a test | After Logout a box is still open or Submit Report still works | Yomna Fassatoui | Open |
| YF-R2 | Given that login, registration and logout are all in App.kt and the 4 of us change it the same week, then there is concern that our PRs will conflict and a merge will break someone else's part. | Technical | 0.7 | 3 Marginal | 1 | 0.7 | | | | Open |
| | | | | | | | | | | |
| | | | | | | | | | | |
| **CUTOFF** | | | | | | | | | | |
| | | | | | | | | | | |
