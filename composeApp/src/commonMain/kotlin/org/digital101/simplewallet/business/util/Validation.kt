package org.digital101.simplewallet.business.util

fun isValidEmail(email: String): Boolean {
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    return email.isEmpty() || !email.matches(regex)
}

fun isValidPassword(password: String): Boolean {
    val regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,15}$")
    return password.isEmpty() || !password.matches(regex)
}