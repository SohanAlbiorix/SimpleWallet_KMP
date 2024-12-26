package org.digital101.simplewallet.presentation.navigation

import org.jetbrains.compose.resources.DrawableResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.card
import simplewallet.composeapp.generated.resources.deposite
import simplewallet.composeapp.generated.resources.finance
import simplewallet.composeapp.generated.resources.home

sealed class BottomNavItem(
    val route: String,
    val icon: DrawableResource,
    val label: String
) {
    data object Home : BottomNavItem(
        route = "Home", label = "Home",
        icon = Res.drawable.home
    )

    data object Deposits :
        BottomNavItem(
            route = "Deposits", label = "Deposits",
            icon = Res.drawable.deposite
        )

    data object Finance :
        BottomNavItem(
            route = "Finance", label = "Finance",
            icon = Res.drawable.finance
        )

    data object Card : BottomNavItem(
        route = "Card", label = "Card",
        icon = Res.drawable.card
    )
}