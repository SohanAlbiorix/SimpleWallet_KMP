package org.digital101.simplewallet.presentation.ui.auth

import CommonEditTextField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginEvent
import org.digital101.simplewallet.presentation.ui.auth.viewmodel.LoginState
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.label_email
import simplewallet.composeapp.generated.resources.label_password

@Composable
fun LoginScreen(state: LoginState, events: (LoginEvent) -> Unit) {

    val email = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }

    DefaultScreenUI {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)
        ) {

            CommonEditTextField(
                text = email,
                placeHolderText = stringResource(Res.string.label_email),
                labelText = stringResource(Res.string.label_email),
                isError = emailError,
                errorMsg = emailErrorMessage,
                onchange = { inputText ->
                    email.value = TextFieldValue(inputText, selection = TextRange(inputText.length))
                    if (inputText.isEmpty()) {
                        emailError = true
                    } else {
                        emailError = false
                        emailErrorMessage = ""
                }
            }
        )
        CommonEditTextField(
            text = password,
            placeHolderText = stringResource(Res.string.label_password),
            labelText = stringResource(Res.string.label_password),
            isError = passwordError,
            errorMsg = passwordErrorMessage,
            isPassword = true,
            onchange = { inputText ->
                password.value = TextFieldValue(inputText, selection = TextRange(inputText.length))
                if (inputText.isEmpty()) {
                    passwordError = true
                } else {
                    passwordError = false
                    passwordErrorMessage = ""
                }
            },
            onPasswordVisibilityToggle = {
            }
        )

        Button(
            onClick = {
                events(LoginEvent.Authorize)
            }
        ) {
            Text("Authorize")
        }
    }
    }
}
