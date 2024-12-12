package org.digital101.simplewallet.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Routes(val name: String) {
    data object Home : Routes("Home") {
        data object Overview : Routes("Home.Overview")
    }

    data object Deposits : Routes("Deposits") {
        data object Overview : Routes("Deposits.Overview")
    }

    data object Finance : Routes("Finance") {
        data object Finance : Routes("Finance.Overview")
    }

    data object Card : Routes("Card") {
        data object Overview : Routes("Card.Overview")
    }

    data object Alerts : Routes("Alerts") {
        data object Logout : Routes("Alerts.Logout")
        data object ProfileUpdate : Routes("Alerts.ProfileUpdate")
    }
}

sealed class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val label: String
) {
    data object Home :
        BottomNavItem(Routes.Home.name, Icons.Default.Home, Icons.Outlined.Home, "Home")

    data object Deposits :
        BottomNavItem(Routes.Deposits.name, Icons.Default.Person, Icons.Outlined.Person, "Deposits")

    data object Finance :
        BottomNavItem(Routes.Finance.name, Icons.Default.Person, Icons.Outlined.Person, "Finance")

    data object Card :
        BottomNavItem(Routes.Card.name, Icons.Default.Person, Icons.Outlined.Person, "Card")

    data object LogoutAlert :
        BottomNavItem(
            Routes.Alerts.Logout.name,
            Icons.AutoMirrored.Default.ExitToApp,
            Icons.AutoMirrored.Default.ExitToApp,
            "Logout"
        )
}