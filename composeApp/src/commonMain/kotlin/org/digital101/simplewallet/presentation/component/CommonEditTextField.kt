import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonEditTextField(
    text: MutableState<TextFieldValue>,
    placeHolderText: String,
    onchange: (String) -> Unit,
    labelText: String,
    isError: Boolean = false,
    errorMsg: String = "",
    isPassword: Boolean = false, // Add flag to handle password field
    onPasswordVisibilityToggle: (() -> Unit)? = null // Function to toggle password visibility
) {
    var isPasswordVisible by remember { mutableStateOf(!isPassword) }

    // If password field, show an icon to toggle visibility
    val visualTransformation = if (isPassword && !isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text.value,
        onValueChange = { newValue ->
            text.value = newValue
            onchange(newValue.text)
        },
        placeholder = { Text(text = placeHolderText) },
        label = { Text(text = labelText, color = Color.Black) },
        shape = RoundedCornerShape(12.dp),
        isError = isError,
        visualTransformation = visualTransformation, // Apply visual transformation for password
        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Black),
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                    onPasswordVisibilityToggle?.invoke()
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            }
        }
    )
    if (isError) {
        Text(
            text = errorMsg,
            color = Color.Red,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
        )
    }
}
