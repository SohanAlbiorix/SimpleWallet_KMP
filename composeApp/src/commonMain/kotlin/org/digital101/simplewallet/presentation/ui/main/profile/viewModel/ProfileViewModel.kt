package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.interactors.neobank.UserInteract
import org.digital101.simplewallet.business.util.isAddress1
import org.digital101.simplewallet.business.util.isAddress2
import org.digital101.simplewallet.business.util.isAnnualIncome
import org.digital101.simplewallet.business.util.isCity
import org.digital101.simplewallet.business.util.isEmployeeIndustry
import org.digital101.simplewallet.business.util.isEmployeeType
import org.digital101.simplewallet.business.util.isMaritalStatus
import org.digital101.simplewallet.business.util.isNameOccupation
import org.digital101.simplewallet.business.util.isNameOfEmployee
import org.digital101.simplewallet.business.util.isPostCode
import org.digital101.simplewallet.business.util.isPreferredName
import org.digital101.simplewallet.business.util.isReligion
import org.digital101.simplewallet.business.util.isState
import org.jetbrains.compose.resources.getString
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.validation_please_enter_Preferred_name
import simplewallet.composeapp.generated.resources.validation_please_enter_Religion
import simplewallet.composeapp.generated.resources.validation_please_enter_address1
import simplewallet.composeapp.generated.resources.validation_please_enter_address2
import simplewallet.composeapp.generated.resources.validation_please_enter_city
import simplewallet.composeapp.generated.resources.validation_please_enter_employ_annual_income
import simplewallet.composeapp.generated.resources.validation_please_enter_employ_industry
import simplewallet.composeapp.generated.resources.validation_please_enter_employ_name
import simplewallet.composeapp.generated.resources.validation_please_enter_employ_occupation
import simplewallet.composeapp.generated.resources.validation_please_enter_employ_type
import simplewallet.composeapp.generated.resources.validation_please_enter_marital_status
import simplewallet.composeapp.generated.resources.validation_please_enter_postcode
import simplewallet.composeapp.generated.resources.validation_please_enter_state


