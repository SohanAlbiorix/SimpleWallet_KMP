package org.digital101.simplewallet.presentation.ui.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.presentation.navigation.AuthNavigation
import org.digital101.simplewallet.presentation.ui.auth.login.LoginScreen
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginViewModel
import org.koin.compose.koinInject

@Composable
internal fun AuthNav(viewModel: LoginViewModel = koinInject(), navigateToMain: () -> Unit) {
    val navigator = rememberNavController()
    NavHost(
        startDestination = AuthNavigation.Login,
        modifier = Modifier.fillMaxSize(),
        navController = navigator,
    ) {
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