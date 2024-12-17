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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.digital101.simplewallet.domain.ToolBarButton
import org.digital101.simplewallet.presentation.component.DefaultButton
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileEvent
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.doneimage
import simplewallet.composeapp.generated.resources.label_address_line_1
import simplewallet.composeapp.generated.resources.label_address_line_2_optional
import simplewallet.composeapp.generated.resources.label_annual_income
import simplewallet.composeapp.generated.resources.label_city
import simplewallet.composeapp.generated.resources.label_done
import simplewallet.composeapp.generated.resources.label_employment_details
import simplewallet.composeapp.generated.resources.label_employment_industry
import simplewallet.composeapp.generated.resources.label_employment_type
import simplewallet.composeapp.generated.resources.label_mailing_address
import simplewallet.composeapp.generated.resources.label_marital_status
import simplewallet.composeapp.generated.resources.label_my_profile
import simplewallet.composeapp.generated.resources.label_name_of_employer_or_company
import simplewallet.composeapp.generated.resources.label_occupation
import simplewallet.composeapp.generated.resources.label_personal_details
import simplewallet.composeapp.generated.resources.label_postcode
import simplewallet.composeapp.generated.resources.label_preferred_name
import simplewallet.composeapp.generated.resources.label_religion
import simplewallet.composeapp.generated.resources.label_state
import simplewallet.composeapp.generated.resources.label_update

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinInject(),
    onBackClick: () -> Unit,
) {
    val state = viewModel.state.value

    val events = viewModel::onTriggerEvent

    val preferredErrorMessage = viewModel.preferredNameErrorMessage.collectAsState().value
    val religionErrorMessage = viewModel.religionErrorMessage.collectAsState().value
    val maritalStatusErrorMessage = viewModel.maritalStatusErrorMessage.collectAsState().value
    val addressLine1ErrorMessage = viewModel.addressLine1ErrorMessage.collectAsState().value
    val addressLine2ErrorMessage = viewModel.addressLine2ErrorMessage.collectAsState().value
    val postCodeErrorMessage = viewModel.postCodeErrorMessage.collectAsState().value
    val cityErrorMessage = viewModel.cityErrorMessage.collectAsState().value
    val stateErrorMessage = viewModel.stateErrorMessage.collectAsState().value
    val employeeTypeErrorMessage = viewModel.employeeTypeErrorMessage.collectAsState().value
    val employeeIndustryErrorMessage = viewModel.employeeIndustryErrorMessage.collectAsState().value
    val employeeNameErrorMessage = viewModel.nameOfEmployeeErrorMessage.collectAsState().value
    val employeeOccupationErrorMessage = viewModel.occupationErrorMessage.collectAsState().value
    val employeeAnnualIncomeErrorMessage = viewModel.annualIncomeErrorMessage.collectAsState().value

    // State to toggle dialog visibility
    DefaultScreenUI(
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

            Spacer(modifier = Modifier.padding(top = 24.dp))
            Text(
                text = stringResource(Res.string.label_my_profile),
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Text(
                text = stringResource(Res.string.label_personal_details),
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
            )

            CommonEditTextField(
                text = state.preferredUsername,
                placeHolderText = stringResource(Res.string.label_preferred_name),
                labelText = stringResource(Res.string.label_preferred_name),
                isError = preferredErrorMessage.isNotEmpty(),
                errorMsg = preferredErrorMessage,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdatePreferredName(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))

            CommonEditTextField(
                text = state.religion,
                placeHolderText = stringResource(Res.string.label_religion),
                labelText = stringResource(Res.string.label_religion),
                isError = religionErrorMessage.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorMsg = religionErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateReligion(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.maritalStatus,
                placeHolderText = stringResource(Res.string.label_marital_status),
                labelText = stringResource(Res.string.label_marital_status),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                isError = maritalStatusErrorMessage.isNotEmpty(),
                errorMsg = maritalStatusErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateMaritalStatus(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Text(
                text = stringResource(Res.string.label_mailing_address),
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
            )

            CommonEditTextField(
                text = state.addressLine1,
                placeHolderText = stringResource(Res.string.label_address_line_1),
                labelText = stringResource(Res.string.label_address_line_1),
                isError = addressLine1ErrorMessage.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorMsg = addressLine1ErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateAddressLine1(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.addressLine2,
                placeHolderText = stringResource(Res.string.label_address_line_2_optional),
                labelText = stringResource(Res.string.label_address_line_2_optional),
                isError = addressLine2ErrorMessage.isNotEmpty(),
                errorMsg = addressLine2ErrorMessage,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateAddressLine2(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.postCode,
                placeHolderText = stringResource(Res.string.label_postcode),
                labelText = stringResource(Res.string.label_postcode),
                isError = postCodeErrorMessage.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                errorMsg = postCodeErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdatePostCode(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.city,
                placeHolderText = stringResource(Res.string.label_city),
                labelText = stringResource(Res.string.label_city),
                isError = cityErrorMessage.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorMsg = cityErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateCity(inputText))
                },
            )

            CommonEditTextField(
                text = state.addressState,
                placeHolderText = stringResource(Res.string.label_state),
                labelText = stringResource(Res.string.label_state),
                isError = stateErrorMessage.isNotEmpty(),
                errorMsg = stateErrorMessage,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateState(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Text(
                text = stringResource(Res.string.label_employment_details),
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 24.dp, top = 8.dp)
            )
            CommonEditTextField(
                text = state.employmentTypeState,
                placeHolderText = stringResource(Res.string.label_employment_type),
                labelText = stringResource(Res.string.label_employment_type),
                isError = employeeTypeErrorMessage.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorMsg = employeeTypeErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateEmploymentType(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.employmentIndustry,
                placeHolderText = stringResource(Res.string.label_employment_industry),
                labelText = stringResource(Res.string.label_employment_industry),
                isError = employeeIndustryErrorMessage.isNotEmpty(),
                errorMsg = employeeIndustryErrorMessage,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateEmploymentIndustry(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.nameOfEmployee,
                placeHolderText = stringResource(Res.string.label_name_of_employer_or_company),
                labelText = stringResource(Res.string.label_name_of_employer_or_company),
                isError = employeeNameErrorMessage.isNotEmpty(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                errorMsg = employeeNameErrorMessage,
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateEmployName(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.occupation,
                placeHolderText = stringResource(Res.string.label_occupation),
                labelText = stringResource(Res.string.label_occupation),
                isError = employeeOccupationErrorMessage.isNotEmpty(),
                errorMsg = employeeOccupationErrorMessage,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateEmployOccupation(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            CommonEditTextField(
                text = state.annualIncome,
                placeHolderText = stringResource(Res.string.label_annual_income),
                labelText = stringResource(Res.string.label_annual_income),
                isError = employeeAnnualIncomeErrorMessage.isNotEmpty(),
                errorMsg = employeeAnnualIncomeErrorMessage,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),

                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateEmployAnnualIncome(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 36.dp))


            DefaultButton(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (preferredErrorMessage.isEmpty() && religionErrorMessage.isEmpty() &&
                        maritalStatusErrorMessage.isEmpty() && addressLine1ErrorMessage.isEmpty() &&
                        addressLine2ErrorMessage.isEmpty() && postCodeErrorMessage.isEmpty() &&
                        cityErrorMessage.isEmpty() && stateErrorMessage.isEmpty() &&
                        employeeOccupationErrorMessage.isEmpty() && employeeIndustryErrorMessage.isEmpty() &&
                        employeeNameErrorMessage.isEmpty() && employeeOccupationErrorMessage.isEmpty() &&
                        employeeAnnualIncomeErrorMessage.isEmpty()
                    ) {
                        events(ProfileEvent.UpdateDate)
                    }
                },
                text = stringResource(Res.string.label_update),
            )
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentSize().padding(16.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                .padding(24.dp)
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

