package org.digital101.simplewallet.business.interactors.auth


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.util.handleUseCaseException

class CheckTokenInteract(
    private val appDataStoreManager: AppDataStore,
) {
    fun execute(): Flow<DataState<Boolean>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))
            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""
            val isTokenValid = token.isNotEmpty()
            emit(DataState.Data(isTokenValid))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}