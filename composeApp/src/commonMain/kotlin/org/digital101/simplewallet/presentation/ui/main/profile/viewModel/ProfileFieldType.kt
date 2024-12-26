package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import org.digital101.simplewallet.domain.EditTextState
import org.digital101.simplewallet.domain.FieldType
import org.digital101.simplewallet.domain.TextFieldType

enum class ProfileFieldType(val type: FieldType = FieldType.INPUT) : TextFieldType {
    PERSONAL_DETAILS(FieldType.EXPANDED_HEADER),
    USER_NAME,
    RELIGION,
    MARITAL_STATUS,
    MAILING_ADDRESS(FieldType.EXPANDED_HEADER),
    ADDRESS_LINE_1,
    ADDRESS_LINE_2,
    ADDRESS_LINE_3,
    POST_CODE,
    CITY,
    STATE,
    EMPLOYMENT_DETAILS(FieldType.EXPANDED_HEADER),
    EMPLOYMENT_TYPE,
    SECTOR,
    NAME_OF_EMPLOYEE,
    OCCUPATION,
    ANNUAL_INCOME
}

fun TextFieldType.textField(state: ProfileState): EditTextState? {
    return when (this) {
        ProfileFieldType.USER_NAME -> state.username
        ProfileFieldType.RELIGION -> state.religion
        ProfileFieldType.MARITAL_STATUS -> state.maritalStatus
        ProfileFieldType.ADDRESS_LINE_1 -> state.addressLine1
        ProfileFieldType.ADDRESS_LINE_2 -> state.addressLine2
        ProfileFieldType.ADDRESS_LINE_3 -> state.addressLine3
        ProfileFieldType.POST_CODE -> state.postCode
        ProfileFieldType.CITY -> state.city
        ProfileFieldType.STATE -> state.state
        ProfileFieldType.EMPLOYMENT_TYPE -> state.employmentType
        ProfileFieldType.SECTOR -> state.sector
        ProfileFieldType.NAME_OF_EMPLOYEE -> state.nameOfEmployee
        ProfileFieldType.OCCUPATION -> state.occupation
        ProfileFieldType.ANNUAL_INCOME -> state.annualIncome
        else -> null
    }
}

fun EditTextState.updateState(state: ProfileState): ProfileState {
    return when (this.field) {
        ProfileFieldType.USER_NAME -> state.copy(username = this)
        ProfileFieldType.RELIGION -> state.copy(religion = this)
        ProfileFieldType.MARITAL_STATUS -> state.copy(maritalStatus = this)
        ProfileFieldType.ADDRESS_LINE_1 -> state.copy(addressLine1 = this)
        ProfileFieldType.ADDRESS_LINE_2 -> state.copy(addressLine2 = this)
        ProfileFieldType.ADDRESS_LINE_3 -> state.copy(addressLine3 = this)
        ProfileFieldType.POST_CODE -> state.copy(postCode = this)
        ProfileFieldType.CITY -> state.copy(city = this)
        ProfileFieldType.STATE -> state.copy(state = this)
        ProfileFieldType.EMPLOYMENT_TYPE -> state.copy(employmentType = this)
        ProfileFieldType.SECTOR -> state.copy(sector = this)
        ProfileFieldType.NAME_OF_EMPLOYEE -> state.copy(nameOfEmployee = this)
        ProfileFieldType.OCCUPATION -> state.copy(occupation = this)
        ProfileFieldType.ANNUAL_INCOME -> state.copy(annualIncome = this)
        else -> state
    }
}