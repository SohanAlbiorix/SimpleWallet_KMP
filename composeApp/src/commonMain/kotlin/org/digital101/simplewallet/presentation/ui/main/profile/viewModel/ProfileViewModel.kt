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
import org.digital101.simplewallet.business.interactors.neobank.UpdateProfileInteract
import org.digital101.simplewallet.business.interactors.neobank.UserInteract
import org.digital101.simplewallet.business.interactors.neobank.WalletInteract
import org.digital101.simplewallet.business.network.neo.responses.Address
import org.digital101.simplewallet.business.network.neo.responses.EmploymentDetail
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
    private val userInteract: UserInteract,
    private val updateProfileInteract: UpdateProfileInteract,
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
                updateProfile()
            }
        }
    }

    init {
        loadProfile()
    }

    private fun updateProfile() {
        val data = state.value.data
        if (data != null) {
            val updatedAddress = mutableListOf<Address>()
            val address = data.addresses
            if (!address.isNullOrEmpty()) {
                address.forEach {
                    if (it.addressType == "Mailing Address") {
                        updatedAddress.add(
                            it.copy(
                                line1 = state.value.addressLine1,
                                line2 = state.value.addressLine2,
                                postcode = state.value.postCode,
                                city = state.value.city,
                                state = state.value.addressState,
                            )
                        )
                    } else {
                        updatedAddress.add(it)
                    }
                }
            }

            val updatedEmploymentDetail = mutableListOf<EmploymentDetail>()
            val employmentDetail = data.employmentDetails
            if (!employmentDetail.isNullOrEmpty()) {
                updatedEmploymentDetail.add(
                    employmentDetail.first().copy(
                        employmentType = state.value.employmentTypeState,
                        sector = state.value.employmentIndustry,
                        occupation = state.value.occupation,
                        companyName = state.value.nameOfEmployee,
                    )
                )
            }

            updateProfileInteract.execute(
                data = data.copy(
                    userName = state.value.preferredUsername,
                    religion = state.value.religion,
                    maritalStatus = state.value.maritalStatus,
                    addresses = updatedAddress,
                    employmentDetails = updatedEmploymentDetail,
                )
            ).onEach { dataState ->
                when (dataState) {
                    is DataState.NetworkStatus -> {}
                    is DataState.Response -> {
                        onTriggerEvent(ProfileEvent.Error(dataState.uiComponent))
                    }

                    is DataState.Data -> {
                        state.value = state.value.copy(isDialogVisible = true)
                    }

                    is DataState.Loading -> {
                        state.value = state.value.copy(
                            progressBarState = dataState.progressBarState,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun dismissPopup() {
        viewModelScope.launch {
            state.value = state.value.copy(
                isDialogVisible = false
            )
        }
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
                        /// personal details
                        preferredUsername = dataState.data?.userName ?: "",
                        religion = dataState.data?.religion ?: "",
                        maritalStatus = dataState.data?.maritalStatus ?: "",

                        /// mailing address
                        addressLine1 = dataState.data?.addresses?.first {
                            it.addressType == "Mailing Address"
                        }?.line1 ?: "",
                        addressLine2 = dataState.data?.addresses?.first {
                            it.addressType == "Mailing Address"
                        }?.line2 ?: "",
                        postCode = dataState.data?.addresses?.first {
                            it.addressType == "Mailing Address"
                        }?.postcode ?: "",
                        city = dataState.data?.addresses?.first {
                            it.addressType == "Mailing Address"
                        }?.city ?: "",
                        addressState = dataState.data?.addresses?.first {
                            it.addressType == "Mailing Address"
                        }?.state ?: "",

                        /// employment details
                        employmentTypeState = dataState.data?.employmentDetails?.first()?.employmentType
                            ?: "",
                        employmentIndustry = dataState.data?.employmentDetails?.first()?.sector
                            ?: "",
                        occupation = dataState.data?.employmentDetails?.first()?.occupation ?: "",
                        nameOfEmployee = dataState.data?.employmentDetails?.first()?.companyName
                            ?: "",

                        data = dataState.data,
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