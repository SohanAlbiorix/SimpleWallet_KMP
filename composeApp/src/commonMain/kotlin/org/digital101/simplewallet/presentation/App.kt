package org.digital101.simplewallet.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.digital101.simplewallet.common.Context
import org.digital101.simplewallet.di.dataModule
import org.digital101.simplewallet.di.viewModelModule
import org.digital101.simplewallet.presentation.navigation.NavigationGraph
import org.digital101.simplewallet.presentation.theme.JetpackComposeDemoTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

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

            Surface {
                NavigationGraph(navController = navigator, context)
            }
        }
    }
}
