package org.digital101.simplewallet.domain

import org.digital101.simplewallet.business.util.isValidText
import org.jetbrains.compose.resources.StringResource

data class EditTextState(
    val field: TextFieldType,
    val label: StringResource,
    val value: String = "",
    val validation: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: StringResource? = null,
)

fun EditTextState.updateValue(value: String?): EditTextState {
    return if (value != null) this.copy(value = value) else this
}

val EditTextState.isValid : Boolean
    get() = if (this.validation) (!this.hasError && this.value.isValidText) else true