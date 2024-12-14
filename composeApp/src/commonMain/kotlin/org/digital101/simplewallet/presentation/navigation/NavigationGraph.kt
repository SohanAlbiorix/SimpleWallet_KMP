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
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.presentation.SharedViewModel
import org.digital101.simplewallet.presentation.token_manager.TokenEvent
import org.digital101.simplewallet.presentation.ui.auth.AuthNav
import org.digital101.simplewallet.presentation.ui.dashboard.MainNav
import org.koin.compose.koinInject

@Composable
fun NavigationGraph(navController: NavHostController, context: Context) {
    val viewModel: SharedViewModel = koinInject()
    viewModel.tokenManager.onTriggerEvent(TokenEvent.CheckToken)
    Box {
        NavHost(
            navController = navController,
            startDestination = if (!viewModel.tokenManager.state.value.isTokenAvailable) AppNavigation.Splash else AppNavigation.Main
        ) {

            composable<AppNavigation.Splash> {
                AuthNav(navigateToMain = {
                    navController.popBackStack()
                    navController.navigate(AppNavigation.Main)
                })
            }

            composable<AppNavigation.Main> {
                MainNav(context) {
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
