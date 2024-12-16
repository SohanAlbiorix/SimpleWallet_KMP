package org.digital101.simplewallet.presentation.navigation

import androidx.compose.material.icons.Icons
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
    data object Home : BottomNavItem(
        route = "Home", label = "Home",
        selectedIcon = Icons.Default.Home,
        unSelectedIcon = Icons.Outlined.Home,
    )

    data object Deposits :
        BottomNavItem(
            route = "Deposits", label = "Deposits",
            selectedIcon = Icons.Default.Person,
            unSelectedIcon = Icons.Outlined.Person,
        )

    data object Finance :
        BottomNavItem(
            route = "Finance", label = "Finance",
            selectedIcon = Icons.Default.Person,
            unSelectedIcon = Icons.Outlined.Person,
        )

    data object Card : BottomNavItem(
        route = "Card", label = "Card",
        selectedIcon = Icons.Default.Person,
        unSelectedIcon = Icons.Outlined.Person,
    )
}