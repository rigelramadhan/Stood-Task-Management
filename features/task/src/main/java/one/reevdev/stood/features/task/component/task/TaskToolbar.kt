package one.reevdev.stood.features.task.component.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.stood.features.task.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackButtonClick: (() -> Unit)? = null,
    actions: Map<ImageVector, () -> Unit> = mapOf()
) {
    TopAppBar(
        modifier = modifier
            .shadow(elevation = 2.dp),
        navigationIcon = {
            onBackButtonClick?.let {
                IconButton(onClick = { it() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_back_button)
                    )
                }
            }
        },
        title = {
            Text(
                modifier = Modifier,
                text = title
            )
        },
        actions = {
            actions.forEach {
                IconButton(onClick = { it.value() }) {
                    Icon(
                        imageVector = it.key,
                        contentDescription = stringResource(R.string.content_description_action_button)
                    )
                }
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}