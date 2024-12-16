package org.digital101.simplewallet.presentation.ui.main.settings.model

import org.jetbrains.compose.resources.DrawableResource

data class ProfileModel(
    val icon: DrawableResource,
    val title: String,
    val onClick: (() -> Unit)? = null,
)
