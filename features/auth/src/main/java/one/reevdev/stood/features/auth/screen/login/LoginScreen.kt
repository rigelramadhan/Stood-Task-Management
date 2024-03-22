package one.reevdev.stood.features.auth.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.cosmoe.utils.emptyString
import one.reevdev.stood.features.auth.R
import one.reevdev.stood.features.common.component.StoodButton
import one.reevdev.stood.features.common.component.StoodButtonText
import one.reevdev.stood.features.common.component.StoodText
import one.reevdev.stood.features.common.component.StoodTextField
import one.reevdev.stood.features.common.theme.StoodTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    isPasswordVisible: Boolean,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityClick: (Boolean) -> Unit,
    onLoginButtonClick: () -> Unit,
    onRegisterButtonClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(color = StoodTheme.colors.surface)
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Switch(checked = true, onCheckedChange = {})
        Spacer(modifier = Modifier.height(32.dp))
        StoodText(
            text = stringResource(R.string.title_login_screen),
            style = StoodTheme.types.bodyLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        StoodTextField(
            value = username,
            onValueChange = onUsernameChange,
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.label_username),
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null
//                )
//            },
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(20.dp))
        StoodTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.label_password),
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Lock,
//                    contentDescription = null
//                )
//            },
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
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            onDone = {
                onLoginButtonClick()
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        StoodButtonText(text = stringResource(R.string.label_forgot_password)) {
            onForgotPasswordClick()
        }
        Spacer(modifier = Modifier.height(24.dp))
        StoodButton(
            onClick = {
                onLoginButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = stringResource(R.string.action_login)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StoodText(text = stringResource(R.string.label_dont_have_any_account))
            StoodButtonText(text = stringResource(R.string.label_register_here)) {
                onRegisterButtonClick()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StoodTheme {
        LoginScreen(
            username = emptyString(),
            password = emptyString(),
            isPasswordVisible = false,
            onUsernameChange = {},
            onPasswordChange = {},
            onPasswordVisibilityClick = {},
            onLoginButtonClick = {},
            onRegisterButtonClick = {},
            onForgotPasswordClick = {},
        )
    }
}