package org.digital101.simplewallet.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.di.dataModule
import org.digital101.simplewallet.presentation.navigation.AppNavigation
import org.digital101.simplewallet.presentation.theme.AppTheme
import org.digital101.simplewallet.presentation.ui.auth.AuthNav
import org.digital101.simplewallet.presentation.ui.main.MainNav
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
internal fun App(context: Context) {
    KoinApplication(application = {
        modules(dataModule(context))
    }) {
        AppTheme {
            val navigator = rememberNavController()
            val viewModel: SharedViewModel = koinInject()

            LaunchedEffect(key1 = viewModel.tokenManager.state.value.isTokenAvailable) {
                if (!viewModel.tokenManager.state.value.isTokenAvailable) {
                    navigator.popBackStack()
                    navigator.navigate(AppNavigation.Auth)
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
                        MainNav {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Auth)
                        }
                    }
                }
            }
        }
    }
}
