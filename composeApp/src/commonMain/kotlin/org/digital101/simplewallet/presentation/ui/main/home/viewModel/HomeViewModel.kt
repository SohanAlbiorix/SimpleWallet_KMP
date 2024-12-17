package org.digital101.simplewallet.presentation.ui.main.home.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.interactors.neobank.UserInteract
import org.digital101.simplewallet.business.interactors.neobank.WalletInteract


class HomeViewModel(
    private val userInteract: UserInteract,
    private val walletInteract: WalletInteract,
) : ViewModel() {

    val state: MutableState<HomeState> = mutableStateOf(HomeState())

    fun onTriggerEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Error -> {

            }

            HomeEvent.LoadWallet -> {
                loadWallet()
            }
        }
    }

    init {
        loadProfile()
    }

    private fun loadProfile() {
        userInteract.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(HomeEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(
                        data = dataState.data,
                    )
                    onTriggerEvent(HomeEvent.LoadWallet)
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(
                        progressBarState = dataState.progressBarState,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun loadWallet() {
        walletInteract.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(HomeEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(
                        wallet = dataState.data,
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
}