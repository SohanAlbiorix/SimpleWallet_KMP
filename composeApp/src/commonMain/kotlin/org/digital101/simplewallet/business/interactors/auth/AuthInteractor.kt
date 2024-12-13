package org.digital101.simplewallet.business.interactors.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.digital101.simplewallet.business.constants.AUTHORIZATION_BEARER_TOKEN
import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.business.datasource.network.auth.AuthService
import org.digital101.simplewallet.business.util.handleUseCaseException

class AuthInteractor(
    private val service: AuthService,
    private val appDataStoreManager: AppDataStore,
) {
    fun authorize(
    ): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))
            val apiResponse = service.authorize()
            apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }
            val result = apiResponse.result
            if (result != null) {
                appDataStoreManager.setValue(
                    DataStoreKeys.TOKEN,
                    AUTHORIZATION_BEARER_TOKEN + result
                )
            }
            emit(DataState.Data(result, apiResponse.status))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

    fun login(
        email: String,
        password: String,
    ): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))
            val apiResponse = service.login(email, password)
            apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }
            val result = apiResponse.result
            if (result != null) {
                appDataStoreManager.setValue(
                    DataStoreKeys.TOKEN,
                    AUTHORIZATION_BEARER_TOKEN + result
                )
                appDataStoreManager.setValue(
                    DataStoreKeys.EMAIL,
                    email
                )
            }
            emit(DataState.Data(result, apiResponse.status))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}