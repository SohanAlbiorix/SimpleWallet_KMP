package org.digital101.simplewallet.domain

import org.digital101.simplewallet.presentation.ui.auth.login.viewModel.LoginFieldType
import org.digital101.simplewallet.presentation.ui.main.profile.viewModel.ProfileFieldType
import org.jetbrains.compose.resources.StringResource
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.validator_address
import simplewallet.composeapp.generated.resources.validator_city
import simplewallet.composeapp.generated.resources.validator_email
import simplewallet.composeapp.generated.resources.validator_employ_industry
import simplewallet.composeapp.generated.resources.validator_employ_name
import simplewallet.composeapp.generated.resources.validator_employ_occupation
import simplewallet.composeapp.generated.resources.validator_employ_type
import simplewallet.composeapp.generated.resources.validator_marital_status
import simplewallet.composeapp.generated.resources.validator_password
import simplewallet.composeapp.generated.resources.validator_postcode
import simplewallet.composeapp.generated.resources.validator_religion
import simplewallet.composeapp.generated.resources.validator_state
import simplewallet.composeapp.generated.resources.validator_username

interface TextFieldType

val TextFieldType.errorMessage: StringResource?
    get() = when (this) {
        ProfileFieldType.USER_NAME -> Res.string.validator_username
        ProfileFieldType.RELIGION -> Res.string.validator_religion
        ProfileFieldType.MARITAL_STATUS -> Res.string.validator_marital_status
        ProfileFieldType.ADDRESS_LINE_1 -> Res.string.validator_address
        ProfileFieldType.POST_CODE -> Res.string.validator_postcode
        ProfileFieldType.CITY -> Res.string.validator_city
        ProfileFieldType.STATE -> Res.string.validator_state
        ProfileFieldType.EMPLOYMENT_TYPE -> Res.string.validator_employ_type
        ProfileFieldType.SECTOR -> Res.string.validator_employ_industry
        ProfileFieldType.NAME_OF_EMPLOYEE -> Res.string.validator_employ_name
        ProfileFieldType.OCCUPATION -> Res.string.validator_employ_occupation

        LoginFieldType.EMAIL -> Res.string.validator_email
        LoginFieldType.PASSWORD -> Res.string.validator_password

        else -> null
    }