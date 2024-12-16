package org.digital101.simplewallet.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.di.dataModule
import org.digital101.simplewallet.presentation.navigation.AppNavigation
import org.digital101.simplewallet.presentation.theme.AppTheme
import org.digital101.simplewallet.presentation.tokenManager.TokenEvent
import org.digital101.simplewallet.presentation.ui.auth.AuthNav
import org.digital101.simplewallet.presentation.ui.main.MainNav
import org.digital101.simplewallet.presentation.ui.main.profile.ProfileScreen
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

val LocalPlatformContext = compositionLocalOf<Context> {
    error("No PlatformContext provided")
}

@Composable
internal fun App(context: Context) {
    KoinApplication(application = {
        modules(dataModule(context))
    }) {
        CompositionLocalProvider(LocalPlatformContext provides context) {
            AppTheme {
                val navigator = rememberNavController()
                val viewModel: SharedViewModel = koinInject()

                val navBackStackEntry by navigator.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                LaunchedEffect(key1 = viewModel.tokenManager.state.value.isTokenAvailable) {
                    if (!viewModel.tokenManager.state.value.isTokenAvailable) {
                        if (currentRoute?.contains(AppNavigation.Auth.toString()) != true) {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Auth)
                        }
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    NavHost(
                        startDestination = if (viewModel.tokenManager.state.value.isTokenAvailable) AppNavigation.Main else AppNavigation.Auth,
                        modifier = Modifier.fillMaxSize(),
                        navController = navigator,
                    ) {
                        composable<AppNavigation.Auth> {
                            AuthNav(navigateToMain = {
                                navigator.popBackStack()
                                navigator.navigate(AppNavigation.Main)
                            })
                        }
                        composable<AppNavigation.Main> {
                            MainNav(
                                profileViewModel = koinInject(),
                                logout = {
                                    viewModel.tokenManager.onTriggerEvent(TokenEvent.Logout)
                                    navigator.navigate(AppNavigation.Auth)
                                }) {
                                navigator.navigate(AppNavigation.Profile)
                            }
                        }

                        composable<AppNavigation.Profile> {
                            ProfileScreen(
                                viewModel = koinInject(),
                            ) {
                                navigator.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
