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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.digital101.simplewallet.domain.FieldType
import org.digital101.simplewallet.presentation.component.DefaultButton
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginEvent
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginFieldType
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginState
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.isValid
import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.textField
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.app_logo
import simplewallet.composeapp.generated.resources.label_app_logo
import simplewallet.composeapp.generated.resources.label_welcome_back
import simplewallet.composeapp.generated.resources.txt_log_in
import simplewallet.composeapp.generated.resources.txt_trouble_logging_in

@Composable
fun LoginScreen(
    state: LoginState,
    events: (LoginEvent) -> Unit,
    navigateToMain: () -> Unit
) {
    LaunchedEffect(state.navigateToMain) {
        if (state.navigateToMain) navigateToMain()
    }

    DefaultScreenUI(
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        queue = state.errorQueue,
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
            LoginFieldType.entries.forEachIndexed { index, type ->
                when (type.type) {
                    FieldType.INPUT -> type.textField(state)?.let { field ->
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        CommonEditTextField(
                            state = field,
                            labelText = stringResource(field.label),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = when {
                                    LoginFieldType.entries.size - 1 == index -> ImeAction.Done
                                    else -> ImeAction.Next
                                }
                            ),
                            isPassword = type == LoginFieldType.PASSWORD,
                            onChange = { text ->
                                events(
                                    LoginEvent.UpdateValue(
                                        value = text,
                                        field = type,
                                        validation = field.validation,
                                    )
                                )
                            }
                        )
                    }

                    else -> Unit
                }
            }
            Spacer(modifier = Modifier.padding(top = 24.dp))
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
                enabled = state.isValid,
                onClick = { events(LoginEvent.Authorize) },
                text = stringResource(Res.string.txt_log_in),
            )
        }
    }
}