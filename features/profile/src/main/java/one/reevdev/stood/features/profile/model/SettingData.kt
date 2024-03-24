package one.reevdev.stood.features.profile.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class SettingData(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val iconBackgroundColor: Color? = null,
    val iconColor: Color? = null,
    val endComponent: (@Composable () -> Unit)? = null,
    val action: () -> Unit,
)
