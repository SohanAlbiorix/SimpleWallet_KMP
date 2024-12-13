package org.digital101.simplewallet.presentation

import androidx.lifecycle.ViewModel
import org.digital101.simplewallet.presentation.token_manager.TokenManager

class SharedViewModel(
    val tokenManager: TokenManager,
) : ViewModel()