package org.digital101.simplewallet.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface MainNavigation {

    @Serializable
    data object Settings : MainNavigation

    @Serializable
    data object Profile : MainNavigation

    @Serializable
    data object Logout : MainNavigation
}