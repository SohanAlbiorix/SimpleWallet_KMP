package org.digital101.simplewallet.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.digital101.simplewallet.common.ChangeStatusBarColors

private val LightColorScheme = lightColorScheme(
    primary = BaseColors.Primary,
    inversePrimary = BaseColors.Secondary,
    inverseSurface = BaseColors.Secondary,
    inverseOnSurface = BaseColors.Secondary,
    background = BaseColors.Primary,
    surface = BaseColors.Primary,
    onPrimary = BaseColors.Primary,
    onSecondary = BaseColors.Primary,
    onTertiary = BaseColors.Primary,
    error = BaseColors.Red,
)

@Composable
fun JetpackComposeDemoTheme(
    content: @Composable () -> Unit
) {
    ChangeStatusBarColors(LightColorScheme.primary)

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}