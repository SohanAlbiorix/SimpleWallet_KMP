package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import org.digital101.simplewallet.business.core.UIComponent

sealed class ProfileEvent {
    data object UpdateDate : ProfileEvent()

    data class OnUpdatePreferredName(val value: String) : ProfileEvent()
    data class OnUpdateReligion(val value: String) : ProfileEvent()
    data class OnUpdateMaritalStatus(val value: String) : ProfileEvent()
    data class OnUpdateAddressLine1(val value: String) : ProfileEvent()
    data class OnUpdateAddressLine2(val value: String) : ProfileEvent()
    data class OnUpdatePostCode(val value: String) : ProfileEvent()
    data class OnUpdateCity(val value: String) : ProfileEvent()
    data class OnUpdateState(val value: String) : ProfileEvent()
    data class OnUpdateEmploymentType(val value: String) : ProfileEvent()
    data class OnUpdateEmploymentIndustry(val value: String) : ProfileEvent()
    data class OnUpdateEmployName(val value: String) : ProfileEvent()
    data class OnUpdateEmployOccupation(val value: String) : ProfileEvent()
    data class OnUpdateEmployAnnualIncome(val value: String) : ProfileEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : ProfileEvent()
}