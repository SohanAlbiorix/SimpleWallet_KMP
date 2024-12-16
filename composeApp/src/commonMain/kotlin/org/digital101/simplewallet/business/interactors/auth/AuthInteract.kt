package org.digital101.simplewallet.business.interactors.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.datasource.network.auth.AuthService
import org.digital101.simplewallet.business.util.handleUseCaseException

class AuthInteract(
    private val service: AuthService,
    private val appDataStoreManager: AppDataStore,
) {

    fun execute(
        email: String,
        password: String,
    ): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.FullScreenLoading))
            val authResponse = service.authorize()
            val authResult = authResponse.result
            if (authResult != null) {
                val loginResponse = service.login(email, password, authResult.id)
                val loginResult = loginResponse.result
                if (loginResult != null) {
                    val resumeResponse = service.resumeForToken(authResult.id)
                    val resumeResult = resumeResponse.result
                    if (resumeResult != null) {
                        val tokenResponse = service.obtainToken(resumeResult.authorizeResponse.code)
                        val tokenResult = tokenResponse.result
                        if (tokenResult != null) {
                            appDataStoreManager.setValue(
                                DataStoreKeys.TOKEN,
                                "${tokenResult.tokenType} ${tokenResult.accessToken}"
                            )
                            emit(DataState.Data(status = tokenResponse.status))
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
