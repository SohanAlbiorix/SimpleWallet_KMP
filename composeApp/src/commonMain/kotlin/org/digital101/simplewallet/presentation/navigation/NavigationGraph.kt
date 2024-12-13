package org.digital101.simplewallet.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import org.digital101.simplewallet.presentation.ui.auth.AuthNav
import org.digital101.simplewallet.presentation.ui.home.MainNav

@Composable
fun NavigationGraph(navController: NavHostController) {

    Box {
        NavHost(
            navController = navController,
            startDestination = AppNavigation.Splash
        ) {

            composable<AppNavigation.Splash> {
                AuthNav(navigateToMain = {
                    navController.popBackStack()
                    navController.navigate(AppNavigation.Main)
                })
            }

            composable<AppNavigation.Main> {
                MainNav {
                    navController.popBackStack()
                    navController.navigate(AppNavigation.Splash)
                }
            }

            dialog("Alerts") {
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
            }
        }
    }
}
