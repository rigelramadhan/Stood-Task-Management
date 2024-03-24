package one.reevdev.stood.features.profile.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import one.reevdev.stood.features.profile.R

@Composable
fun EnterIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = stringResource(R.string.content_description_enter_button_icon)
        )
    }
}