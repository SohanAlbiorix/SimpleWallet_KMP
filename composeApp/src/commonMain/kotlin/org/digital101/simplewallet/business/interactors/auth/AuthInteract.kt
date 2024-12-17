package org.digital101.simplewallet.business.interactors.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.network.pingOne.PingOneService
import org.digital101.simplewallet.business.util.handleUseCaseException

class AuthInteract(
    private val service: PingOneService,
    private val appDataStoreManager: AppDataStore,
) {

    fun execute(
        email: String,
        password: String,
    ): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.FullScreenLoading))
            val authResponse = service.authorize()
            if (authResponse?.id != null) {
                val loginResponse = service.login(email, password, authResponse.id)
                if (loginResponse != null) {
                    val resumeResponse = service.resumeForToken(authResponse.id)
                    if (resumeResponse != null) {
                        val tokenResponse =
                            service.obtainToken(resumeResponse.authorizeResponse.code)
                        if (tokenResponse != null) {
                            appDataStoreManager.setValue(
                                DataStoreKeys.TOKEN,
                                "${tokenResponse.tokenType} ${tokenResponse.accessToken}"
                            )
                            emit(DataState.Data(tokenResponse.tokenType))
                        }
                    }
                }
            }
        } catch (e: Exception) {
            emit(DataState.NetworkStatus(NetworkState.Failed))
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
