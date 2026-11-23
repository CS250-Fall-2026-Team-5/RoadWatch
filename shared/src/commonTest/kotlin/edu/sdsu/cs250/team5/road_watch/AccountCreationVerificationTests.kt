package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals

class AccountCreationVerificationTests {

    //JB-02
    @Test
    fun usernameMustContain3CharactersTest() {
        assertEquals("Username must contain 3+ characters", AccountValidator.validate("", "P4ssword!"))
    }

    //JB-03
    @Test
    fun passwordRequirementsTest() {
        assertEquals("Password length must be within 8 to 20 characters", AccountValidator.validate("admin", "P4ss"))
        assertEquals("Password must contain a special character", AccountValidator.validate("admin", "Password"))
        assertEquals("Password must contain an uppercase letter", AccountValidator.validate("admin", "password$"))
        assertEquals("Password must contain 1+ digits", AccountValidator.validate("admin", "Password$"))
    }

    //JB-04
    @Test
    fun accountCreationTest() {
        assertEquals("Account Creation Successful", AccountValidator.validate("admin", "P4ssword!"))
    }
}