package org.digital101.simplewallet.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val label: String
) {
    data object Home :
        BottomNavItem("Home", Icons.Default.Home, Icons.Outlined.Home, "Home")

    data object Deposits :
        BottomNavItem("Deposits", Icons.Default.Person, Icons.Outlined.Person, "Deposits")

    data object Finance :
        BottomNavItem("Finance", Icons.Default.Person, Icons.Outlined.Person, "Finance")

    data object Card :
        BottomNavItem("Card", Icons.Default.Person, Icons.Outlined.Person, "Card")

    data object LogoutAlert :
        BottomNavItem(
            "Alerts",
            Icons.AutoMirrored.Default.ExitToApp,
            Icons.AutoMirrored.Default.ExitToApp,
            "Logout"
        )
}