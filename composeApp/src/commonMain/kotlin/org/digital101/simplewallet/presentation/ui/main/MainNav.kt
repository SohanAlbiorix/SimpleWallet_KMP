package org.digital101.simplewallet.presentation.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.presentation.component.bottomBar
import org.digital101.simplewallet.presentation.navigation.BottomNavItem
import org.digital101.simplewallet.presentation.navigation.MainNavigation
import org.digital101.simplewallet.presentation.ui.main.profile.ProfileScreen
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.koin.compose.koinInject

@Composable
fun MainNav(logout: () -> Unit) {
    val navBottomBarController = rememberNavController()
    val profileViewModel: ProfileViewModel = koinInject()

    Scaffold(bottomBar = {
        bottomBar(navBottomBarController)
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                startDestination = BottomNavItem.Home.route,
                navController = navBottomBarController,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = BottomNavItem.Home.route) {
//                    HomeScreen(navBottomBarController, context)
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
                composable<MainNavigation.Profile> {
                    ProfileScreen(
                        viewModel = profileViewModel,
                        onBackClick = { navBottomBarController.popBackStack() }
                    )
                }

                /* dialog("Alerts") {
                    AlertDialog(title = {
                        Text(
                            text = "Alert",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    }, text = {
                        Text(
                            text = "Are you sure want to logout?",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    }, onDismissRequest = {
                        navController.navigateUp()
                    }, confirmButton = {
                        ElevatedButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Text(
                                text = "Logout",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.inversePrimary
                            )
                        }
                    })
                } */
            }
        }

    }
}