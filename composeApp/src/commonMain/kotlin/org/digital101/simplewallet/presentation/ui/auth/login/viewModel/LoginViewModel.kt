package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.digital101.simplewallet.business.constants.TAG
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.business.interactors.auth.AuthInteract
import org.digital101.simplewallet.business.util.isValidEmail
import org.digital101.simplewallet.business.util.isValidPassword
import org.jetbrains.compose.resources.getString
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.validation_password_must_be
import simplewallet.composeapp.generated.resources.validation_please_enter_valid_email

class LoginViewModel(
    private val authInteract: AuthInteract,
) : ViewModel() {

    val state: MutableState<LoginState> = mutableStateOf(
        LoginState(
            usernameLogin = "qian_ming_hui@101digital.io",
            passwordLogin = "Password@12345",
        )
    )

    fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Authorize -> authorize()

            is LoginEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is LoginEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }

            is LoginEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is LoginEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is LoginEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is LoginEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    var emailErrorMessage = MutableStateFlow("")
    var passwordErrorMessage = MutableStateFlow("")

    private fun authorize() {
        authInteract.execute(
            email = state.value.usernameLogin,
            password = state.value.passwordLogin,
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(LoginEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(
                        navigateToMain = dataState.data != null
                    )
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(
                        progressBarState = dataState.progressBarState,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onUpdatePasswordLogin(value: String) {
        state.value = state.value.copy(passwordLogin = value)
        viewModelScope.launch {
            passwordErrorMessage.value =
                if (isValidPassword(value)) getString(Res.string.validation_password_must_be) else ""
        }
    }

    private fun onUpdateUsernameLogin(value: String) {
        state.value = state.value.copy(usernameLogin = value)
        viewModelScope.launch {
            emailErrorMessage.value =
                if (isValidEmail(value)) getString(Res.string.validation_please_enter_valid_email) else ""
        }
    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("$TAG: onTriggerEvent:  ${uiComponent.message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("$TAG: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {
    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }
}