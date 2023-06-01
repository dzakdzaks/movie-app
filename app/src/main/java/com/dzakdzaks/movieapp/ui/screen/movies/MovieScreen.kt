package com.dzakdzaks.movieapp.ui.screen.movies

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.dzakdzaks.movieapp.R
import com.dzakdzaks.movieapp.ui.component.ScrollToTopButton
import com.dzakdzaks.movieapp.ui.screen.destinations.DetailScreenDestination
import com.dzakdzaks.movieapp.ui.screen.movies.components.ErrorItem
import com.dzakdzaks.movieapp.ui.screen.movies.components.LoadingItem
import com.dzakdzaks.movieapp.ui.screen.movies.components.MovieCard
import com.dzakdzaks.movieapp.ui.screen.movies.components.PullRefreshBox
import com.dzakdzaks.movieappcore.model.movie.Movie
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Destination(start = true)
@Composable
fun MovieScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<MovieViewModel>()

    val moviesPaging = viewModel.nowPlayingMoviesPaging.collectAsLazyPagingItems()

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()

    val showScrollToTop by remember {
        derivedStateOf {
            gridState.firstVisibleItemIndex > 0
        }
    }

    var refreshing by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) { innerPadding ->
        PullRefreshBox(
            refreshing = refreshing,
            onRefresh = {
                moviesPaging.refresh()
            }
        ) {
            ContentList(
                context = context,
                innerPadding = innerPadding,
                data = moviesPaging,
                gridState = gridState,
                onRefreshing = { isRefreshing ->
                    refreshing = isRefreshing
                },
                onClickMovie = { movie ->
                    navigator.navigate(
                        DetailScreenDestination(
                            movieId = movie.id
                        )
                    )
                }
            )
            AnimatedVisibility(
                visible = showScrollToTop,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                ScrollToTopButton(modifier = Modifier.padding(innerPadding)) {
                    coroutineScope.launch {
                        gridState.animateScrollToItem(0)
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentList(
    context: Context,
    innerPadding: PaddingValues,
    data: LazyPagingItems<Movie>,
    gridState: LazyGridState,
    onClickMovie: (Movie) -> Unit,
    onRefreshing: (Boolean) -> Unit,
) {

    val loadState = data.loadState.mediator

    when (loadState?.refresh) {
        LoadState.Loading -> {
            onRefreshing(true)
            LoadingItem(modifier = Modifier.padding(innerPadding))
        }


        is LoadState.Error -> {
            onRefreshing(false)
            val refreshErrorMsg = (loadState.refresh as LoadState.Error).error.message
            ErrorItem(modifier = Modifier.padding(innerPadding), msg = refreshErrorMsg, action = {
                data.refresh()
            })
        }

        is LoadState.NotLoading -> {
            onRefreshing(false)
            LazyVerticalGrid(
                state = gridState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = data.itemCount,
                    key = data.itemKey(),
                    contentType = data.itemContentType()
                ) { index ->
                    data[index]?.let { movie ->
                        MovieCard(context = context, movie = movie, onClick = onClickMovie)
                    }
                }

                item {
                    when (loadState.append) {
                        LoadState.Loading -> {
                            LoadingItem()
                        }

                        is LoadState.Error -> {
                            val appendErrorMsg = (loadState.append as LoadState.Error).error.message
                            ErrorItem(msg = appendErrorMsg) {
                                data.retry()
                            }
                        }

                        else -> {}
                    }
                }
            }
        }

        else -> {}
    }
}