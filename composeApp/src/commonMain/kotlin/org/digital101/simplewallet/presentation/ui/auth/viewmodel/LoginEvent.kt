package org.digital101.simplewallet.presentation.ui.auth.viewmodel

import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.UIComponent

sealed class LoginEvent {


    data object Authorize : LoginEvent()
    data object Login : LoginEvent()
    data object OnRemoveHeadFromQueue : LoginEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : LoginEvent()


    data object OnRetryNetwork : LoginEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : LoginEvent()
}
