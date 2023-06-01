package com.dzakdzaks.movieapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScrollToTopButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier
            .fillMaxSize()
            .padding(24.dp), Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onClick
        ) {
            Icon(imageVector = rememberArrowUpward(), "arrow up")
        }
    }
}