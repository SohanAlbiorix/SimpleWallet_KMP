package org.digital101.simplewallet.presentation.ui.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.presentation.navigation.AuthNavigation
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginViewModel
import org.koin.compose.koinInject

@Composable
internal fun AuthNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {
    val navigator = rememberNavController()

    NavHost(
        startDestination = AuthNavigation.Splash,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<AuthNavigation.Splash> {
            SplashScreen(
                state = viewModel.state.value,
                navigateToMain = navigateToMain,
                navigateToLogin = {
                    navigator.popBackStack()
                    navigator.navigate(AuthNavigation.Login)
                },
                events = viewModel::onTriggerEvent,
            )
        }
        composable<AuthNavigation.Login> {
            LoginScreen(
                viewModel,
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToMain = navigateToMain,
            )
        }
    }

}