package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.domain.TextFieldType

sealed class LoginEvent {
    data object Authorize : LoginEvent()
    data class UpdateValue(
        val field: TextFieldType,
        val validation: Boolean,
        val value: String,
    ) : LoginEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : LoginEvent()
}
