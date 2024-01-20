package one.reevdev.stood.features.task.component.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import one.reevdev.cosmoe.utils.toComposeColor
import one.reevdev.stood.core.domain.task.model.Category
import one.reevdev.stood.features.task.R

@Composable
fun CategoryDropdown(
    modifier: Modifier = Modifier,
    category: Category,
    isDropdownExpanded: Boolean,
    onDropdownClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                onDropdownClick()
            },
        shape = CircleShape
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(24.dp)
                    .background(color = category.color.toComposeColor()),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier,
                text = category.name,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = if (isDropdownExpanded)
                    Icons.Outlined.KeyboardArrowUp else
                    Icons.Outlined.KeyboardArrowDown,
                contentDescription = stringResource(
                    R.string.content_description_dropdown_icon
                )
            )
        }
    }
}