# PR5 Team Report

Team 6: Kolina Katoanga, Yomna Fassatoui, Jordan Baugh, Leo Freije

## 1. Feature Scope

**Feature:** Users can create an account, log in and log out.

**User story:** As a RoadWatch user, I want to create an account and log in and out, so that only signed-in users can report hazards.

**Feature Issue:** [#123](https://github.com/CS250-Fall-2026-Team-5/RoadWatch/issues/123)

**Acceptance criteria:**
1. Registering with a valid username and password saves the account in the database. (Username: 3+ characters. Password: 8 to 20 characters with at least one uppercase letter, one digit and one special character.)
2. Registering with a password that breaks a rule shows the right error message and no account is saved.
3. Logging in with a saved username and the right password logs the user in.
4. Logging in with a wrong password shows an error and the user stays logged out.
5. After logging out, releasing the pin on the map does not open the Hazard Report form and the top button shows "Login" again.
6. On desktop, a saved account is still there after closing and reopening the app.

**In Scope:**
- Registration screen using the password rules in AccountValidator
- Account table in the SQLite database (desktop)
- Login that checks the username and password
- Logout button
- Unit tests for the validator and the account storage

**Out of Scope:**
- Password reset
- Editing or deleting an account
- Linking hazard reports to the account that made them
- Saving accounts on Android and iOS (they only use in-memory storage)
- Email verification or bot protection

## 2. Top Risks

## 3. Change Control

Any change to the feature after this scope has to be asked for in writing as a GitHub Issue with the label "change request". The person asking for it prices it first: how many days it adds and what it could break or delay. The team then decides together at our next meeting if the answer is yes, no or later. If we say yes, we update the In Scope list, the build plan and the risk register.

## 4. Board Snapshot

![board](screenshots/board.png)

## 5. Build Plan

| Part | Owner | Done by |
|---|---|---|
| Account database | Jordan Baugh | |
| Account registration | Leo Freije | |
| Login | Kolina Katoanga | |
| Logout | Yomna Fassatoui | |
