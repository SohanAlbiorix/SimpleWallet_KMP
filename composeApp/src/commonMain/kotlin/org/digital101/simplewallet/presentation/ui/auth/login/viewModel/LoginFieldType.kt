package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import org.digital101.simplewallet.domain.EditTextState
import org.digital101.simplewallet.domain.FieldType
import org.digital101.simplewallet.domain.TextFieldType

enum class LoginFieldType(val type: FieldType = FieldType.INPUT) : TextFieldType {
    EMAIL,
    PASSWORD,
}

fun TextFieldType.textField(state: LoginState): EditTextState? {
    return when (this) {
        LoginFieldType.EMAIL -> state.email
        LoginFieldType.PASSWORD -> state.password
        else -> null
    }
}

fun EditTextState.updateState(state: LoginState): LoginState {
    return when (this.field) {
        LoginFieldType.EMAIL -> state.copy(email = this)
        LoginFieldType.PASSWORD -> state.copy(password = this)
        else -> state
    }
}