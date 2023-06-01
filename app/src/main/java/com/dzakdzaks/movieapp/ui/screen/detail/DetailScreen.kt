package com.dzakdzaks.movieapp.ui.screen.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.dzakdzaks.movieapp.ui.component.ScrollToTopButton
import com.dzakdzaks.movieapp.ui.screen.detail.components.CollapsingHeader
import com.dzakdzaks.movieapp.ui.screen.detail.components.ContentDesc
import com.dzakdzaks.movieapp.ui.screen.detail.components.ContentReviews
import com.dzakdzaks.movieapp.ui.screen.detail.components.ContentTrailer
import com.dzakdzaks.movieapp.ui.screen.detail.components.PinnedHeader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Destination(
    navArgsDelegate = DetailScreenNavArgs::class
)
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
) {

    val viewModel = hiltViewModel<DetailViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    val reviewPaging = viewModel.reviewsPaging.collectAsLazyPagingItems()

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val showScrollToTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val context = LocalContext.current

    val uriHandler = LocalUriHandler.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = listState,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 100.dp),
    ) {
        item {
            CollapsingHeader(
                context = context,
                lazyListState = listState,
                state = state,
                onBack = { navigator.navigateUp() })
        }
        stickyHeader {
            PinnedHeader(context = context, state = state, showScrollToTop = showScrollToTop) {
                navigator.navigateUp()
            }
        }
        item {
            ContentDesc(state = state)
        }
        item {
            ContentTrailer(
                context = context,
                state = state,
                onClick = { url ->
                    uriHandler.openUri(url)
                }
            )
        }
        item {
            ContentReviews(
                data = reviewPaging,
            )
        }
    }

    AnimatedVisibility(
        visible = showScrollToTop,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ScrollToTopButton {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }
        }
    }

}