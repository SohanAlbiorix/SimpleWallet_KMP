package org.digital101.simplewallet.presentation.tokenManager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.interactors.auth.CheckTokenInteract
import org.digital101.simplewallet.business.interactors.auth.LogoutInteract

class TokenManager(
    private val checkTokenInteract: CheckTokenInteract,
    private val logoutInteract: LogoutInteract,
) {
    private val sessionScope = CoroutineScope(Dispatchers.Main)
    val state: MutableState<TokenState> = mutableStateOf(TokenState())

    fun onTriggerEvent(event: TokenEvent) {
        when (event) {
            is TokenEvent.CheckToken -> checkToken()
            is TokenEvent.Logout -> logout()
        }
    }

    private fun checkToken() {
        checkTokenInteract.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {}
                is DataState.Data -> {
                    state.value = state.value.copy(isTokenAvailable = dataState.data ?: false)
                }

                is DataState.Loading -> {}
            }
        }.launchIn(sessionScope)
    }

    private fun logout() {
        logoutInteract.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {}
                is DataState.Data -> {
                    checkToken()
                }

                is DataState.Loading -> {}
            }
        }.launchIn(sessionScope)
    }
}
