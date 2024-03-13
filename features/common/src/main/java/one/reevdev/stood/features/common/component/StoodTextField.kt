package one.reevdev.stood.features.common.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun StoodTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    placeholder: String? = null,
    value: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onValueChange: (String) -> Unit,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
    onGo: (KeyboardActionScope.() -> Unit)? = null,
    onNext: (KeyboardActionScope.() -> Unit)? = null,
    onPrevious: (KeyboardActionScope.() -> Unit)? = null,
    onSearch: (KeyboardActionScope.() -> Unit)? = null,
    onSend: (KeyboardActionScope.() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label = { Text(text = label) },
        placeholder = placeholder?.let { { Text(text = it) } },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = StoodTheme.colors.background,
            focusedContainerColor = StoodTheme.colors.background,
            unfocusedIndicatorColor = StoodTheme.colors.primaryUnfocused,
            unfocusedLabelColor = StoodTheme.colors.primaryUnfocused,
            unfocusedTextColor = StoodTheme.colors.textDisabled,
            focusedIndicatorColor = StoodTheme.colors.primary,
            focusedLabelColor = StoodTheme.colors.primary,
            focusedTextColor = StoodTheme.colors.textColor,
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        shape = CircleShape,
        maxLines = maxLines,
        minLines = minLines,
        keyboardActions = KeyboardActions(
            onDone = onDone,
            onGo = onGo,
            onNext = onNext,
            onPrevious = onPrevious,
            onSearch = onSearch,
            onSend = onSend
        ),
        visualTransformation = visualTransformation
    )
}