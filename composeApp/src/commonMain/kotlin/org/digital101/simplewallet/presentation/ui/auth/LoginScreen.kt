package org.digital101.simplewallet.presentation.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginEvent

@Composable
fun LoginScreen(events: (LoginEvent) -> Unit) {
    Column {
        Button(
            onClick = {
                events(LoginEvent.Authorize)
            }
        ) {
            Text("Authorize")
        }
    }
}