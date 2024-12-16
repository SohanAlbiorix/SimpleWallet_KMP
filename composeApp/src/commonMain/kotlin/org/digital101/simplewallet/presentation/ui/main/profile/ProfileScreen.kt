package org.digital101.simplewallet.presentation.ui.main.profile

import CommonEditTextField
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.digital101.simplewallet.presentation.component.DefaultButton
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileEvent
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileViewModel
import org.jetbrains.compose.resources.stringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.label_address_line_1
import simplewallet.composeapp.generated.resources.label_address_line_2_optional
import simplewallet.composeapp.generated.resources.label_annual_income
import simplewallet.composeapp.generated.resources.label_city
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
    viewModel: ProfileViewModel,
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
    DefaultScreenUI(
        onClickStartIconToolbar = onBackClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp).verticalScroll(rememberScrollState())
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
                onchange = { inputText ->
                    events(ProfileEvent.OnUpdateEmployAnnualIncome(inputText))
                },
            )
            Spacer(modifier = Modifier.padding(top = 36.dp))
            DefaultButton(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (!(preferredErrorMessage.isEmpty() && religionErrorMessage.isEmpty() &&
                                maritalStatusErrorMessage.isEmpty() && addressLine1ErrorMessage.isEmpty() && addressLine2ErrorMessage.isEmpty()
                                && postCodeErrorMessage.isEmpty() && cityErrorMessage.isEmpty() && stateErrorMessage.isEmpty() && employeeOccupationErrorMessage.isEmpty()
                                && employeeIndustryErrorMessage.isEmpty() && employeeNameErrorMessage.isEmpty() && employeeOccupationErrorMessage.isEmpty() && employeeAnnualIncomeErrorMessage.isEmpty()
                                )
                    )
                        events(ProfileEvent.UpdateDate)
                },
                text = stringResource(Res.string.label_update),
            )
        }
    }
}
