package org.digital101.simplewallet.presentation.tokenManager

sealed class TokenEvent {
    data object CheckToken : TokenEvent()
    data object Logout : TokenEvent()

}