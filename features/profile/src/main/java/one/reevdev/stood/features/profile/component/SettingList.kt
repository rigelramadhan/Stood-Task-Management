package one.reevdev.stood.features.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.common.theme.StoodTheme
import one.reevdev.stood.features.profile.model.SettingData

@Composable
fun SettingList(
    modifier: Modifier = Modifier,
    settings: List<SettingData>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        settings.forEach {
            SettingItem(
                modifier = Modifier,
                icon = it.icon,
                iconBackground = it.iconBackgroundColor ?: StoodTheme.colors.eggShell,
                iconColor = it.iconColor ?: StoodTheme.colors.onEggShell,
                text = it.text,
                endComponent = it.endComponent,
                onClick = it.action
            )
        }
    }
}