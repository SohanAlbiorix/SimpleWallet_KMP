package org.digital101.simplewallet.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import org.digital101.simplewallet.presentation.component.bottomBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.back

@Composable
fun NavigationGraph(navController: NavHostController) {

    val viewModel = koinViewModel<NavigationGraphViewModel>()
    val isTopBar = viewModel.isTopBar.collectAsState().value

    Scaffold(
        bottomBar = { bottomBar(navController) },
        topBar = {
            if (isTopBar) {
                @OptIn(ExperimentalMaterial3Api::class)
                (TopAppBar(
                    colors = TopAppBarColors(
                        actionIconContentColor = MaterialTheme.colorScheme.primary,
                        containerColor = MaterialTheme.colorScheme.inversePrimary,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.primary,
                        scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("VISIBLE")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                stringResource(Res.string.back)
                            )
                        }
                    }
                ))
            }
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Routes.Home.name
        ) {
            navigation(
                route = Routes.Home.name,
                startDestination = Routes.Home.Overview.name,
            ) {
                composable(
                    Routes.Home.Overview.name, deepLinks = listOf(
                        navDeepLink {
                            uriPattern = "myapp://${Routes.Home.name}"
                        } // Note that this pattern has no relation to the route itself
                    )) {
                    viewModel.isTopBar.value = false
                    /*ListScreen(navigateToDetails = { objectId ->
                        navController.navigate(
                            Routes.Home.Detail.createRoute(objectId)
                        )
                    })*/
                }
            }
            navigation(
                route = Routes.Deposits.name,
                startDestination = Routes.Deposits.Overview.name,
            ) {
                composable(
                    Routes.Deposits.Overview.name, deepLinks = listOf(
                        navDeepLink {
                            uriPattern = "myapp://${Routes.Deposits.name}"
                        } // Note that this pattern has no relation to the route itself
                    )) {
                    viewModel.isTopBar.value = false
                    /*ProfileScreen(navigateToDetails = { objectId ->
                        navController.navigate(
                            Routes.Profile.Detail.createRoute(objectId)
                        )
                    })*/
                }
            }

            dialog(Routes.Alerts.Logout.name) {
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
