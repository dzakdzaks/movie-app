package com.dzakdzaks.movieapp.ui.screen.detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.dzakdzaks.movieapp.common.ext.readableDateTime
import com.dzakdzaks.movieapp.common.model.movie.review.MovieReview

@Composable
fun ContentReviews(
    data: LazyPagingItems<MovieReview>,
) {

    when (data.loadState.refresh) {
        LoadState.Loading -> LoadingDetail()
        is LoadState.NotLoading -> {
            Content(
                data = data,
            )
        }
        is LoadState.Error -> {
            val refreshErrorMsg = (data.loadState.refresh as LoadState.Error).error.message
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "Movie Reviews ${refreshErrorMsg ?: "Unknown error"}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
        }
        else -> {}
    }
}

@Composable
private fun Content(
    data: LazyPagingItems<MovieReview>,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = if (data.itemCount > 1) "Reviews" else "Review",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.padding(4.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            count = data.itemCount,
            key = data.itemKey(),
            contentType = data.itemContentType()
        ) { index ->
            ReviewCard(
                data = data[index],
            )
        }
    }
}

@Composable
private fun ReviewCard(
    data: MovieReview?,
) {
    data?.let { review ->
        Column(
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(16.dp)
                .animateContentSize(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = review.author,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Written on ${review.createdAt.readableDateTime()}",
                style = MaterialTheme.typography.bodySmall,
            )
            if (review.createdAt.readableDateTime() != review.updatedAt.readableDateTime()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Edited on ${review.updatedAt.readableDateTime()}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
            ExpandableText(
                modifier = Modifier.fillMaxWidth(),
                text = review.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
