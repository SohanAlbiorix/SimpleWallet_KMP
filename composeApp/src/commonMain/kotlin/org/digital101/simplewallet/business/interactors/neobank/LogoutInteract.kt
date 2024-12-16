package org.digital101.simplewallet.business.interactors.neobank


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.digital101.simplewallet.business.constants.DataStoreKeys
import org.digital101.simplewallet.business.core.AppDataStore
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.util.handleUseCaseException

class LogoutInteract(
    private val appDataStoreManager: AppDataStore,
) {
    fun execute(): Flow<DataState<Boolean>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))
            appDataStoreManager.setValue(DataStoreKeys.TOKEN, "")
            emit(DataState.Data(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
