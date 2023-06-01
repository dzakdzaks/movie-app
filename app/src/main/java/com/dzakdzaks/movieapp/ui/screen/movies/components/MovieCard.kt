package com.dzakdzaks.movieapp.ui.screen.movies.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dzakdzaks.movieapp.ui.component.rememberBrokenImage
import com.dzakdzaks.movieappcore.model.movie.Movie

@Composable
fun MovieCard(context: Context, movie: Movie, onClick: (Movie) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .height(250.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(movie) },
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = ImageRequest.Builder(context = context)
                    .data(movie.getFullPosterPath())
                    .crossfade(true)
                    .build(),
                error = rememberVectorPainter(image = rememberBrokenImage()),
                contentDescription = "Poster Image",
                contentScale = ContentScale.FillBounds,
            )

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
                    text = movie.title,
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}