package org.digital101.simplewallet.presentation.ui.auth.login.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.business.interactors.auth.AuthInteract
import org.digital101.simplewallet.business.util.isValidEmail
import org.digital101.simplewallet.business.util.isValidPassword
import org.digital101.simplewallet.business.util.isValidText
import org.digital101.simplewallet.domain.errorMessage
import org.digital101.simplewallet.domain.updateValue

class LoginViewModel(
    private val authInteract: AuthInteract,
) : ViewModel() {

    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Error -> {
                val queue: Queue<UIComponent> = state.value.errorQueue
                queue.add(event.uiComponent)
                state.value = state.value.copy(errorQueue = queue)
            }

            is LoginEvent.UpdateValue -> validateOrUpdate(event)

            LoginEvent.Authorize -> authorize()
        }
    }

    private fun validateOrUpdate(event: LoginEvent.UpdateValue) {
        if (event.validation) {
            val isValid = when (event.field) {
                LoginFieldType.EMAIL -> event.value.isValidEmail
                LoginFieldType.PASSWORD -> event.value.isValidPassword
                else -> event.value.isValidText
            }
            event.field.textField(state.value)
                ?.updateValue(event.value)
                ?.copy(
                    hasError = !isValid,
                    errorMessage = when {
                        !isValid -> event.field.errorMessage
                        else -> null
                    }
                )
                ?.updateState(state.value)
                ?.let { state.value = it }
        } else {
            event.field.textField(state.value)
                ?.updateValue(event.value)
                ?.updateState(state.value)
                ?.let { state.value = it }
        }
    }

    private fun authorize() {
        authInteract.execute(
            email = state.value.email.value,
            password = state.value.password.value,
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    onTriggerEvent(LoginEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(navigateToMain = dataState.data != null)
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(progressBarState = dataState.progressBarState)
                }

                else -> Unit
            }
        }.launchIn(viewModelScope)
    }
}