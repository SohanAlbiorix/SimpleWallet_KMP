package org.digital101.simplewallet.presentation.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.digital101.simplewallet.presentation.component.DEFAULT__BUTTON_SIZE_EXTRA
import org.digital101.simplewallet.presentation.component.DefaultButton
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginEvent
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginState

@Composable
fun LoginScreen(state: LoginState, events: (LoginEvent) -> Unit) {
    DefaultScreenUI {
        Column {
            DefaultButton(
                progressBarState = state.progressBarState,
                onClick = {

                },
                enableElevation = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DEFAULT__BUTTON_SIZE_EXTRA),
                text = "Authorize"
            )
        }
    }
}