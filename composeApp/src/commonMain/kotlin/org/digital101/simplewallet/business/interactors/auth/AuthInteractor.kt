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
    val appDataStoreManager: AppDataStore,
) {
    fun authorize(
    ): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.FullScreenLoading))
            val apiResponse = service.authorize()
            /*apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }*/
            val result = apiResponse.result
            if (result != null) {
                appDataStoreManager.setValue(
                    DataStoreKeys.FLOW_ID,
                    result.id
                )
                println("---------------${appDataStoreManager.readValue(DataStoreKeys.FLOW_ID)}--------------------")
            }
            emit(DataState.Data(result?.status, apiResponse.status))
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
            emit(DataState.Loading(progressBarState = ProgressBarState.FullScreenLoading))
            val apiResponse = service.login(
                email,
                password,
                appDataStoreManager.readValue(DataStoreKeys.FLOW_ID) ?: ""
            )
            /*apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }*/
            val result = apiResponse.result
            if (result != null) {
                appDataStoreManager.setValue(
                    DataStoreKeys.USERNAME,
                    result.embedded.user.username
                )
            }
            emit(DataState.Data(result?.status, apiResponse.status))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

    fun resumeForToken(): Flow<DataState<String>> = flow {
        try {
            val apiResponse = service.resumeForToken(
                appDataStoreManager.readValue(DataStoreKeys.FLOW_ID) ?: ""
            )
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
                    DataStoreKeys.RESUME_TOKEN,
                    result.authorizeResponse.code
                )
            }
            emit(DataState.Data(result?.status, apiResponse.status))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }

    fun obtainToken(): Flow<DataState<String>> = flow {
        try {
            val apiResponse = service.obtainToken(
                appDataStoreManager.readValue(DataStoreKeys.FLOW_ID) ?: ""
            )
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
                    AUTHORIZATION_BEARER_TOKEN + result.accessToken
                )
            }
            emit(DataState.Data(result?.accessToken, apiResponse.status))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
