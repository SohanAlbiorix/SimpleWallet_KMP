package org.digital101.simplewallet.presentation.theme

import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.digital101.simplewallet.common.ChangeStatusBarColors

private val LightColorScheme = lightColorScheme(
    primary = BaseColors.Primary,
    background = BaseColors.Primary,
    secondary = BaseColors.Secondary,
    tertiary = BaseColors.Tertiary,
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

@Composable
fun DefaultCardColorsTheme() = CardColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.onBackground,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.onBackground,
)

@Composable
fun DefaultButtonWithBorderPrimaryTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.background,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = MaterialTheme.colorScheme.background,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)

@Composable
fun DefaultButtonTheme() = buttonColors(
    containerColor = MaterialTheme.colorScheme.tertiary,
    contentColor = MaterialTheme.colorScheme.secondary,
    // disabledBackgroundColor = MaterialTheme.colorScheme.background,
    disabledContentColor = MaterialTheme.colorScheme.primary
)