package org.digital101.simplewallet.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.digital101.simplewallet.presentation.navigation.BottomNavItem

val bottomBar: @Composable (NavHostController) -> Unit = { navController ->
    val screens = listOf(
        BottomNavItem.Home, BottomNavItem.Deposits, BottomNavItem.Finance, BottomNavItem.Card,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.secondary) {
        screens.forEach { screen ->
            val isSelected = currentRoute?.contains(screen.route)
            NavigationBarItem(
                selected = isSelected ?: false,
                label = { Text(text = screen.label) },
                icon = {
                    Icon(
                        imageVector = if (isSelected == true) screen.selectedIcon else screen.unSelectedIcon,
                        contentDescription = screen.label,
                    )
                },
                onClick = {
                    navController.navigate(screen.route) {
                        if (!screen.route.contains("Alerts")) {
                            navController.graph.findStartDestination().route?.let {
                                popUpTo(it) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent,
                ),
            )
        }
    }
}