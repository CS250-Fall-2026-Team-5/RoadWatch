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

        if (password.length !in 8..20)
        {
            return "Password length must be within 8 to 20 characters"
        }

        if (hasSpecialChar(password) == false)
        {
            return "Password must contain a special character"
        }

        if (hasUppercaseChar(password) == false)
        {
            return "Password must contain an uppercase letter"
        }

        if (hasDigit(password) == false)
        {
            return "Password must contain 1+ digits"
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