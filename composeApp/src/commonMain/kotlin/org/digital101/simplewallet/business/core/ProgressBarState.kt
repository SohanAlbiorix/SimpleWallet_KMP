package org.digital101.simplewallet.business.core

sealed class ProgressBarState {

    data object ButtonLoading : ProgressBarState()

    data object ScreenLoading : ProgressBarState()

    data object FullScreenLoading : ProgressBarState()

    data object Idle : ProgressBarState()
}

