package one.reevdev.stood.features.common.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun StoodTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    placeholder: String? = null,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label = { Text(text = label) },
        placeholder = placeholder?.let { { Text(text = it)} },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = StoodTheme.colors.primaryUnfocused,
            unfocusedLabelColor = StoodTheme.colors.primaryUnfocused,
            unfocusedTextColor = StoodTheme.colors.textDisabled,
            focusedContainerColor = StoodTheme.colors.primaryFocused,
            focusedLabelColor = StoodTheme.colors.primaryFocused,
            focusedTextColor = StoodTheme.colors.textColor,
        )
    )
}