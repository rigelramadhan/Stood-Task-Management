package one.reevdev.stood.features.common.component.topappbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import one.reevdev.stood.features.common.R
import one.reevdev.stood.features.common.component.texts.StoodText
import one.reevdev.stood.features.common.theme.StoodTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoodTopAppBar(
    modifier: Modifier = Modifier,
    backButtonEnabled: Boolean = true,
    centerTitle: Boolean,
    title: String,
    @DrawableRes endIcon: Int? = null,
    endIconColor: Color = StoodTheme.colors.onSurface,
    onBackButtonClick: () -> Unit = {},
    onEndIconClick: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            StoodText(
                text = title,
                textAlign = if (centerTitle) TextAlign.Center else TextAlign.Start
            )
        },
        actions = {
            endIcon?.let {
                IconButton(onClick = { onEndIconClick?.invoke() }) {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = stringResource(R.string.content_description_top_bar_button),
                        tint = endIconColor
                    )
                }
            }
        },
        navigationIcon = {
            if (backButtonEnabled) {
                IconButton(onClick = { onBackButtonClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = stringResource(R.string.content_description_top_bar_button),
                        tint = StoodTheme.colors.onSurface
                    )
                }
            }
        }
    )
}