import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.digital101.simplewallet.domain.EditTextState
import org.digital101.simplewallet.presentation.theme.BaseColors
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommonEditTextField(
    modifier: Modifier = Modifier,
    state: EditTextState,
    placeHolderText: String? = null,
    onChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    labelText: String,
    keyboardActions: KeyboardActions? = null,
    singleLine: Boolean = true,
    isPassword: Boolean = false, // Add flag to handle password field
) {
    val focusManager = LocalFocusManager.current

    var isPasswordVisible by remember { mutableStateOf(!isPassword) }

    // If password field, show an icon to toggle visibility
    val visualTransformation = if (isPassword && !isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        value = state.value,
        singleLine = singleLine,
        onValueChange = onChange,
        isError = state.hasError,
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = keyboardOptions,
        modifier = modifier.fillMaxWidth(),
        placeholder = { placeHolderText?.let { Text(text = it) } },
        label = { Text(text = labelText, color = BaseColors.Gray) },
        keyboardActions = keyboardActions ?: when (keyboardOptions.imeAction) {
            ImeAction.Done, ImeAction.Go, ImeAction.Search, ImeAction.Send -> KeyboardActions(onDone = {
                focusManager.clearFocus(true)
            })

            ImeAction.Next -> KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) })
            ImeAction.Previous -> KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Previous) })
            ImeAction.Previous -> KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Previous) })
            else -> KeyboardActions.Default
        },
        visualTransformation = visualTransformation, // Apply visual transformation for password
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            unfocusedBorderColor = BaseColors.Gray,
        ),
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            }
        }
    )
    if (state.errorMessage != null) {
        Text(
            fontSize = 12.sp,
            color = Color.Red,
            text = stringResource(state.errorMessage),
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
        )
    }
}
