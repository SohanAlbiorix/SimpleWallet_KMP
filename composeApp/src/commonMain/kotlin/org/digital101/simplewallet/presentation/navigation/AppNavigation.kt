package org.digital101.simplewallet.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation {

    @Serializable
    data object Auth : AppNavigation

    @Serializable
    data object Main : AppNavigation

}
