package org.digital101.simplewallet.presentation.ui.auth.login

import CommonEditTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.digital101.simplewallet.presentation.component.DefaultButton
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginEvent
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginState
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.app_logo
import simplewallet.composeapp.generated.resources.label_app_logo
import simplewallet.composeapp.generated.resources.label_email
import simplewallet.composeapp.generated.resources.label_password
import simplewallet.composeapp.generated.resources.label_welcome_back
import simplewallet.composeapp.generated.resources.txt_log_in
import simplewallet.composeapp.generated.resources.txt_trouble_logging_in

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    state: LoginState,
    events: (LoginEvent) -> Unit,
    navigateToMain: () -> Unit
) {
    LaunchedEffect(state.navigateToMain) {
        if (state.navigateToMain) navigateToMain()
    }

    val emailErrorMessage = viewModel.emailErrorMessage.collectAsState().value
    val passwordErrorMessage = viewModel.passwordErrorMessage.collectAsState().value

    DefaultScreenUI(
        queue = state.errorQueue,
        onRemoveHeadFromQueue = { events(LoginEvent.OnRemoveHeadFromQueue) },
        progressBarState = state.progressBarState,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.app_logo),
                contentDescription = stringResource(Res.string.label_app_logo),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(top = 56.dp))

            Text(
                text = stringResource(Res.string.label_welcome_back),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                ),
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            CommonEditTextField(
                text = state.usernameLogin,
                placeHolderText = stringResource(Res.string.label_email),
                labelText = stringResource(Res.string.label_email),
                isError = emailErrorMessage.isNotEmpty(),
                errorMsg = emailErrorMessage,
                onchange = { inputText ->
                    events(LoginEvent.OnUpdateUsernameLogin(inputText))
                },
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            CommonEditTextField(
                text = state.passwordLogin,
                placeHolderText = stringResource(Res.string.label_password),
                labelText = stringResource(Res.string.label_password),
                isError = passwordErrorMessage.isNotEmpty(),
                errorMsg = passwordErrorMessage,
                isPassword = true,
                onchange = { inputText ->
                    events(LoginEvent.OnUpdatePasswordLogin(inputText))
                },
                onPasswordVisibilityToggle = {
                }
            )

            Spacer(modifier = Modifier.padding(top = 28.dp))

            Text(
                text = stringResource(Res.string.txt_trouble_logging_in),
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.secondary
                ),
            )

            Spacer(modifier = Modifier.weight(1f))

            DefaultButton(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (emailErrorMessage.isEmpty() && passwordErrorMessage.isEmpty())
                        events(LoginEvent.Authorize)
                },
                text = stringResource(Res.string.txt_log_in),
            )
        }
    }
}
