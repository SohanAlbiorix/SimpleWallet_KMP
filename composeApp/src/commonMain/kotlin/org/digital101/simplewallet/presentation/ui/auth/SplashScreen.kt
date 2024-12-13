package org.digital101.simplewallet.presentation.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginEvent
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginState

@Composable
fun SplashScreen(
    state: LoginState,
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit,
    events: (LoginEvent) -> Unit
) {

    LaunchedEffect(state.navigateToMain) {
        events(LoginEvent.Authorize)
        delay(1000L)
        if (state.navigateToMain) {
            navigateToMain()
        } else {
            navigateToLogin()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.BottomCenter
    ) {

    }
}
