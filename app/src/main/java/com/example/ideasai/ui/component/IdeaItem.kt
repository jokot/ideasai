package com.example.ideasai.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ideasai.core.data.model.Idea
import java.util.UUID

@Composable
fun IdeaItem(
    idea: Idea,
    onToggleFavorite: (Idea) -> Unit,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(idea.id) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = idea.name,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = idea.topic,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            IconButton(
                onClick = { onToggleFavorite(idea) },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (idea.isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    contentDescription = if (idea.isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (idea.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IdeaItemPreview() {
    MaterialTheme {
        IdeaItem(
            idea = Idea(
                id = UUID.randomUUID().toString(),
                name = "AI Shopping Assistant",
                topic = "E-Commerce",
                isFavorite = true
            ),
            onToggleFavorite = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NonFavoriteIdeaItemPreview() {
    MaterialTheme {
        IdeaItem(
            idea = Idea(
                id = UUID.randomUUID().toString(),
                name = "VR Fitness Platform",
                topic = "Health Tech",
                isFavorite = false
            ),
            onToggleFavorite = {}
        )
    }
}