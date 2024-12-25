package org.digital101.simplewallet.business.util

object Validator {
    const val EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
    const val PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,15}\$"
}

private val String.toRegex: Regex
    get() = Regex(this)

val String.isValidText: Boolean
    get() = this.isNotEmpty()

val String.isValidEmail: Boolean
    get() = this.isValidText && this.matches(Validator.EMAIL.toRegex)

val String.isValidPassword: Boolean
    get() = this.isValidText && this.matches(Validator.PASSWORD.toRegex)