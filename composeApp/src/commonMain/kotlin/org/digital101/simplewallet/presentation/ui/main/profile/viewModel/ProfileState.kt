package org.digital101.simplewallet.presentation.ui.main.profile.viewModel

import org.digital101.simplewallet.business.core.NetworkState
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.business.core.Queue
import org.digital101.simplewallet.business.core.UIComponent

data class ProfileState(
    val preferredUsername: String = "",
    val religion: String = "",
    val maritalStatus: String = "",
    val addressLine1: String = "",
    val addressLine2: String = "",
    val postCode: String = "",
    val city: String = "",
    val addressState: String = "",
    val employmentTypeState: String = "",
    val employmentIndustry: String = "",
    val nameOfEmployee: String = "",
    val occupation: String = "",
    val annualIncome: String = "",

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)