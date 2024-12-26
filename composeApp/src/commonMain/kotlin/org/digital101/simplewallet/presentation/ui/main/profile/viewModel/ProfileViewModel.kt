package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.digital101.simplewallet.business.core.DataState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent
import org.digital101.simplewallet.business.interactors.neobank.UpdateProfileInteract
import org.digital101.simplewallet.business.interactors.neobank.UserInteract
import org.digital101.simplewallet.business.util.isValidText
import org.digital101.simplewallet.domain.errorMessage
import org.digital101.simplewallet.domain.updateValue


class ProfileViewModel(
    private val userInteract: UserInteract,
    private val updateProfileInteract: UpdateProfileInteract,
) : ViewModel() {
    val state: MutableState<ProfileState> = mutableStateOf(ProfileState())

    fun onTriggerEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateValue -> validateOrUpdate(event)

            is ProfileEvent.Error -> {
                val queue: Queue<UIComponent> = state.value.errorQueue
                queue.add(event.uiComponent)
                state.value = state.value.copy(errorQueue = queue)
            }

            ProfileEvent.UpdateProfile -> updateProfile()
        }
    }

    init {
        loadProfile()
    }

    private fun updateProfile() {
        state.value.data?.let { data ->
            val updatedAddresses = data.addresses?.map { address ->
                if (address.addressType?.contains("Mailing Address", ignoreCase = true) == true) {
                    address.copy(
                        line1 = state.value.addressLine1.value,
                        line2 = state.value.addressLine2.value,
                        line3 = state.value.addressLine3.value,
                        postcode = state.value.postCode.value,
                        city = state.value.city.value,
                        state = state.value.state.value
                    )
                } else address
            }

            val updatedEmploymentDetails = data.employmentDetails?.map { employmentDetail ->
                employmentDetail.copy(
                    companyName = state.value.nameOfEmployee.value,
                    employmentType = state.value.employmentType.value,
                    occupation = state.value.occupation.value,
                    sector = state.value.sector.value
                )
            }

            val updatedData = data.copy(
                userName = state.value.username.value,
                religion = state.value.religion.value,
                maritalStatus = state.value.maritalStatus.value,
                addresses = updatedAddresses,
                employmentDetails = updatedEmploymentDetails
            )

            // Print the updated data for debugging purposes
            println("Updated Data Body: $updatedData")

            updateProfileInteract.execute(data = updatedData)
                .onEach { dataState ->
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
                                progressBarState = dataState.progressBarState
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
                    val address =
                        if (!dataState.data?.addresses.isNullOrEmpty()) dataState.data?.addresses?.firstOrNull {
                            it.addressType?.contains("Mailing") == true
                        } else null
                    val employmentDetail =
                        if (!dataState.data?.employmentDetails.isNullOrEmpty()) dataState.data?.employmentDetails?.firstOrNull() else null
                    state.value = state.value.copy(
                        /// personal details
                        username = state.value.username.copy(
                            value = dataState.data?.userName.orEmpty()
                        ),
                        religion = state.value.religion.copy(
                            value = dataState.data?.religion ?: ""
                        ),
                        maritalStatus = state.value.maritalStatus.copy(
                            value = dataState.data?.maritalStatus ?: ""
                        ),

                        /// mailing address
                        addressLine1 = state.value.addressLine1.copy(value = address?.line1 ?: ""),
                        addressLine2 = state.value.addressLine2.copy(value = address?.line2 ?: ""),
                        addressLine3 = state.value.addressLine3.copy(value = address?.line3 ?: ""),
                        postCode = state.value.postCode.copy(value = address?.postcode ?: ""),
                        city = state.value.city.copy(value = address?.city ?: ""),
                        state = state.value.state.copy(value = address?.state ?: ""),

                        /// employment details
                        employmentType = state.value.employmentType.copy(
                            value = employmentDetail?.employmentType ?: ""
                        ),
                        sector = state.value.sector.copy(value = employmentDetail?.sector ?: ""),
                        nameOfEmployee = state.value.nameOfEmployee.copy(
                            value = employmentDetail?.companyName ?: ""
                        ),
                        occupation = state.value.occupation.copy(
                            value = employmentDetail?.occupation ?: ""
                        ),

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

    private fun validateOrUpdate(event: ProfileEvent.UpdateValue) {
        if (event.validation) {
            val isValid = event.value.isValidText
            event.field.textField(state.value)
                ?.updateValue(event.value)
                ?.copy(
                    hasError = !isValid,
                    errorMessage = when {
                        !isValid -> event.field.errorMessage
                        else -> null
                    }
                )
                ?.updateState(state.value)
                ?.let { state.value = it }
        } else {
            event.field.textField(state.value)
                ?.updateValue(event.value)
                ?.updateState(state.value)
                ?.let { state.value = it }
        }
    }
}