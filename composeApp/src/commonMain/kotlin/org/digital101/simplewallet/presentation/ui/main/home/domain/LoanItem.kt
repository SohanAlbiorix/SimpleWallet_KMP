package org.digital101.simplewallet.presentation.ui.main.home.domain

import org.jetbrains.compose.resources.DrawableResource

data class LoanItem(
    val category: String,
    val title: String,
    val description: String,
    val imageRes: DrawableResource
)