package one.reevdev.stood.features.profile.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.common.component.StoodText
import one.reevdev.stood.features.common.theme.StoodTheme
import one.reevdev.stood.features.profile.R

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    iconBackground: Color = StoodTheme.colors.eggShell,
    iconColor: Color = StoodTheme.colors.onEggShell,
    @StringRes text: Int,
    endComponent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .background(iconBackground),
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.description_setting_item_icon),
            tint = iconColor,
        )
        StoodText(
            modifier = Modifier
                .weight(1f),
            text = stringResource(text),
            style = StoodTheme.types.bodyMedium
        )
        endComponent?.invoke() ?: EnterIconButton(modifier = Modifier) {
            onClick()
        }
    }
}