package org.digital101.simplewallet.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class ToolBarButton(
    val icon: ImageVector,
    val description: String? = null,
    val onClick: () -> Unit
)