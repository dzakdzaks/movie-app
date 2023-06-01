package com.dzakdzaks.movieapp.ui.screen.detail.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.dzakdzaks.movieapp.common.model.movie.video.ResultMovieVideo

@Composable
fun ContentTrailer(
    context: Context,
    state: DetailState,
    onClick: (String) -> Unit,
) {
    when (state.movieTrailerLoading) {
        true -> LoadingDetail()
        false -> {
            if (state.movieTrailers.isNotEmpty()) {
                Content(
                    context = context,
                    state = state,
                    onClick = onClick
                )
            } else {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = state.movieTrailerError,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

}

@Composable
private fun Content(
    context: Context,
    state: DetailState,
    onClick: (String) -> Unit,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = if (state.movieTrailers.size > 1) "Trailers" else "Trailer",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
    )
    Spacer(modifier = Modifier.padding(4.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.movieTrailers) {
            TrailerCard(
                context = context,
                video = it,
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun TrailerCard(
    context: Context,
    video: ResultMovieVideo,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = ImageRequest.Builder(context = context)
                .data(video.getThumbnail())
                .crossfade(true)
                .build(),
            error = rememberVectorPainter(image = rememberBrokenImage()),
            contentDescription = "Thumbnail",
            contentScale = ContentScale.FillBounds,
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
            onClick = { onClick(video.getUrl()) }
        ) {
            Icon(
                imageVector = Icons.Outlined.PlayArrow,
                contentDescription = "Play"
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.Black.copy(alpha = 0.7f))
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(6.dp),
                text = video.name,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}