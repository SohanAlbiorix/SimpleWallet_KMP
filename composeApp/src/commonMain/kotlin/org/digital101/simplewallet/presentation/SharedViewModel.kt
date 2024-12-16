package org.digital101.simplewallet.presentation

import androidx.lifecycle.ViewModel
import org.digital101.simplewallet.presentation.tokenManager.TokenEvent
import org.digital101.simplewallet.presentation.tokenManager.TokenManager

class SharedViewModel(
    val tokenManager: TokenManager,
) : ViewModel() {
    init {
        tokenManager.onTriggerEvent(TokenEvent.CheckToken)
    }
}