package org.digital101.simplewallet.presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.digital101.simplewallet.presentation.navigation.BottomNavItem
import org.digital101.simplewallet.presentation.navigation.MainNavigation
import org.digital101.simplewallet.presentation.ui.main.home.HomeScreen
import org.digital101.simplewallet.presentation.ui.main.settings.AccountSettings
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainNavigation(
    logout: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    val navBottomBarController = rememberNavController()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AccountSettings(logout = {
                navBottomBarController.navigate(MainNavigation.Logout)
            }, navigateToProfile = navigateToProfile) {
                coroutineScope.launch { drawerState.close() }
            }
        }
    ) {
        Scaffold(bottomBar = {
            BottomNavigationUI(navBottomBarController)
        }) { innerPadding ->
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {
                NavHost(
                    startDestination = BottomNavItem.Home.route,
                    navController = navBottomBarController,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = BottomNavItem.Home.route) {
                        HomeScreen {
                            if (drawerState.isClosed) {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        }
                    }
                    composable(route = BottomNavItem.Deposits.route) {
//                    WishlistNav()
                    }
                    composable(route = BottomNavItem.Finance.route) {
//                    CartNav()
                    }
                    composable(route = BottomNavItem.Card.route) {
//                    ProfileNav(logout = logout)
                    }

                    dialog<MainNavigation.Logout> {
                        AlertDialog(
                            containerColor = MaterialTheme.colorScheme.background,
                            title = {
                                Text(
                                    text = "Alert",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                            }, text = {
                                Text(
                                    text = "Are you sure want to logout?",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                            }, onDismissRequest = {
                                navBottomBarController.navigateUp()
                            }, confirmButton = {
                                ElevatedButton(onClick = {
                                    navBottomBarController.navigateUp()
                                    logout()
                                }) {
                                    Text(
                                        text = "Logout",
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationUI(
    navController: NavController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        )
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.tertiary,
            tonalElevation = 8.dp,
        ) {
            val items = listOf(
                BottomNavItem.Home,
                BottomNavItem.Deposits,
                BottomNavItem.Finance,
                BottomNavItem.Card,
            )
            items.forEach {
                val selected = it.route == currentRoute
                NavigationBarItem(
                    label = {
                        Text(
                            text = it.label,
                            color = if (selected) Color.Black else MaterialTheme.colorScheme.outlineVariant
                        )
                    },
                    selected = it.route == currentRoute,
                    icon = {
                        Icon(
                            painter = painterResource(it.icon),
                            tint = if (selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.outlineVariant,
                            contentDescription = it.label,
                        )
                    },
                    onClick = {
                        if (currentRoute != it.route) {
                            navController.navigate(it.route) {
                                navController.graph.findStartDestination().route?.let { route ->
                                    popUpTo(route) {
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
}