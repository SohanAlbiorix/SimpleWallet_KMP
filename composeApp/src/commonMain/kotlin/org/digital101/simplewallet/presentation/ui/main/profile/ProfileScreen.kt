package org.digital101.simplewallet.presentation.ui.main.profile

import CommonEditTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.digital101.simplewallet.domain.FieldType
import org.digital101.simplewallet.domain.ToolBarButton
import org.digital101.simplewallet.presentation.component.DefaultButton
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileEvent
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.isValid
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.textField
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.doneimage
import simplewallet.composeapp.generated.resources.label_done
import simplewallet.composeapp.generated.resources.label_employment_details
import simplewallet.composeapp.generated.resources.label_mailing_address
import simplewallet.composeapp.generated.resources.label_my_profile
import simplewallet.composeapp.generated.resources.label_personal_details
import simplewallet.composeapp.generated.resources.label_update

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val state = viewModel.state.value
    val events = viewModel::onTriggerEvent

    // State to toggle dialog visibility
    DefaultScreenUI(
        title = stringResource(Res.string.label_my_profile),
        leading = ToolBarButton(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = onBackClick,
            description = "Back",
        ),
        progressBarState = state.progressBarState,
        networkState = state.networkState,
        queue = state.errorQueue,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.padding(top = 12.dp))
            ProfileFieldType.entries.forEach { type ->
                when (type.type) {
                    FieldType.EXPANDED_HEADER -> {
                        val text = when (type) {
                            ProfileFieldType.PERSONAL_DETAILS -> stringResource(Res.string.label_personal_details)
                            ProfileFieldType.MAILING_ADDRESS -> stringResource(Res.string.label_mailing_address)
                            else -> stringResource(Res.string.label_employment_details)
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Text(
                            text = text,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 24.dp, top = 8.dp),
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        )
                    }

                    FieldType.INPUT -> type.textField(state)?.let { field ->
                        Spacer(modifier = Modifier.padding(top = 6.dp))
                        CommonEditTextField(
                            state = field,
                            labelText = stringResource(field.label),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            onChange = { text ->
                                events(
                                    ProfileEvent.UpdateValue(
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

            Spacer(modifier = Modifier.padding(top = 12.dp))

            DefaultButton(
                shape = RoundedCornerShape(8.dp),
                enabled = state.isValid,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.label_update),
                onClick = { events(ProfileEvent.UpdateProfile) },
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
    }

    if (state.isDialogVisible) {
        ProfileUpdateSuccessDialog(onDoneClick = {
            viewModel.dismissPopup()
            onBackClick()
        })
    }
}

@Composable
fun ProfileUpdateSuccessDialog(
    onDoneClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onDoneClick,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .background(color = Color(0xFFFFD231), shape = CircleShape)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.doneimage),
                        contentDescription = null,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Profile update successful",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                DefaultButton(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onDoneClick()
                        /*if (emailErrorMessage.isEmpty() && passwordErrorMessage.isEmpty())
                            events(LoginEvent.Authorize)*/
                    },
                    text = stringResource(Res.string.label_done),
                )
            }
        }
    }
}

