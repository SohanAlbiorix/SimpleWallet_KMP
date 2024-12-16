package org.digital101.simplewallet.business.interactors.neobank

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.network.neo.NeoService
import org.digital101.simplewallet.business.network.neo.responses.UserData
import org.digital101.simplewallet.business.util.handleUseCaseException

class UserInteract(
    private val service: NeoService,
    private val appDataStoreManager: AppDataStore,
) {

    fun execute(): Flow<DataState<UserData>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.FullScreenLoading))
            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""
            val response = service.user(token)
            val result = response.result
            if (result != null) {
                emit(DataState.Data(result.data, status = true))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.NetworkStatus(NetworkState.Failed))
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}