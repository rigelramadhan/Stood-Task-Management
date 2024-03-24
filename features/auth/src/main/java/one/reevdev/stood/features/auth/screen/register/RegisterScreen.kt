package one.reevdev.stood.features.auth.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.stood.features.auth.R
import one.reevdev.stood.features.common.R.font
import one.reevdev.stood.features.common.component.buttons.StoodButton
import one.reevdev.stood.features.common.component.buttons.StoodButtonText
import one.reevdev.stood.features.common.component.texts.StoodText
import one.reevdev.stood.features.common.component.texts.StoodTextField
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
    displayName: String,
    password: String,
    confirmPassword: String,
    isPasswordVisible: Boolean,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onDisplayNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityClick: (Boolean) -> Unit,
    onRegisterButtonClick: () -> Unit,
    onLoginButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = StoodTheme.colors.surface)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Switch(checked = true, onCheckedChange = {})
        Spacer(modifier = Modifier.height(32.dp))
        TermsAndCondition(
            onTosClick = {},
            onPrivacyPolicyClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        StoodText(
            text = stringResource(R.string.title_create_an_account),
            style = StoodTheme.types.bodyLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        StoodTextField(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            label = stringResource(id = R.string.label_email),
            imeAction = ImeAction.Next,
        )
        StoodTextField(
            value = username,
            onValueChange = onUsernameChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            label = stringResource(R.string.label_username),
            imeAction = ImeAction.Next,
        )
        StoodTextField(
            value = displayName,
            onValueChange = onDisplayNameChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            label = stringResource(id = R.string.label_display_name),
            imeAction = ImeAction.Next,
        )
        StoodTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            label = stringResource(R.string.label_password),
            trailingIcon = {
                val icon =
                    if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { onPasswordVisibilityClick(!isPasswordVisible) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            onNext = {
                // Move focus to the next field
            },
        )
        StoodTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.label_confirm_password),
            visualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            onDone = {
                onRegisterButtonClick()
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        StoodButton(
            onClick = {
                onRegisterButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(R.string.action_register)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StoodText(text = "Already have an account? ")
            StoodButtonText(text = "Login") {
                onLoginButtonClick()
            }
        }
    }
}

@Composable
fun TermsAndCondition(
    modifier: Modifier = Modifier,
    onTosClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit
) {
    val termsOfService = stringResource(R.string.label_terms_of_service)
    val privacyPolicy = stringResource(R.string.label_privacy_policy)
    val buttonTextColor = StoodTheme.colors.tertiary
    val textColor = StoodTheme.colors.textColor
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = ParagraphStyle(
                lineHeight = 16.sp
            )
        ) {
            withStyle(
                style = SpanStyle(
                    fontFamily = FontFamily(Font(font.josefin_sans_regular)),
                    fontSize = 12.sp,
                    color = textColor
                )
            ) {
                append("By opting to use this app, you're acknowledging your agreement with our ")
                withStyle(style = SpanStyle(color = buttonTextColor)) {
                    pushStringAnnotation(termsOfService, termsOfService)
                    append(termsOfService)
                }
                append(" and ")
                withStyle(style = SpanStyle(color = buttonTextColor)) {
                    pushStringAnnotation(privacyPolicy, privacyPolicy)
                    append(privacyPolicy)
                }
                append(".")
            }
        }
    }
    ClickableText(
        modifier = modifier,
        text = annotatedString,
    ) {
        annotatedString.getStringAnnotations(it, it).firstOrNull()?.let { span ->
            when (span.item) {
                termsOfService -> onTosClick()
                privacyPolicy -> onPrivacyPolicyClick()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    StoodTheme {
        RegisterScreen(
            username = emptyString(),
            email = emptyString(),
            displayName = emptyString(),
            password = emptyString(),
            confirmPassword = emptyString(),
            isPasswordVisible = false,
            onUsernameChange = {},
            onEmailChange = {},
            onDisplayNameChange = {},
            onPasswordChange = {},
            onConfirmPasswordChange = {},
            onPasswordVisibilityClick = {},
            onRegisterButtonClick = {},
            onLoginButtonClick = {}
        )
    }
}