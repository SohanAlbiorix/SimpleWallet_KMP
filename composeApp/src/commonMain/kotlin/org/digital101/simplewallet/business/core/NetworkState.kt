package org.digital101.simplewallet.business.core

sealed class NetworkState {

    data object Good : NetworkState()

    data object Failed : NetworkState()

}
