package com.dzakdzaks.movieapp.ui.screen.movies.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshBox(
    modifier: Modifier = Modifier,
    refreshing: Boolean,
    onRefresh: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    // The LaunchedEffect is needed to fix the hiding of the indicator: https://issuetracker.google.com/issues/248274004
    // Without this workaround the indicator would be visible even if the loading finished
    var isRefreshingWorkaround by remember { mutableStateOf(refreshing) }
    LaunchedEffect(key1 = refreshing) {
        isRefreshingWorkaround = refreshing
    }
    val state = rememberPullRefreshState(isRefreshingWorkaround, onRefresh)
    Box(
        modifier = modifier.pullRefresh(state),
    ) {
        content()
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = isRefreshingWorkaround,
            state = state,
            contentColor = MaterialTheme.colorScheme.primary,
        )
    }
}