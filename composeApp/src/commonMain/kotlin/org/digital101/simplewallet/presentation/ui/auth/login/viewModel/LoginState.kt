package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.domain.EditTextState
import org.digital101.simplewallet.domain.isValid
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.label_email
import simplewallet.composeapp.generated.resources.label_password

data class LoginState(
    val email: EditTextState = EditTextState(
        field = LoginFieldType.EMAIL,
        label = Res.string.label_email,
        validation = true,
        value = "qian_ming_hui@101digital.io",
    ),
    val password: EditTextState = EditTextState(
        field = LoginFieldType.PASSWORD,
        label = Res.string.label_password,
        validation = true,
        value = "Password@12345",
    ),

    val navigateToMain: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)

val LoginState.isValid
    get() = email.isValid && password.isValid