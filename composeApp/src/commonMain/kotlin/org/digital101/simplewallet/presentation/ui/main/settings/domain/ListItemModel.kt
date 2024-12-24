package org.digital101.simplewallet.presentation.ui.main.settings.domain

import org.jetbrains.compose.resources.DrawableResource

data class ListItemModel(
    val icon: DrawableResource,
    val title: String,
    val onClick: (() -> Unit)? = null,
)
