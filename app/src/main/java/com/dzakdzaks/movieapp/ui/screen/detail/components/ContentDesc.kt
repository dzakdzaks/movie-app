package com.dzakdzaks.movieapp.ui.screen.detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dzakdzaks.movieapp.ui.screen.detail.DetailState

@Composable
fun ContentDesc(state: DetailState) {
    when (state.movieDetailLoading) {
        true -> {
            LoadingDetail()
        }

        false -> {
            if (state.movieDetailError.isEmpty()) {
                Content(
                    state = state
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = state.movieDetailError,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Content(state: DetailState) {
    val movie = state.movieDetail
    Column(
        modifier = Modifier
            .animateContentSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = if (movie.isTitleAndOriginalTitleSame()) movie.title else "${movie.title} (${movie.originalTitle})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        if (movie.getTaglineWithQuotation().isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = movie.getTaglineWithQuotation(),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RatingStar(vote = movie.getVoteRound())
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = movie.getYearOfReleaseDate(),
                style = MaterialTheme.typography.bodyMedium
            )
            if (movie.adult) {
                TextChip(modifier = Modifier.align(Alignment.CenterVertically), text = "18+")
            }
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = movie.getReadableRuntime(),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            movie.genres.forEach { genre ->
                TextChip(modifier = Modifier.align(Alignment.CenterVertically), text = genre.name)
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        ExpandableText(
            modifier = Modifier.fillMaxWidth(),
            text = movie.overview,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun RatingStar(vote: String) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Star",
            tint = Color(0xFFFFD700)
        )
        Spacer(modifier = Modifier.padding(1.dp))
        Text(
            text = vote,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}