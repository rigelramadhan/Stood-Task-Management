package one.reevdev.stood.features.task.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.stood.ui.theme.StoodTheme

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    title: String,
    priority: Int,
    hour: String,
    date: String,
    navigateToDetail: () -> Unit,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { navigateToDetail() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                HourAndDateText(
                    modifier = Modifier,
                    hour = hour,
                    date = date
                )
            }
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = priority.toString(),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun HourAndDateText(
    modifier: Modifier = Modifier,
    hour: String,
    date: String,
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier,
            text = hour,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier,
            text = date,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    StoodTheme {
        TaskItem(
            title = "Do Some Chores",
            priority = 3,
            hour = "14:25",
            date = "27 November 2001",
            navigateToDetail = {}
        )
    }
}