package org.digital101.simplewallet.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.digital101.simplewallet.business.core.ProgressBarState
import org.digital101.simplewallet.presentation.theme.DefaultButtonTheme
import org.digital101.simplewallet.presentation.theme.DefaultButtonWithBorderPrimaryTheme

val DEFAULT__BUTTON_SIZE = 50.dp
val DEFAULT__BUTTON_SIZE_EXTRA = 60.dp

@Composable
fun ButtonLoading(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = (enabled || progressBarState != ProgressBarState.Idle),
        modifier = modifier,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            AnimatedVisibility(visible = (progressBarState == ProgressBarState.ButtonLoading || progressBarState == ProgressBarState.FullScreenLoading)) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(25.dp),
                    strokeWidth = 2.dp,
                    color = if (enabled) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
                )
            }

            content()
        }
    }
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    enabled: Boolean = true,
    enableElevation: Boolean = false,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    text: String,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onClick: () -> Unit,
) {
    ButtonLoading(
        enabled = enabled,
        modifier = modifier,
        elevation = if (enableElevation) ButtonDefaults.buttonElevation() else ButtonDefaults.buttonElevation(
            0.dp
        ),
        colors = if (enabled) DefaultButtonTheme() else DefaultButtonWithBorderPrimaryTheme(),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        shape = shape,
        onClick = onClick,
        progressBarState = progressBarState,
        contentPadding = contentPadding,
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}