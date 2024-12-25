package org.digital101.simplewallet.presentation.theme

import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.digital101.simplewallet.common.ChangeStatusBarColors

private val LightColorScheme = lightColorScheme(
    primary = BaseColors.Primary,
    background = BaseColors.Primary,
    secondary = BaseColors.Secondary,
    tertiary = BaseColors.Tertiary,
    error = BaseColors.Red,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
//    ChangeStatusBarColors(LightColorScheme.primary)
    ChangeStatusBarColors(Color.Black)

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun DefaultCardColorsTheme() = CardColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.onBackground,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.onBackground,
)

@Composable
fun DefaultButtonTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.tertiary,
    contentColor = MaterialTheme.colorScheme.secondary,
    disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
    disabledContentColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.38f)
)