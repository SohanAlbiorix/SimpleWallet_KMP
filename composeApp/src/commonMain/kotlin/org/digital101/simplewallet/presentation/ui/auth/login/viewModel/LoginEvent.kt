package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.UIComponent

sealed class LoginEvent {


    data object Authorize : LoginEvent()

    data class OnUpdateUsernameLogin(val value: String) : LoginEvent()
    data class OnUpdatePasswordLogin(val value: String) : LoginEvent()
    data object OnRemoveHeadFromQueue : LoginEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : LoginEvent()


    data object OnRetryNetwork : LoginEvent()

    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : LoginEvent()
}
