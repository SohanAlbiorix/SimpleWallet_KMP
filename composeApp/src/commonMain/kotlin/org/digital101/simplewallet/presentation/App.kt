package org.digital101.simplewallet.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.di.dataModule
import org.digital101.simplewallet.di.viewModelModule
import org.digital101.simplewallet.presentation.navigation.AppNavigation
import org.digital101.simplewallet.presentation.navigation.NavigationGraph
import org.digital101.simplewallet.presentation.theme.JetpackComposeDemoTheme
import org.digital101.simplewallet.presentation.token_manager.TokenEvent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
@Preview
fun App(context: Context) {
    KoinApplication(application = {
        modules(
            dataModule(context),
            viewModelModule,
        )
    }) {
        JetpackComposeDemoTheme {
            val navigator = rememberNavController()
            val viewModel: SharedViewModel = koinInject()
            viewModel.tokenManager.onTriggerEvent(TokenEvent.CheckToken)
            LaunchedEffect(key1 = viewModel.tokenManager.state.value.isTokenAvailable) {
                if (!viewModel.tokenManager.state.value.isTokenAvailable) {
                    navigator.popBackStack()
                    navigator.navigate(AppNavigation.Splash)
                } else {
                    navigator.navigate(AppNavigation.Main)
                }
            }
            Surface {
                NavigationGraph(navController = navigator)
            }
        }
    }
}
