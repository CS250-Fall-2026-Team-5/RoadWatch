package edu.sdsu.cs250.team5.road_watch

object AccountValidator {

    fun validate(
        username: String,
        password: String
    ): String? {

        if (username.length < 3)
        {
            return "Username must contain 3+ characters"
        }
    
        return "Account Creation Successful"
    }

    fun hasSpecialChar(password: String): Boolean {
    return password.any { !it.isLetterOrDigit() && !it.isWhitespace() }
    }

    fun hasUppercaseChar(password: String): Boolean {
    return password.any { it.isUpperCase() }
    }

    fun hasDigit(password: String): Boolean {
    return password.any { it.isDigit() }
    }
}   