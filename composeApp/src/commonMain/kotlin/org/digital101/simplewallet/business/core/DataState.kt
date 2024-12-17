package org.digital101.simplewallet.business.core

import org.digital101.simplewallet.business.network.common.Status

sealed class DataState<T> {

    data class NetworkStatus<T>(val networkState: NetworkState) : DataState<T>()

    data class Response<T>(val uiComponent: UIComponent) : DataState<T>()

    data class Data<T>(val data: T? = null, val status: Status? = null) : DataState<T>()

    data class Loading<T>(val progressBarState: ProgressBarState = ProgressBarState.Idle) :
        DataState<T>()

}