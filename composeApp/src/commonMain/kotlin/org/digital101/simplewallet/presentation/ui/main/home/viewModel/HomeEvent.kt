package org.digital101.simplewallet.presentation.ui.main.home.viewModel

import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileEvent

sealed class HomeEvent {
    data object LoadWallet : HomeEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : HomeEvent()
}