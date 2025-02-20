package com.exa.android.vlogassignment.presentation.VlogScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.exa.android.vlogassignment.domain.Vlog
import com.exa.android.vlogassignment.ui.theme.VlogAssignmentTheme


@Composable
fun VlogItem(
    vlog: Vlog,
    modifier: Modifier = Modifier,
    onClick: (String?) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(vlog.vlogLink)
            }
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = vlog.imageUrl,
                contentDescription = "News Image",
                modifier = Modifier
                    .size(100.dp).padding(2.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = vlog.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = vlog.date,
                        fontSize = 12.sp,
                        color = Color.Black.copy(.4f)
                    )
                    Spacer(modifier = Modifier.weight(1f))  // Push arrow to end
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Go to Web",
                        tint = Color.Cyan,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewVlogItem() {
    VlogItem(
        Vlog(
            title = "Killing of teacher and Hamas assault set a jittery France.",
            date = "Jun 12, 2024",
            imageUrl = "https://your-image-url.com/sample.jpg",
            author = 554,
            vlogLink = null
        )
    ) {

    }

}