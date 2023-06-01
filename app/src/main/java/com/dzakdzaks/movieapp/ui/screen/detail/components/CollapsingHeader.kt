package com.dzakdzaks.movieapp.ui.screen.detail.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dzakdzaks.movieapp.ui.component.HorizontalPagerIndicator
import com.dzakdzaks.movieapp.ui.component.rememberBrokenImage
import com.dzakdzaks.movieapp.ui.screen.detail.DetailState
import kotlinx.coroutines.launch

@Composable
fun CollapsingHeader(
    context: Context,
    lazyListState: LazyListState,
    state: DetailState,
    onBack: () -> Unit,
) {
    when (state.movieImageLoading) {
        true -> {}
        false -> {
            if (state.movieImageError.isEmpty()) {
                Content(
                    context = context,
                    lazyListState = lazyListState,
                    state = state,
                    onBack = onBack
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    context: Context,
    lazyListState: LazyListState,
    state: DetailState,
    onBack: () -> Unit,
) {
    var scrolledY = 0f
    var previousOffset = 0

    val pagerState = rememberPagerState { state.movieImages.size }

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .graphicsLayer {
            scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
            translationY = scrolledY * 0.5f
            previousOffset = lazyListState.firstVisibleItemScrollOffset
        }
        .fillMaxWidth()) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            state = pagerState,
        ) { index ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = ImageRequest.Builder(context = context)
                    .data(state.movieImages[index].getFullBackdropPath())
                    .crossfade(true)
                    .build(),
                error = rememberVectorPainter(image = rememberBrokenImage()),
                contentDescription = "Backdrop Image",
                contentScale = ContentScale.FillBounds
            )
        }
        HorizontalPagerIndicator(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp),
            pagerState = pagerState,
        ) {
            coroutineScope.launch {
                pagerState.scrollToPage(it)
            }
        }
        TextChip(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            text = "${pagerState.currentPage.plus(1)}/${pagerState.pageCount}"
        )
        IconButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
            onClick = onBack
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
        }
    }
}