package com.dzakdzaks.movieapp.ui.screen.detail.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dzakdzaks.movieapp.ui.component.rememberBrokenImage
import com.dzakdzaks.movieapp.ui.screen.detail.DetailState

@Composable
fun PinnedHeader(
    context: Context,
    state: DetailState,
    showScrollToTop: Boolean,
    onBack: () -> Unit,
) {
    when (state.movieDetailLoading) {
        true -> {}
        false -> {
            if (state.movieDetailError.isEmpty()) {
                Content(
                    context = context,
                    state = state,
                    showScrollToTop = showScrollToTop,
                    onBack = onBack
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        text = "Movie Detail",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun Content(
    context: Context,
    state: DetailState,
    showScrollToTop: Boolean,
    onBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            visible = !showScrollToTop,
            enter = fadeIn() + expandIn(),
            exit = fadeOut() + shrinkOut(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = ImageRequest.Builder(context = context)
                    .data(state.movieDetail.getFullPosterPath())
                    .crossfade(true)
                    .build(),
                error = rememberVectorPainter(image = rememberBrokenImage()),
                contentDescription = "Poster Image",
                contentScale = ContentScale.Fit
            )
        }

        AnimatedVisibility(visible = showScrollToTop) {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }
        }

        Text(
            modifier = if (showScrollToTop) Modifier.padding(horizontal = 16.dp) else Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = state.movieDetail.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            maxLines = if (showScrollToTop) 1 else 5,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
        )
    }
}