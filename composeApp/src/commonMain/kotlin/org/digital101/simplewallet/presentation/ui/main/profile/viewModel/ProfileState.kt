package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.business.network.neo.responses.UserDataDTO
import org.digital101.simplewallet.domain.EditTextState
import org.digital101.simplewallet.domain.isValid
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.ADDRESS_LINE_1
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.ADDRESS_LINE_2
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.ADDRESS_LINE_3
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.ANNUAL_INCOME
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.CITY
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.EMPLOYMENT_TYPE
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.MARITAL_STATUS
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.NAME_OF_EMPLOYEE
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.OCCUPATION
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.POST_CODE
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.RELIGION
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.SECTOR
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.STATE
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType.USER_NAME
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.label_address_line_1
import simplewallet.composeapp.generated.resources.label_address_line_2_optional
import simplewallet.composeapp.generated.resources.label_address_line_3_optional
import simplewallet.composeapp.generated.resources.label_annual_income
import simplewallet.composeapp.generated.resources.label_city
import simplewallet.composeapp.generated.resources.label_employment_industry
import simplewallet.composeapp.generated.resources.label_employment_type
import simplewallet.composeapp.generated.resources.label_marital_status
import simplewallet.composeapp.generated.resources.label_name_of_employer_or_company
import simplewallet.composeapp.generated.resources.label_occupation
import simplewallet.composeapp.generated.resources.label_postcode
import simplewallet.composeapp.generated.resources.label_preferred_name
import simplewallet.composeapp.generated.resources.label_religion
import simplewallet.composeapp.generated.resources.label_state

data class ProfileState(
    val username: EditTextState = EditTextState(
        field = USER_NAME,
        label = Res.string.label_preferred_name,
        validation = true,
    ),
    val religion: EditTextState = EditTextState(
        field = RELIGION,
        label = Res.string.label_religion,
        validation = true,
    ),
    val maritalStatus: EditTextState = EditTextState(
        field = MARITAL_STATUS,
        label = Res.string.label_marital_status,
        validation = true,
    ),
    val addressLine1: EditTextState = EditTextState(
        field = ADDRESS_LINE_1,
        label = Res.string.label_address_line_1,
        validation = true,
    ),
    val addressLine2: EditTextState = EditTextState(
        field = ADDRESS_LINE_2,
        label = Res.string.label_address_line_2_optional,
    ),
    val addressLine3: EditTextState = EditTextState(
        field = ADDRESS_LINE_3,
        label = Res.string.label_address_line_3_optional,
    ),
    val postCode: EditTextState = EditTextState(
        field = POST_CODE,
        label = Res.string.label_postcode,
        validation = true,
    ),
    val city: EditTextState = EditTextState(
        field = CITY,
        label = Res.string.label_city,
        validation = true,
    ),
    val state: EditTextState = EditTextState(
        field = STATE,
        label = Res.string.label_state,
        validation = true,
    ),
    val employmentType: EditTextState = EditTextState(
        field = EMPLOYMENT_TYPE,
        label = Res.string.label_employment_type,
        validation = true,
    ),
    val sector: EditTextState = EditTextState(
        field = SECTOR,
        label = Res.string.label_employment_industry,
        validation = true,
    ),
    val nameOfEmployee: EditTextState = EditTextState(
        field = NAME_OF_EMPLOYEE,
        label = Res.string.label_name_of_employer_or_company,
        validation = true,
    ),
    val occupation: EditTextState = EditTextState(
        field = OCCUPATION,
        label = Res.string.label_occupation,
        validation = true,
    ),
    val annualIncome: EditTextState = EditTextState(
        field = ANNUAL_INCOME,
        label = Res.string.label_annual_income,
    ),

    val data: UserDataDTO? = null,
    val isDialogVisible: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)

val ProfileState.isValid: Boolean
    get() {
        val profileValid = username.isValid && religion.isValid && maritalStatus.isValid
        val addressValid = addressLine1.isValid && addressLine2.isValid && addressLine3.isValid &&
                postCode.isValid && city.isValid && state.isValid
        val employmentValid = employmentType.isValid && sector.isValid && nameOfEmployee.isValid &&
                occupation.isValid && annualIncome.isValid
        return profileValid && addressValid && employmentValid
    }