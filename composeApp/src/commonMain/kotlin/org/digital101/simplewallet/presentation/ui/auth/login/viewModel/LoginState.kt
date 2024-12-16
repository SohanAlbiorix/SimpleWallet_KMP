package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent

data class LoginState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,
    val navigateToMain: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
