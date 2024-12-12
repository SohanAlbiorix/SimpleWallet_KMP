package org.digital101.simplewallet.presentation.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationGraphViewModel : ViewModel() {
    val isTopBar: MutableStateFlow<Boolean> = MutableStateFlow(false)
}