class ProfileViewModel(
    val userInteract: UserInteract,
) : ViewModel() {
    val state: MutableState<ProfileState> = mutableStateOf(ProfileState())

    var preferredNameErrorMessage = MutableStateFlow("")
    var religionErrorMessage = MutableStateFlow("")
    var maritalStatusErrorMessage = MutableStateFlow("")
    var addressLine1ErrorMessage = MutableStateFlow("")
    var addressLine2ErrorMessage = MutableStateFlow("")
    var postCodeErrorMessage = MutableStateFlow("")
    var cityErrorMessage = MutableStateFlow("")
    var stateErrorMessage = MutableStateFlow("")
    var employeeTypeErrorMessage = MutableStateFlow("")
    var employeeIndustryErrorMessage = MutableStateFlow("")
    var nameOfEmployeeErrorMessage = MutableStateFlow("")
    var occupationErrorMessage = MutableStateFlow("")
    var annualIncomeErrorMessage = MutableStateFlow("")

    fun onTriggerEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.OnUpdatePreferredName -> {
                OnUpdatePreferredName(event.value)
            }

            is ProfileEvent.OnUpdateReligion -> {
                onUpdateReligion(event.value)
            }

            is ProfileEvent.OnUpdateMaritalStatus -> {
                onUpdateMaritalStatus(event.value)
            }

            is ProfileEvent.OnUpdateAddressLine1 -> {
                onUpdateAddressLine1(event.value)
            }

            is ProfileEvent.OnUpdateAddressLine2 -> {
                onUpdateAddressLine2(event.value)
            }

            is ProfileEvent.OnUpdatePostCode -> {
                onUpdatePostCode(event.value)
            }

            is ProfileEvent.OnUpdateCity -> {
                onUpdateCity(event.value)
            }

            is ProfileEvent.OnUpdateState -> {
                onUpdateState(event.value)
            }

            is ProfileEvent.OnUpdateEmploymentType -> {
                onUpdateType(event.value)
            }

            is ProfileEvent.OnUpdateEmploymentIndustry -> {
                onUpdateIndustry(event.value)
            }

            is ProfileEvent.OnUpdateEmployName -> {
                onUpdateName(event.value)
            }

            is ProfileEvent.OnUpdateEmployOccupation -> {
                onUpdateOccupation(event.value)
            }

            is ProfileEvent.OnUpdateEmployAnnualIncome -> {
                onUpdateIncome(event.value)
            }

            is ProfileEvent.Error -> {

            }

            ProfileEvent.UpdateDate -> {

            }
        }
    }

    init {
        loadProfile()
    }

    private fun loadProfile() {
        userInteract.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(ProfileEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(
                        // TODO :: Bind your data here

                    )
                }

                is DataState.Loading -> {
                    state.value = state.value.copy(
                        progressBarState = dataState.progressBarState,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun OnUpdatePreferredName(value: String) {
        state.value = state.value.copy(preferredUsername = value)
        viewModelScope.launch {
            preferredNameErrorMessage.value = if (isPreferredName(value)) {
                getString(Res.string.validation_please_enter_Preferred_name)
            } else {
                ""
            }
        }
    }

    fun onUpdateReligion(value: String) {
        state.value = state.value.copy(religion = value)
        viewModelScope.launch {
            religionErrorMessage.value = if (isReligion(value)) {
                getString(Res.string.validation_please_enter_Religion)
            } else {
                ""
            }
        }
    }

    fun onUpdateMaritalStatus(value: String) {
        state.value = state.value.copy(maritalStatus = value)
        viewModelScope.launch {
            religionErrorMessage.value = if (isMaritalStatus(value)) {
                getString(Res.string.validation_please_enter_marital_status)
            } else {
                ""
            }
        }
    }

    fun onUpdateAddressLine1(value: String) {
        state.value = state.value.copy(addressLine1 = value)
        viewModelScope.launch {
            addressLine1ErrorMessage.value = if (isAddress1(value)) {
                getString(Res.string.validation_please_enter_address1)
            } else {
                ""
            }
        }
    }

    fun onUpdateAddressLine2(value: String) {
        state.value = state.value.copy(addressLine2 = value)
        viewModelScope.launch {
            addressLine2ErrorMessage.value = if (isAddress2(value)) {
                getString(Res.string.validation_please_enter_address2)
            } else {
                ""
            }
        }
    }

    fun onUpdatePostCode(value: String) {
        state.value = state.value.copy(postCode = value)
        viewModelScope.launch {
            postCodeErrorMessage.value = if (isPostCode(value)) {
                getString(Res.string.validation_please_enter_postcode)
            } else {
                ""
            }
        }
    }

    fun onUpdateCity(value: String) {
        state.value = state.value.copy(city = value)
        viewModelScope.launch {
            cityErrorMessage.value = if (isCity(value)) {
                getString(Res.string.validation_please_enter_city)
            } else {
                ""
            }
        }
    }

    fun onUpdateState(value: String) {
        state.value = state.value.copy(addressState = value)
        viewModelScope.launch {
            stateErrorMessage.value = if (isState(value)) {
                getString(Res.string.validation_please_enter_state)
            } else {
                ""
            }
        }
    }

    fun onUpdateType(value: String) {
        state.value = state.value.copy(employmentTypeState = value)
        viewModelScope.launch {
            employeeTypeErrorMessage.value = if (isEmployeeType(value)) {
                getString(Res.string.validation_please_enter_employ_type)
            } else {
                ""
            }
        }
    }

    fun onUpdateIndustry(value: String) {
        state.value = state.value.copy(employmentIndustry = value)
        viewModelScope.launch {
            employeeIndustryErrorMessage.value = if (isEmployeeIndustry(value)) {
                getString(Res.string.validation_please_enter_employ_industry)
            } else {
                ""
            }
        }
    }

    fun onUpdateName(value: String) {
        state.value = state.value.copy(nameOfEmployee = value)
        viewModelScope.launch {
            nameOfEmployeeErrorMessage.value = if (isNameOfEmployee(value)) {
                getString(Res.string.validation_please_enter_employ_name)
            } else {
                ""
            }
        }
    }

    fun onUpdateOccupation(value: String) {
        state.value = state.value.copy(occupation = value)
        viewModelScope.launch {
            occupationErrorMessage.value = if (isNameOccupation(value)) {
                getString(Res.string.validation_please_enter_employ_occupation)
            } else {
                ""
            }
        }
    }

    fun onUpdateIncome(value: String) {
        state.value = state.value.copy(annualIncome = value)
        viewModelScope.launch {
            annualIncomeErrorMessage.value = if (isAnnualIncome(value)) {
                getString(Res.string.validation_please_enter_employ_annual_income)
            } else {
                ""
            }
        }
    }
}