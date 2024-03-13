package one.reevdev.stood.features.auth.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
) {

    Column(
        modifier = modifier
            .background(color = StoodTheme.colors.background)
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        StoodTextField(
            value = username,
            onValueChange = onUsernameChange,
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.label_username),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            imeAction = ImeAction.Next,
        )
        StoodTextField(
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.label_password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
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

        Spacer(modifier = Modifier.height(32.dp))

        StoodButton(
            onClick = {
                onLoginButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(R.string.action_login))
        }

        Spacer(modifier = Modifier.height(16.dp))

        StoodButton(
            onClick = {
                onRegisterButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = stringResource(R.string.action_register))
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
        )
    }
}