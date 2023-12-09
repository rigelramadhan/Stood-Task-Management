package one.reevdev.stood.features.task.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import one.reevdev.stood.features.task.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskToolbar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackButtonClick: () -> Unit,
    actions: Map<ImageVector, () -> Unit>
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { onBackButtonClick() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.content_description_back_button)
                )
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
                IconButton(onClick = { it.value }) {
                    Icon(
                        imageVector = it.key,
                        contentDescription = stringResource(R.string.content_description_action_button)
                    )
                }
            }
        }
    )
}