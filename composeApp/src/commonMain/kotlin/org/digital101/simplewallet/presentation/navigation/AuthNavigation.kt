package org.digital101.simplewallet.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthNavigation {

    @Serializable
    data object Splash : AuthNavigation

    @Serializable
    data object Login : AuthNavigation

}

