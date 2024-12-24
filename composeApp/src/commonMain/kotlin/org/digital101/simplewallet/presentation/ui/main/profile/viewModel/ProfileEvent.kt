package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.domain.TextFieldType

sealed class ProfileEvent {
    data object UpdateProfile : ProfileEvent()
    data class UpdateValue(
        val field: TextFieldType,
        val validation: Boolean,
        val value: String,
    ) : ProfileEvent()
    data class Error(val uiComponent: UIComponent) : ProfileEvent()
}